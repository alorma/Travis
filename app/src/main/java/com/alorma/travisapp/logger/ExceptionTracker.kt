package com.alorma.travisapp.logger

import javax.inject.Inject


class ExceptionTracker @Inject constructor() {
    fun track(t: Throwable) {
        t.printStackTrace()
    }
}
