package com.alorma.travisapp.data.repos;

import com.alorma.travisapp.data.network.TravisEndpoints
import io.reactivex.Observable
import javax.inject.Inject

class ApiGetReposDataSource @Inject constructor(val travisEndpoints: TravisEndpoints) : GetReposDataSource {

    override fun getRepos(reposSearch: ReposSearch): Observable<List<TravisRepo>> {
        return travisEndpoints.getRepos(reposSearch.key).map { it.repos }
                .onErrorResumeNext { _: Throwable? -> Observable.empty() }
    }

}