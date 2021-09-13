package com.example.tvprogramapp.ui

import android.animation.TimeInterpolator
import android.os.Bundle
import android.transition.Transition
import android.util.Log
import android.view.View
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.transition.addListener
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.transition.ChangeBounds
import com.bumptech.glide.Glide
import com.example.tvprogramapp.R
import com.example.tvprogramapp.databinding.ActivityDetailsBinding
import com.example.tvprogramapp.dto.Program
import com.example.tvprogramapp.helpers.viewBinding
import org.koin.android.ext.android.bind


class DetailsActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityDetailsBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        with(window) {
            exitTransition = null
            enterTransition = null

            sharedElementEnterTransition.apply {
                duration = 400
                interpolator = FastOutSlowInInterpolator()
                binding.mainContent.translationY = 100f
                addListener(onEnd = {
                    binding.mainContent.animate().alpha(1.0f).translationY(0f).setDuration(250)
                        .start()
                })
            }

            sharedElementReturnTransition.apply {
                addListener(
                    onStart = {
                        binding.mainContent.animate().alpha(0.0f).translationY(100f)
                            .setDuration(250)
                            .start()
                    }
                )
            }
        }

        supportStartPostponedEnterTransition()
        val extras = intent.extras

        val program: Program = extras!!.getSerializable("program") as Program


        with(binding) {
            name.text = program.name
            container.transitionName = extras.getString("transition_name")
            description.text = program.description.ifEmpty { "No description" }
            start.text = program.start
            end.text = program.end
            Glide.with(applicationContext).load(program.icon).into(logo)

        }

    }


}