package com.tuan88291.mvvmpattern.view.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View

class CanvasDraw(context: Context, attrs: AttributeSet): View(context, attrs) {
    private var mPaint: Paint? = null
    private var mPaint1: Paint? = null
    private var mPaint2: Paint? = null
    private var mText: TextPaint? = null
    init {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint?.setColor(Color.RED)
        mPaint?.style = Paint.Style.STROKE
        mPaint?.strokeWidth = 2f

        mPaint1 = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint1?.setColor(Color.BLUE)
        mPaint1?.style = Paint.Style.STROKE
        mPaint1?.strokeWidth = 2f

        mText = TextPaint(Paint.ANTI_ALIAS_FLAG)
        mText?.color = Color.RED
        mText?.textSize = 45f
        mText?.textAlign = Paint.Align.CENTER

        mPaint2 = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint2?.setColor(Color.RED)
        mPaint2?.style = Paint.Style.STROKE
        mPaint2?.strokeWidth = 30f
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val arcSize = (((width / 2.2) * 2) - 30).toFloat()
        val left = ((width / 2) - (arcSize / 2)).toFloat()
        val top = ((height / 2) - (arcSize / 2)).toFloat()
        val bottom = top + arcSize
        val right = left + arcSize

        canvas?.drawArc(RectF(left, top, right, bottom), 30f, 300f, false, mPaint2!!)
        canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), (width / 2.2).toFloat(), mPaint!!)
        canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), ((width / 2.2) - 30).toFloat(), mPaint1!!)

        canvas?.drawText("12", ((width / 2)).toFloat(),
            (((height - (((width / 2.2) - 30) * 2)) / 2) + 45).toFloat(), mText!!)

        val dis1 = (width - (((width / 2.2) - 30) * 2)) / 2
        canvas?.drawText("09", (dis1 + 30).toFloat(),
            (height / 2).toFloat(), mText!!)

        val dis = (width - (((width / 2.2) - 30) * 2)) / 2
        canvas?.drawText("15", (width - (dis + 35)).toFloat(),
            (height / 2).toFloat(), mText!!)

        val disy = (((height - (((width / 2.2) - 30) * 2))) / 2) + (((width / 2.2) - 30) * 2)
        canvas?.drawText("18", ((width / 2)).toFloat(),
            (disy - 10).toFloat(), mText!!)

    }
}