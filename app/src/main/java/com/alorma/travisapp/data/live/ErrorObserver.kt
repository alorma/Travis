package com.alorma.travisapp.data.live

interface ErrorObserver {
    /**
     * Called when error.
     * @param t  The new throwable
     */
    fun onError(t: Throwable)
}
