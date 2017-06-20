package com.alorma.travisapp.data

import android.arch.lifecycle.LiveData
import com.alorma.travisapp.data.account.GetAccountDataUseCase
import com.alorma.travisapp.data.account.TravisAccount
import com.alorma.travisapp.data.live.AccountLiveData
import com.alorma.travisapp.data.repos.GetAccountReposUseCase
import com.alorma.travisapp.data.repos.TravisRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainPresenter @Inject constructor(val accountDataUseCase: GetAccountDataUseCase,
                                        val accountRepoUseCase: GetAccountReposUseCase) {

    var travisAccountLiveData: AccountLiveData? = null

    var screen: Screen? = null
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun start(accountLiveData: AccountLiveData) {
        travisAccountLiveData = accountLiveData
        val disposable = accountDataUseCase.getAccount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onTravisAccount, this::onError)

        compositeDisposable.add(disposable)
    }

    fun onTravisAccount(travisAccount: TravisAccount) {
        travisAccountLiveData?.addValue(travisAccount)
        loadRepos(travisAccount.login)
    }

    fun loadRepos(login: String) {
        val disposable = accountRepoUseCase.getRepos(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t -> onReposLoaded(t) }, { t -> onError(t) })

        compositeDisposable.add(disposable)
    }

    fun onReposLoaded(repos: List<TravisRepo>) {
        screen?.showRepos(repos)
    }

    fun onError(throwable: Throwable) {

    }

    fun stop() {
        compositeDisposable.clear()
        screen = null
    }

    interface Screen {
        fun showRepos(repos: List<TravisRepo>)
    }
}
