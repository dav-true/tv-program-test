package com.example.tvprogramapp.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvprogramapp.databinding.ProgramItemBinding
import com.example.tvprogramapp.dto.Program
import com.example.tvprogramapp.interfaces.ItemClickListener
import com.example.tvprogramapp.ui.MainActivity
import org.koin.dsl.koinApplication


class ProgramRecyclerViewAdapter(
    private val context: Context
) : PagingDataAdapter<Program, ProgramRecyclerViewAdapter.ViewHolder>(ProgramComparator) {

    lateinit var clickListener: ItemClickListener

    class ViewHolder(val binding: ProgramItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            name.text = getItem(holder.absoluteAdapterPosition)?.name
            Glide.with(context).load(getItem(holder.absoluteAdapterPosition)?.icon).into(logo)
            container.setOnClickListener {
                clickListener.openItem(getItem(holder.absoluteAdapterPosition)!!, container)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ProgramItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    object ProgramComparator : DiffUtil.ItemCallback<Program>() {
        override fun areItemsTheSame(oldItem: Program, newItem: Program) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Program, newItem: Program) =
            oldItem == newItem
    }


}