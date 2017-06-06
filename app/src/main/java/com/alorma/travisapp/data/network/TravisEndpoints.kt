package com.alorma.travisapp.data.network

import com.alorma.travisapp.data.account.TravisAccountsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface TravisEndpoints {

    @GET("/accounts")
    fun getAccount(): Single<TravisAccountsResponse>
}