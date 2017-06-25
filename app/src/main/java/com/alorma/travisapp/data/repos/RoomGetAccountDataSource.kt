package com.alorma.travisapp.data.repos

import com.alorma.travisapp.data.room.TravisRepoDao
import io.reactivex.Observable
import javax.inject.Inject

class RoomGetReposDataSource @Inject constructor(val reposDao: TravisRepoDao,
                                                 val reposMapper: RepoMapper)
    : GetReposDataSource {

    override fun getRepos(reposSearch: ReposSearch): Observable<List<TravisRepo>> {
        return Observable.fromCallable({
            reposDao.getAll().map { reposMapper.toMap(it) }
        })
    }

    override fun populate(list: List<TravisRepo>) {
        for (travisRepoEntity in list.map { reposMapper.toEntity(it) }) {
            reposDao.insert(travisRepoEntity)
        }
    }
}
