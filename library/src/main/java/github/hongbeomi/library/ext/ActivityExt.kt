package github.hongbeomi.library.ext

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun Activity.hasPermission(vararg permissions: String) = permissions.all {
    ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
}

fun Activity.requestPermissions(vararg permissions: String, requestPermissionCode: Int) = ActivityCompat.requestPermissions(
    this,
    permissions,
    requestPermissionCode
)