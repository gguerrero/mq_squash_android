package com.mqsquashers.app.features.menu

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mqsquashers.app.R


// An ItemDecoration that draws a divider between items in a RecyclerView.
class DividerItemDecoration(
    private val context: Context
) : RecyclerView.ItemDecoration() {

    // Load the separator drawable we created earlier
    private val divider = ContextCompat.getDrawable(context, R.drawable.recycler_view_separator)

    /**
     * This method is called by the RecyclerView to draw the decorations.
     * We will draw our line here.
     */
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (divider == null) return

        // Convert 12dp to pixels
        val paddingPx = 12f.dpToPx()

        val left = parent.paddingLeft + paddingPx.toInt()
        val right = parent.width - parent.paddingRight - paddingPx.toInt()

        // Loop through all visible children *except the last one*
        for (i in 0 until parent.childCount - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            // Calculate the position to draw the divider
            val top = child.bottom + params.bottomMargin
            val bottom = top + divider.intrinsicHeight

            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
    }

    /**
     * This method adds space to the bottom of each item to make room for the divider.
     */
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        // Don't add space after the very last item in the list
        if (parent.getChildAdapterPosition(view) != state.itemCount - 1) {
            outRect.bottom = divider?.intrinsicHeight ?: 0
        }
    }

    // Utility function to convert DP to PX
    private fun Float.dpToPx(): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            context.resources.displayMetrics
        )
    }
}
