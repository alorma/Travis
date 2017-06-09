package com.alorma.travisapp.data.network

import com.alorma.travisapp.data.account.TravisAccountsResponse
import com.alorma.travisapp.data.repos.TravisReposResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TravisEndpoints {

    @GET("/accounts")
    fun getAccount(): Single<TravisAccountsResponse>

    @GET("/repos/{search}")
    fun getRepos(@Path("search") searchKey: String): Single<TravisReposResponse>
}