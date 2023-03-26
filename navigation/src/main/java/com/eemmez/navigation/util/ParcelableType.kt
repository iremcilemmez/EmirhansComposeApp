package com.eemmez.navigation.util

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.eemmez.navigation.util.extension.getSafeParcelable
import com.google.gson.Gson

@Suppress("DEPRECATION")
class ParcelableType<T : Parcelable>(
    private val clazz: Class<T>,
    private val gson: Gson
) : NavType<T>(isNullableAllowed = true) {

    override fun get(bundle: Bundle, key: String): T? =
        bundle.getSafeParcelable(key, clazz)

    override fun parseValue(value: String): T =
        gson.fromJson(value, clazz)

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }
}