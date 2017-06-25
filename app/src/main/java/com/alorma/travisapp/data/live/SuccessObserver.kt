package com.alorma.travisapp.data.live;

interface SuccessObserver<in T> {
    /**
     * Called when the data is changed.
     *
     * @param t The new data
     */
    fun onChanged(t:T)
}