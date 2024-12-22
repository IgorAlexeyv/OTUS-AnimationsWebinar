package com.otus.animations

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.animation.ValueAnimator.INFINITE
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

class MyCircle @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()
    private val circleParameters = CircleParameters()

    override fun onDraw(canvas: Canvas) {
        paint.alpha = circleParameters.alpha
        canvas.drawCircle(
            (width / 2).toFloat(), (height / 2).toFloat(), circleParameters.radius, paint
        )
        super.onDraw(canvas)
    }

    fun startCustomAnimation() {
        val radiusAnimatedValue = PropertyValuesHolder.ofFloat("radius", 0f, 300f)
        val alphaAnimatedValue = PropertyValuesHolder.ofInt("alpha", 0xFF, 0x0)
        ValueAnimator.ofPropertyValuesHolder(radiusAnimatedValue, alphaAnimatedValue)
            .apply {
                duration = 1000L
                interpolator = LinearInterpolator()
                repeatCount = INFINITE
                startDelay = 0
                addUpdateListener {
                    circleParameters.radius = it.getAnimatedValue("radius") as Float
                    circleParameters.alpha = it.getAnimatedValue("alpha") as Int
                    invalidate()
                }
                start()
            }
    }

    data class CircleParameters(var radius: Float = 200f, var alpha: Int = 255)
}
