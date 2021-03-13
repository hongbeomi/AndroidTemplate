package github.hongbeomi.library.ext

import android.graphics.Bitmap
import android.graphics.Canvas

inline fun Bitmap.onCanvas(
    action: Canvas.() -> Unit
): Bitmap = also {
    Canvas(it).run(action)
}

