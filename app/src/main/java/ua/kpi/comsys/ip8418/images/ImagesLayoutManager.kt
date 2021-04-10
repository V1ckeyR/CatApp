package ua.kpi.comsys.ip8418.images

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max
import kotlin.math.min

class ImagesLayoutManager : RecyclerView.LayoutManager() {
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.MATCH_PARENT
        )
    }

    private fun measureChildWithDecorationsAndMargin(child: View, widthSpec: Int, heightSpec: Int) {
        val decorRect = Rect()
        calculateItemDecorationsForChild(child, decorRect)
        val lp = child.layoutParams as RecyclerView.LayoutParams
        val width = updateSpecWithExtra(
                widthSpec,
                lp.leftMargin + decorRect.left,
                lp.rightMargin + decorRect.right
        )
        val height = updateSpecWithExtra(
                heightSpec,
                lp.topMargin + decorRect.top,
                lp.bottomMargin + decorRect.bottom
        )
        child.measure(width, height)
    }

    private fun updateSpecWithExtra(spec: Int, startInset: Int, endInset: Int): Int {
        if (startInset == 0 && endInset == 0) return spec
        val mode = View.MeasureSpec.getMode(spec)
        return if (mode == View.MeasureSpec.AT_MOST || mode == View.MeasureSpec.EXACTLY) {
            View.MeasureSpec.makeMeasureSpec(
                    View.MeasureSpec.getSize(spec) - startInset - endInset,
                    mode
            )
        } else {
            spec
        }
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State?) {
        detachAndScrapAttachedViews(recycler)

        var position = 0
        val size = width / 3
        var top = 0
        var bottom = 2 * size
        var left = 0
        var right = 2 * size

        while (position < itemCount) {
            val view = recycler.getViewForPosition(position)
            addView(view)
            measureChildWithMargins(view, width, height)

            var widthSpec = View.MeasureSpec.makeMeasureSpec(size, View.MeasureSpec.EXACTLY)
            var heightSpec = View.MeasureSpec.makeMeasureSpec(size, View.MeasureSpec.EXACTLY)

            if (position % 9 == 0 || position % 9 == 7) {
                widthSpec = View.MeasureSpec.makeMeasureSpec(2 * size, View.MeasureSpec.EXACTLY)
                heightSpec = View.MeasureSpec.makeMeasureSpec(2 * size, View.MeasureSpec.EXACTLY)
            }
            measureChildWithDecorationsAndMargin(view, widthSpec, heightSpec)

            layoutDecorated(view, left, top, right, bottom)

            when (position % 9) {
                0 -> {
                    bottom = top + size
                    left = 2 * size
                    right = width
                }

                1 -> {
                    top = getDecoratedBottom(view)
                    bottom = top + size
                }

                2, 5 -> {
                    top = getDecoratedBottom(view)
                    bottom = top + size
                    left = 0
                    right = size
                }

                3 -> {
                    left = size
                    right = 2 * size
                }

                4 -> {
                    left = 2 * size
                    right = width
                }

                6 -> {
                    bottom = top + 2 * size
                    left = size
                    right = width
                }

                7 -> {
                    top += size
                    bottom = top + size
                    left = 0
                    right = size
                }

                8 -> {
                    top = getDecoratedBottom(view)
                    bottom = top + 2 * size
                    right = 2 * size
                }
            }
            position++
        }
    }

    override fun canScrollVertically(): Boolean = true

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val delta = scrollVerticallyInternal(dy)
        offsetChildrenVertical(-delta)
        return delta
    }

    private fun scrollVerticallyInternal(dy: Int): Int {
        if (childCount == 0) {
            return 0
        }

        val topView = getChildAt(0)
        val bottomView = getChildAt(childCount - 1)

        val viewSpan = getDecoratedBottom(bottomView!!) - getDecoratedTop(topView!!)
        if (viewSpan <= height) return 0

        var delta = 0
        if (dy < 0) {
            val firstView = getChildAt(0)
            val firstViewPosition = getPosition(firstView!!)
            delta = if (firstViewPosition > 0) dy else max(getDecoratedTop(firstView), dy)
        } else if (dy > 0) {
            val lastView = getChildAt(childCount - 1)
            val lastViewPosition = getPosition(lastView!!)
            delta = if (lastViewPosition < itemCount - 1) dy else min(getDecoratedBottom(lastView) - height, dy)
        }
        return delta
    }

}