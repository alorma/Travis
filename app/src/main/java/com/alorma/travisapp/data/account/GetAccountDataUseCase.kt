package com.alorma.travisapp.data.account

import io.reactivex.Single
import javax.inject.Inject

class GetAccountDataUseCase @Inject constructor(
        val accountDataSource: GetAccountDataSource) {

    fun getAccount(): Single<TravisAccount> {
        return accountDataSource.getAccount()
    }

}
