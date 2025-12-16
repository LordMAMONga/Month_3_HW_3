package com.geeks.hw_3.data.local

import android.R.attr.value
import android.content.Context
import android.content.SharedPreferences
import com.geeks.hw_3.core.AppKey
import androidx.core.content.edit

class Pref(context: Context) {

    val pref: SharedPreferences =
        context.getSharedPreferences(AppKey.KEY_PREF, Context.MODE_PRIVATE)

    fun setOnBoardingShown() {
        pref.edit { putBoolean(AppKey.KEY, true) }
    }

    fun isOnBoardingShown(): Boolean {
        return pref.getBoolean(AppKey.KEY, false)
    }
}

