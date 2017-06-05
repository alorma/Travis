package com.alorma.travisapp.data

import com.alorma.travisapp.data.account.GetAccountDataUseCase
import com.alorma.travisapp.data.account.TravisAccount
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainPresenter @Inject constructor(val accountDataUseCase: GetAccountDataUseCase) {

    var screen: Screen? = null
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun start() {
        val disposable = accountDataUseCase.getAccount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onTravisAccount, this::onError)

        compositeDisposable.add(disposable)
    }

    fun onTravisAccount(travisAccount: TravisAccount) {
        screen?.showAccount(travisAccount)
    }

    fun onError(throwable: Throwable) {

    }

    fun stop() {
        compositeDisposable.clear()
        screen = null
    }

    interface Screen {
        fun showAccount(travisAccount: TravisAccount)
    }
}
