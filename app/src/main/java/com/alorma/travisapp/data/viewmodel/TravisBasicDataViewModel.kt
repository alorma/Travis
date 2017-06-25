package com.alorma.travisapp.data.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.alorma.travisapp.data.account.GetAccountDataUseCase
import com.alorma.travisapp.data.account.TravisAccount
import com.alorma.travisapp.data.live.EitherLiveData
import com.alorma.travisapp.data.repos.GetAccountReposUseCase
import com.alorma.travisapp.data.repos.TravisRepo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TravisBasicDataViewModel(app: Application) : AndroidViewModel(app) {

    @Inject
    lateinit var accountDataUseCase: GetAccountDataUseCase

    @Inject
    lateinit var accountRepoUseCase: GetAccountReposUseCase

    val composite: CompositeDisposable = CompositeDisposable()

    var accountData: EitherLiveData<TravisAccount> = EitherLiveData()
    val reposData: EitherLiveData<List<TravisRepo>> = EitherLiveData()

    fun getTravisAccount(): EitherLiveData<TravisAccount> {
        if (accountData.value == null) {
            val disposable = accountDataUseCase.getAccount()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ account -> onAccountLoaded(account) },
                            { t -> accountData.post(t) })

            composite.add(disposable)
        }
        return accountData
    }

    fun getTravisRepos(): EitherLiveData<List<TravisRepo>> {
        return reposData
    }

    fun onAccountLoaded(travisAccount: TravisAccount) {
        accountData.post(travisAccount)

        loadRepos(travisAccount.login)
    }

    private fun loadRepos(login: String) {
        val disposable = accountRepoUseCase.getRepos(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ reposData.post(it) }, { t -> reposData.post(t) })

        composite.add(disposable)
    }

    override fun onCleared() {
        composite.clear()
        super.onCleared()
    }
}
