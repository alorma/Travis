package com.alorma.travisapp.data.account

open class TravisAccount(val name: String, val login: String) {

    open fun name(): String {
        return name
    }
}
