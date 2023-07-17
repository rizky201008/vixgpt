package com.vixiloc.vixgpt.data.sharedpreferences

import android.content.Context

class Model(context:Context) {
    companion object {
        var PREFS_NAME = "model"
        var MODEL_NAME = "model_name"

    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun getModel(): String? {
        val apiKey = preferences.getString(MODEL_NAME, "gpt-3.5-turbo")
        return apiKey
    }

    fun setModel(newVal: String) {
        val editor = preferences.edit()
        editor.putString(MODEL_NAME, newVal)
        editor.apply()
    }
}