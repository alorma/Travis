package com.alorma.travisapp.data.builds

import io.reactivex.Single
import javax.inject.Inject

class GetRepoBuildUseCase @Inject constructor(val getRepoBuildsDataSource: GetRepoBuildsDataSource) {

    fun loadRepoBuilds(slug: String): Single<MutableList<TravisRepoBuild>> {
        return getRepoBuildsDataSource.loadRepoBuilds(slug).toList()
    }
}
