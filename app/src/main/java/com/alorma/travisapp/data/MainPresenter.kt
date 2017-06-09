package com.alorma.travisapp.data

import com.alorma.travisapp.data.account.GetAccountDataUseCase
import com.alorma.travisapp.data.account.TravisAccount
import com.alorma.travisapp.data.repos.GetAccountReposUseCase
import com.alorma.travisapp.data.repos.TravisRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainPresenter @Inject constructor(val accountDataUseCase: GetAccountDataUseCase,
                                        val accountRepoUseCase: GetAccountReposUseCase) {

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
        screen?.showAccount(travisAccount.login, travisAccount.reposCount)

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
        fun showAccount(login: String, reposNumber: Int)
        fun showRepos(repos: List<TravisRepo>)
    }
}
