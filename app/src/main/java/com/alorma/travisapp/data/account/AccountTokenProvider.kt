package com.alorma.travisapp.data.account

interface AccountTokenProvider {
    fun getToken(): String?
}
