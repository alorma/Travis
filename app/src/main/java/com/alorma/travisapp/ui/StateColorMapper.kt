package com.alorma.travisapp.ui

import android.graphics.Color


interface StateColorMapper {
    fun map(stateColor: StateColor): Int
}


class DummyStateColorMapper : StateColorMapper {
    override fun map(stateColor: StateColor): Int {
        when (stateColor) {
            StateColor.GREEN -> return Color.parseColor("#B2DFDB")
            StateColor.YELLOW -> return Color.parseColor("#F0F4C3")
            StateColor.RED -> return Color.parseColor("#FFCDD2")
            else -> return Color.parseColor("#E0E0E0")
        }
    }

}