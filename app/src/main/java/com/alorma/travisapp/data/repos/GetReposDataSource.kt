package com.alorma.travisapp.data.repos

import com.alorma.travisapp.data.network.TravisEndpoints
import io.reactivex.Observable
import javax.inject.Inject

class GetReposDataSource @Inject constructor(val travisEndpoints: TravisEndpoints) {

    fun getRepos(reposSearch: ReposSearch): Observable<TravisRepo> {
        return travisEndpoints.getRepos(reposSearch.key).flatMapObservable { Observable.fromIterable(it.repos) }
    }

}