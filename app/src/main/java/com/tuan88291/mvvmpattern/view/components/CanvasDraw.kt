package com.tuan88291.mvvmpattern.view.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class CanvasDraw(context: Context, attrs: AttributeSet): View(context, attrs) {
    private var mPaint: Paint? = null
    init {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint?.setColor(Color.RED)
        mPaint?.style = Paint.Style.STROKE
        mPaint?.strokeWidth = 10f
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val arcSize = 500
        val left = ((width / 2) - (arcSize / 2)).toFloat()
        val top = ((height / 2) - (arcSize / 2)).toFloat()
        val bottom = top + arcSize
        val right = left + arcSize

        canvas?.drawArc(RectF(left, top, right, bottom), 30f, 300f, true, mPaint!!)
        canvas?.drawCircle((left + (arcSize / 1.5)).toFloat(),
            (top + (arcSize / 4)).toFloat(), 30f, mPaint!!)

        canvas?.drawCircle((left + (arcSize / 1.4)).toFloat(),
            (top + (arcSize / 2)).toFloat(), 30f, mPaint!!)

        canvas?.drawCircle((left + (arcSize)).toFloat(),
            (top + (arcSize / 2)).toFloat(), 30f, mPaint!!)

    }
}