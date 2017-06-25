package com.alorma.travisapp.data.repos

import io.reactivex.Observable
import javax.inject.Inject

class GetAccountReposRepository @Inject constructor(
        val apiReposDataSource: ApiGetReposDataSource,
        val roomGetReposDataSource: RoomGetReposDataSource) {

    fun getRepos(login: String): Observable<List<TravisRepo>> {

        val room = roomGetReposDataSource.getRepos(ReposSearch(login)).defaultIfEmpty(mutableListOf())
        val api = apiReposDataSource.getRepos(ReposSearch(login))
                .doOnNext({ roomGetReposDataSource.populate(it) }).flatMap { room }

        return room.concatWith(api)
    }
}