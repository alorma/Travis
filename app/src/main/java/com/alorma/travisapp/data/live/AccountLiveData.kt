package com.alorma.travisapp.data.live

import android.arch.lifecycle.LiveData
import com.alorma.travisapp.data.account.TravisAccount

class AccountLiveData : LiveData<TravisAccount>() {
    fun addValue(travisAccount: TravisAccount) {
        value = travisAccount
    }
}