package com.alorma.travisapp.logger

import android.util.Log

class AppLogger {

    fun i(tag: String = "TravisApp", message: String) {
        Log.i(tag, message)
    }

}