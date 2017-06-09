package com.alorma.travisapp.data.account

import io.reactivex.Single
import javax.inject.Inject

open class GetAccountDataUseCase @Inject constructor(
        val accountDataSource: GetAccountDataSource) {

    open fun getAccount(): Single<TravisAccount> {
        return accountDataSource.getAccount()
    }

}
