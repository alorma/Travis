package com.alorma.travisapp.data.account

import com.alorma.travisapp.data.network.TravisEndpoints
import io.reactivex.Single
import javax.inject.Inject

class GetAccountDataSource @Inject constructor(val travisEndpoints: TravisEndpoints) {

    fun getAccount(): Single<TravisAccount> {
        return travisEndpoints.getAccount()
    }

}
