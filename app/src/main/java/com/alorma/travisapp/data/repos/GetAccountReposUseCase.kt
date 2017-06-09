package com.alorma.travisapp.data.repos

import io.reactivex.Single
import javax.inject.Inject

open class GetAccountReposUseCase @Inject constructor(
        val getReposDataSource: GetReposDataSource) {

    fun getRepos(login: String): Single<MutableList<TravisRepo>> {
        return getReposDataSource.getRepos(ReposSearch(login)).toList()
    }

}
