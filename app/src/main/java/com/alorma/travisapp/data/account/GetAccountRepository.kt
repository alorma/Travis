package com.alorma.travisapp.data.account

import io.reactivex.Single
import javax.inject.Inject

class GetAccountRepository  @Inject constructor(
        val apiAccountDataSource: ApiGetAccountDataSource,
        val cacheAccountDataSource: CacheGetAccountDataSource) {

    fun getAccount(): Single<TravisAccount> {
        return cacheAccountDataSource.getAccount()
                .onErrorResumeNext { apiAccountDataSource.getAccount()
                        .doOnSuccess({ cacheAccountDataSource.populate(it) }) }
    }

}
