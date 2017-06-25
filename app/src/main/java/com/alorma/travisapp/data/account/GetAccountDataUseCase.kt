package com.alorma.travisapp.data.account

import io.reactivex.Single
import javax.inject.Inject

open class GetAccountDataUseCase @Inject constructor(
        val accountRepository: GetAccountRepository) {

    open fun getAccount(): Single<TravisAccount> {
        return accountRepository.getAccount()
    }

}
