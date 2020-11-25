package com.findpet.UserRegister.datasource

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import data.RequestUser


private const val FINDPET_PREF_NAME = "FINDPET"

private const val USER_DATA_KEY = "USER_DATA"

class UserLocalDataSource(private val context: Context) {

    private val edit: SharedPreferences.Editor by lazy {
        context.getSharedPreferences(FINDPET_PREF_NAME, MODE_PRIVATE).edit()
    }

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(FINDPET_PREF_NAME, MODE_PRIVATE)
    }

    fun saveUser(user: RequestUser) {
        edit.run {
            putString(USER_DATA_KEY, Gson().toJson(user))
            apply()
        }
    }

    fun getUser(): RequestUser? =
        Gson().fromJson(sharedPreferences.getString(USER_DATA_KEY, null), RequestUser::class.java)

    fun clear() = edit.run {
        clear()
        apply()
    }
}