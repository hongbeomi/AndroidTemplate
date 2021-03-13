package github.hongbeomi.library.util

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider

class OvalOutLineProvider : ViewOutlineProvider() {

    override fun getOutline(view: View, outline: Outline) {
        view.apply {
            outline.setOval(
                paddingLeft,
                paddingTop,
                width - paddingRight,
                height - paddingBottom
            )
        }
    }

}

class RoundRectOutlineProvider(private val radius: Float) : ViewOutlineProvider() {

    override fun getOutline(view: View, outline: Outline) {
        view.apply {
            outline.setRoundRect(
                paddingLeft,
                paddingTop,
                width - paddingRight,
                height - paddingBottom,
                radius
            )
        }
    }

}