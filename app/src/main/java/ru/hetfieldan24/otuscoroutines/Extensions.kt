package ru.hetfieldan24.otuscoroutines

import android.util.Log

fun Any.myLog(message: String?)
{
    val className = this::class.java.simpleName
    Log.e("MYLOG from $className", message)
}

const val TEST = "TEST"