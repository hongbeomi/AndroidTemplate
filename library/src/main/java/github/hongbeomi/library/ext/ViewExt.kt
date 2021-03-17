package github.hongbeomi.library.ext

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.databinding.BindingAdapter
import github.hongbeomi.library.util.OvalOutLineProvider
import github.hongbeomi.library.util.RoundRectOutlineProvider
import kotlinx.coroutines.*


fun <T> debounce(
    delayMillis: Long,
    scope: CoroutineScope = MainScope(),
    action: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        if (debounceJob == null) {
            debounceJob = scope.launch {
                action(param)
                delay(delayMillis)
                debounceJob = null
            }
        }
    }
}

@BindingAdapter(value = ["delayMillis", "onOnceClickListener"], requireAll = false)
fun View.setOnOnceClickListener(delayMillis: Long = 1000L, listener: View.OnClickListener) {
    val clickWithDebounce: (View) -> Unit = debounce(delayMillis) {
        listener.onClick(it)
    }
    setOnClickListener(clickWithDebounce)
}

fun View.setMargin(left: Int? = null, top: Int? = null, right: Int? = null, bottom: Int? = null) =
    updateLayoutParams<ViewGroup.MarginLayoutParams> {
        left?.let { leftMargin = it }
        top?.let { topMargin = it }
        right?.let { rightMargin = it }
        bottom?.let { bottomMargin = it }
    }

fun View.getBitmap(): Bitmap? {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    layout(left, top, right, bottom)
    draw(Canvas(bitmap))
    return bitmap
}

fun View.clipToOval() {
    clipToOutline = true
    outlineProvider = OvalOutLineProvider()
}

fun View.clipToRoundRect(radius: Float) {
    clipToOutline = true
    outlineProvider = RoundRectOutlineProvider(radius)
}