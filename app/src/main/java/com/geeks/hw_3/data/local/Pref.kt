package com.geeks.hw_3.data.local

import android.content.Context
import android.content.SharedPreferences

class Pref(context: Context) {

    val pref: SharedPreferences = context.getSharedPreferences("Key", Context.MODE_PRIVATE)

    fun saveCounter(value: String, counter: String?){
        pref.edit().putString("keycout", value)
    }

    fun getCounter(): String? {
        return pref.getString("keycout","hey")
    }
}