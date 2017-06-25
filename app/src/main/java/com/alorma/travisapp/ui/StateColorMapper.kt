package com.alorma.travisapp.ui

import android.graphics.Color


interface StateColorMapper {
    fun map(stateColor: StateColor): Int
}


class DummyStateColorMapper : StateColorMapper {
    override fun map(stateColor: StateColor): Int {
        when (stateColor) {
            StateColor.GREEN -> return Color.parseColor("#00FF00")
            StateColor.YELLOW -> return Color.parseColor("#FFFF00")
            StateColor.RED -> return Color.parseColor("#FF0000")
            else -> return Color.parseColor("#666666")
        }
    }

}