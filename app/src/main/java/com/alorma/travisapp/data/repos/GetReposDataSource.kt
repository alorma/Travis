package com.alorma.travisapp.data.repos

import io.reactivex.Observable

interface GetReposDataSource {
    fun getRepos(reposSearch: ReposSearch): Observable<List<TravisRepo>>
    fun populate(list: List<TravisRepo>) {

    }
}