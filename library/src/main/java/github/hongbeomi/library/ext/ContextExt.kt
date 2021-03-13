package github.hongbeomi.library.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * keyboard
 *
 */

fun Context.showKeyboard(view: View? = null) {
    view?.requestLayout()
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
        InputMethodManager.SHOW_FORCED,
        0
    )
}

fun Context.hideKeyboard() {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
        InputMethodManager.HIDE_IMPLICIT_ONLY,
        0
    )
}

/**
 * toast
 */

fun Context.showShort(@StringRes stringRes: Int, context: Context) {
    showShort(context.getString(stringRes), context)
}

fun Context.showShort(charSequence: CharSequence, context: Context) {
    showShort(charSequence.toString(), context)
}

fun Context.showShort(string: String, context: Context) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
}

fun Context.showLong(@StringRes stringRes: Int, context: Context) {
    showLong(context.getString(stringRes), context)
}

fun Context.showLong(charSequence: CharSequence, context: Context) {
    showLong(charSequence.toString(), context)
}

fun Context.showLong(string: String, context: Context) {
    Toast.makeText(context, string, Toast.LENGTH_LONG).show()
}

/**
 * open activity
 */

inline fun <reified T : Activity> Context.startActivity(noinline action: Intent.() -> Unit) {
    startActivity(
        Intent(this, T::class.java).apply(action)
    )
}