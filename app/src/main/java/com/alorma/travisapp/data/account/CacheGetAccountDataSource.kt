package com.alorma.travisapp.data.account

import android.content.Context
import android.preference.PreferenceManager
import io.reactivex.Single
import javax.inject.Inject

class CacheGetAccountDataSource @Inject constructor(val context: Context) : GetAccountDataSource {

    override fun getAccount(): Single<TravisAccount> {
        return Single.fromCallable({
            val defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            if (defaultSharedPreferences.contains("LOGIN")) {
                val name = defaultSharedPreferences.getString("NAME", null)
                val login = defaultSharedPreferences.getString("LOGIN", null)
                val count = defaultSharedPreferences.getInt("COUNT", 0)
                TravisAccount(name, login, "User", count)
            } else {
                throw NoAccountException()
            }
        })
    }

    override fun populate(it: TravisAccount) {
        val defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = defaultSharedPreferences.edit()
        editor.putString("NAME", it.name)
        editor.putString("LOGIN", it.login)
        editor.putInt("COUNT", it.reposCount)
        editor.apply()
    }
}

