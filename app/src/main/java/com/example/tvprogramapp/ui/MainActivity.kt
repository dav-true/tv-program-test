package com.example.tvprogramapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tvprogramapp.adapters.LoadingStateAdapter
import com.example.tvprogramapp.adapters.ProgramRecyclerViewAdapter
import com.example.tvprogramapp.databinding.ActivityMainBinding
import com.example.tvprogramapp.dto.Program
import com.example.tvprogramapp.helpers.viewBinding
import com.example.tvprogramapp.interfaces.ItemClickListener
import com.example.tvprogramapp.viewmodels.ProgramViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), ItemClickListener {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel by viewModel<ProgramViewModel>()
    private val programAdapter: ProgramRecyclerViewAdapter by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = with(programAdapter) {
                clickListener = this@MainActivity
                addLoadStateListener {
                    if (itemCount > 0) {
                        lifecycleScope.launch(Dispatchers.Main) {
                            binding.loading.visibility = View.GONE
                        }
                    }
                }
                withLoadStateHeaderAndFooter(
                    header = LoadingStateAdapter(this),
                    footer = LoadingStateAdapter(this)
                )
            }
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }


        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getProgram()
                .collectLatest { pagingData ->
                    programAdapter.submitData(pagingData)
                }
        }

    }


    override fun openItem(program: Program, view: View) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("program", program)
        intent.putExtra(
            "transition_name",
            ViewCompat.getTransitionName(view)
        )
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            view,
            ViewCompat.getTransitionName(view)!!
        )
        startActivity(intent, options.toBundle())
    }

}