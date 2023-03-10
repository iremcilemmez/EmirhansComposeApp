package com.eemmez.navigation.util.extension

import android.os.Build
import android.os.Parcelable
import androidx.navigation.NavHostController

fun <T : Parcelable> NavHostController.putParcelable(key: String, data: T) {
    currentBackStackEntry?.arguments?.putParcelable(key, data)
}

@Suppress("DEPRECATION")
fun <T : Parcelable> NavHostController.getParcelable(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        previousBackStackEntry?.arguments?.getParcelable(key, clazz)
    } else {
        previousBackStackEntry?.arguments?.getParcelable(key)
    }
}