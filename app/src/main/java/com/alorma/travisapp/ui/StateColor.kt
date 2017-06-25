package com.alorma.travisapp.ui

import com.alorma.travisapp.data.builds.BuildState.CREATED
import com.alorma.travisapp.data.builds.BuildState.ERRORED
import com.alorma.travisapp.data.builds.BuildState.FAILED
import com.alorma.travisapp.data.builds.BuildState.PASSED
import com.alorma.travisapp.data.builds.BuildState.QUEUED
import com.alorma.travisapp.data.builds.BuildState.STARTED

enum class StateColor {
    GREEN,
    YELLOW,
    RED,
    GRAY;
}

object BuildStateColorMapper {
    fun fromState(state: String): StateColor {
        return when (state.toLowerCase()) {
            FAILED -> StateColor.RED
            ERRORED -> StateColor.RED
            CREATED -> StateColor.YELLOW
            QUEUED -> StateColor.YELLOW
            STARTED -> StateColor.YELLOW
            PASSED -> StateColor.GREEN
            else -> StateColor.GRAY
        }
    }
}