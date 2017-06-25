package com.alorma.travisapp.ui;

import com.alorma.travisapp.data.builds.BuildState;

public enum StateColor {
    GREEN,
    YELLOW,
    RED,
    GRAY;

    public static StateColor fromState(String state) {
        if (BuildState.FAILED.equalsIgnoreCase(state)) {
            return RED;
        } else if (BuildState.ERRORED.equalsIgnoreCase(state)) {
            return RED;
        } else if (BuildState.CREATED.equalsIgnoreCase(state)) {
            return YELLOW;
        } else if (BuildState.QUEUED.equalsIgnoreCase(state)) {
            return YELLOW;
        } else if (BuildState.STARTED.equalsIgnoreCase(state)) {
            return YELLOW;
        } else if (BuildState.PASSED.equalsIgnoreCase(state)) {
            return GREEN;
        }
        return GRAY;
    }
}