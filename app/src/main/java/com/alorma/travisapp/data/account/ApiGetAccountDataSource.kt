package com.alorma.travisapp.data.account

import com.alorma.travisapp.data.network.TravisEndpoints
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class ApiGetAccountDataSource @Inject constructor(val travisEndpoints: TravisEndpoints)
    : GetAccountDataSource {

    override fun getAccount(): Single<TravisAccount> {
        return travisEndpoints.getAccount()
                .map { response -> response.accounts }
                .flatMapObservable { Observable.fromIterable(it) }
                .filter({ t -> t.type == "user" })
                .singleElement().toSingle()
                .onErrorResumeNext(Single.error(NoAccountException()))
    }
}
