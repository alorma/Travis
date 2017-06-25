package com.alorma.travisapp.data.builds

data class TravisRepoBuild(val id: Long,
                           val build: ApiTravisRepoBuild,
                           val commit: TravisCommit?)