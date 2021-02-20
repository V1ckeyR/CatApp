package ua.kpi.comsys.ip8418.drawing

import android.annotation.SuppressLint
import android.R
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat


class DiagramView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
    )

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val orange = ContextCompat.getColor(context, R.color.holo_orange_dark)

        val paint = Paint().apply {
            color = orange
            strokeWidth = 200f
            style = Paint.Style.STROKE
        }

        paint.strokeWidth = (width*0.2).toFloat()
        val size = (width*0.7).toFloat()
        val left = (width-size)/2
        val top = (height-size)/2
        val right = (width+size)/2
        val bottom = (height+size)/2
        val rectF = RectF(left, top, right, bottom)
        val thirty = 360 * 0.3f

        canvas?.drawArc(rectF, 0f, thirty, false, paint)
        paint.color = Color.GREEN
        canvas?.drawArc(rectF, thirty, thirty, false, paint)
        paint.color = Color.BLACK
        canvas?.drawArc(rectF, thirty * 2, 360 * 0.4f, false, paint)
    }
}