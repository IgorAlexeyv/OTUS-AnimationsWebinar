package com.otus.animations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import com.otus.animations.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.circle) { setOnClickListener { startAnimation() } }
    }

    private fun startAnimation() {
        with(binding.circle) {
            val translationX = ObjectAnimator.ofFloat(this, "translationX", 0f, this.width.toFloat() / 2, 0f)
            val scaleUpX = ObjectAnimator.ofFloat(this, "scaleX", 1f, 0.5f, 1f, 1f, 1.2f, 1f)
            val scaleUpY = ObjectAnimator.ofFloat(this, "scaleY", 1f, 0.5f, 1f, 1f, 1.2f, 1f)
            val fadeOut = ObjectAnimator.ofFloat(this, "alpha", 1f, 0f, 1f, 1f, 1f, 1f)

            AnimatorSet().apply {
                duration = 2000
                interpolator = AccelerateDecelerateInterpolator()
                playTogether(translationX, scaleUpX, scaleUpY, fadeOut)
            }.start()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}