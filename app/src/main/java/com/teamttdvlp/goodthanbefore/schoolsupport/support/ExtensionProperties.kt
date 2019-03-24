package com.teamttdvlp.goodthanbefore.schoolsupport.support

import android.util.Log

fun Any.logError(mess_name : String, message : Any) {
    Log.e(mess_name, message.toString())
}