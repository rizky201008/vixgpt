package com.vixiloc.vixgpt.data.sharedpreferences

import android.content.Context

class ApiKey(context: Context) {
    companion object {
        var PREFS_NAME = "api"
        var APIKEY_NAME = "api_key"

    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun getApiKey(): String? {
        val apiKey = preferences.getString(APIKEY_NAME, null)
        return apiKey
    }

    fun setApiKey(newVal: String) {
        val editor = preferences.edit()
        editor.putString(APIKEY_NAME, newVal)
        editor.apply()
    }

}