package com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass

import java.lang.Exception

class Result<T> {
    var isSuccess : Boolean = false
    var isFailed : Boolean = false
    var result : T? = null
    var exception : Exception? = null

    fun success (value:T) {
        result = value
    }

    fun failed (e:Exception) {
        exception = e
    }
}