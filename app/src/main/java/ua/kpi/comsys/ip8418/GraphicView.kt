package ua.kpi.comsys.ip8418

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

typealias XY = Pair<Float, Float>

class GraphicView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        readAttrs(attrs)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        if (attrs != null) readAttrs(attrs)
    }

    private var graphColor = Color.BLACK

    private fun readAttrs(attrs: AttributeSet) {
        with(context.obtainStyledAttributes(attrs, R.styleable.GraphicView)) {
            graphColor = getColor(R.styleable.GraphicView_graphColor, Color.BLACK)
            recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = Paint().apply {
            color = Color.BLACK
            strokeWidth = 5f
        }

        val angle = 0.3
        val length = 0.05 * width
        val one = width/12
        var x1 = 0f
        var y1 = 0f
        var x2 = 0f
        var y2 = 0f
        fun drawLine() = canvas?.drawLine(x1, y1, x2, y2, paint)

        //abscissa
        y1 = (height / 2).toFloat()
        x2 = width.toFloat()
        y2 = y1
        drawLine()

        //arrow
        x1 = (width - length * cos(angle)).toFloat()
        y1 = (height / 2 - length * sin(angle)).toFloat()
        x2 = width.toFloat()
        drawLine()
        y1 = (height / 2 + length * sin(angle)).toFloat()
        drawLine()

        //ordinance
        x1 = (width / 2).toFloat()
        y1 = 0f
        x2 = x1
        y2 = height.toFloat()
        drawLine()

        //arrow
        x2 = (width / 2 - length * sin(angle)).toFloat()
        y2 = (length * cos(angle)).toFloat()
        drawLine()
        x2 = (width / 2 + length * sin(angle)).toFloat()
        drawLine()

        //single segments
        x1 = (width / 2 - length / 2).toFloat()
        y1 = (height / 2 - one).toFloat()
        x2 = (width / 2 + length / 2).toFloat()
        y2 = y1
        drawLine()

        x1 = (width / 2 + one).toFloat()
        y1 = (height / 2 - length / 2).toFloat()
        x2 = x1
        y2 = (height / 2 + length / 2).toFloat()
        drawLine()

        //Function y=e^x
        fun y(x: Float) = Math.E.toFloat().pow(x)
        val points = mutableListOf<XY>()
        val step = 0.25
        for (i in 0..24) {
            val x = (i * step).toFloat()
            points += width/2 + x * one to height/2 + y(x) * -one
            points += width/2 - x * one to height/2 + y(-x) * -one
        }
        points += (width/2).toFloat() to (height/2 + y(0f) * -one)
        points.sortBy { p -> p.first }

        paint.color = graphColor
        points.windowed(2, 1, false) {
            val (x1, y1) = it.first()
            val (x2, y2) = it.last()
            canvas?.drawLine(x1, y1, x2, y2, paint)
        }
    }
}