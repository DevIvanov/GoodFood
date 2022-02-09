package com.juniorteam.football.util.view


import android.content.Context
import android.widget.Toast

fun Context.toast(messageString: Int) {

    val toast = Toast.makeText(this, messageString, Toast.LENGTH_SHORT)

    toast.show()
}