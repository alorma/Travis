package com.alorma.travisapp.data.account


open class AccountAgent {

    open fun accountExist(): Boolean {
        return false
    }

    open fun getAccount(): TravisAccount? {
        return null
    }

}
