package com.alorma.travisapp.data.network

import com.alorma.travisapp.data.account.TravisAccount
import io.reactivex.Single
import retrofit2.http.GET

interface TravisEndpoints {

    @GET("/accounts")
    fun getAccount(): Single<TravisAccount>
}