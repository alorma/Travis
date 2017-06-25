package com.alorma.travisapp.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import com.alorma.travisapp.R

interface StateColorMapper {
    fun map(stateColor: StateColor): Int
}

class AndroidStateColorMapper(val context: Context) : StateColorMapper {
    override fun map(stateColor: StateColor): Int {
        when (stateColor) {
            StateColor.GREEN -> return ContextCompat.getColor(context, R.color.travis_build_green)
            StateColor.YELLOW -> return ContextCompat.getColor(context, R.color.travis_build_yellow)
            StateColor.RED -> return ContextCompat.getColor(context, R.color.travis_build_red)
            else -> return ContextCompat.getColor(context, R.color.travis_build_gray)
        }
    }
}