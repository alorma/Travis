package com.alorma.travisapp.data.account

import io.reactivex.Single

interface GetAccountDataSource {
    fun getAccount(): Single<TravisAccount>
    fun populate(it: TravisAccount) {

    }
}
