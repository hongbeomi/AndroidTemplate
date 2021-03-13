package github.hongbeomi.library.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations

fun <Y, X> LiveData<Y>.switchMap(switchMapFunction: (Y) -> LiveData<X>) =
    Transformations.switchMap(this, switchMapFunction)

fun <X, Y> LiveData<X>.map(mapFunction: (X) -> Y) =
    Transformations.map(this, mapFunction)

fun <T> LiveData<T>.observeOnce(
    lifecycleOwner: LifecycleOwner,
    observer: (T) -> Unit
) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T) {
            observer.invoke(t)
            removeObserver(this)
        }
    })
}