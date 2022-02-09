package com.juniorteam.football.util


import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

object PreferenceHelper {

    const val CUSTOM_PREF_NAME = "User_data"
    private const val USER_STATE = "STATE"
    private const val COUNTER = "COUNTER"


    fun defaultPreference(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    fun customPreference(context: Context, name: String): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    private inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var SharedPreferences.state
        get() = getString(USER_STATE, "")
        set(value) {
            editMe {
                it.putString(USER_STATE, value)
            }
        }

    var SharedPreferences.counter
        get() = getInt(COUNTER, 0)
        set(value) {
            editMe {
                it.putInt(COUNTER, value)
            }
        }
}
