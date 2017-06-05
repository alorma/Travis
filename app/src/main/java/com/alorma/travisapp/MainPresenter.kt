package com.alorma.travisapp

import com.alorma.travisapp.data.account.AccountAgent
import javax.inject.Inject

class MainPresenter @Inject constructor(val accountAgent: AccountAgent) {

    lateinit var view: View

    fun start() {
        if (accountAgent.accountExist()) {
            view.showAccountName(accountAgent.getAccount()?.name)
        } else {
            view.showNoAccount()
        }
    }


    interface View {
        fun showAccountName(account: String?)
        fun showNoAccount()
    }
}
