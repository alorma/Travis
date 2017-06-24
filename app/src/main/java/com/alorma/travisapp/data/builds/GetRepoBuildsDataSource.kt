package com.alorma.travisapp.data.builds

import com.alorma.travisapp.data.network.TravisEndpoints
import io.reactivex.Observable
import javax.inject.Inject

class GetRepoBuildsDataSource @Inject constructor(val travisEndpoints: TravisEndpoints) {

    fun loadRepoBuilds(slug: String): Observable<TravisRepoBuild> {
        return travisEndpoints.getRepoBuilds(slug).flatMapObservable { Observable.fromIterable(it.builds) }
    }
}