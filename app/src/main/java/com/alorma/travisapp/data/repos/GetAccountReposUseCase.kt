package com.alorma.travisapp.data.repos

import io.reactivex.Observable
import javax.inject.Inject

open class GetAccountReposUseCase @Inject constructor(
        val repository: GetAccountReposRepository) {

    fun getRepos(login: String): Observable<List<TravisRepo>> {
        return repository.getRepos(login).map { it.sortedWith(compareByDescending { it.active }) }
    }
}
