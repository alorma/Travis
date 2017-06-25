package com.alorma.travisapp.data.builds

import com.alorma.travisapp.ui.BuildStateColorMapper
import com.alorma.travisapp.ui.StateColor

data class TravisRepoBuild(val id: Long,
                           val build: ApiTravisRepoBuild,
                           val commit: TravisCommit?)


fun TravisRepoBuild.stateColor(functionToGetColor: (stateColor: StateColor) -> Int,
                               applyColor: (color: Int) -> Unit) {
    val stateColor = BuildStateColorMapper.fromState(build.state)
    val color = functionToGetColor.invoke(stateColor)
    applyColor.invoke(color)
}

fun TravisRepoBuild.pullRequest(functionToShow: (pullRequestNumber: Long, pullRequestTitle: String) -> Unit,
                                functionToHide: () -> Unit) {

    if (build.isPullRequest) {
        functionToShow.invoke(build.pullRequestNumber, build.pullRequestTitle)
    } else {
        functionToHide.invoke()
    }

}