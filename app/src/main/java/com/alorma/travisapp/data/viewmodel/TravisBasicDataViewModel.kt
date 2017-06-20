package com.alorma.travisapp.data.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.alorma.travisapp.data.account.GetAccountDataUseCase
import com.alorma.travisapp.data.account.TravisAccount
import com.alorma.travisapp.data.repos.GetAccountReposUseCase
import com.alorma.travisapp.data.repos.TravisRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TravisBasicDataViewModel : ViewModel() {

    @Inject
    lateinit var accountDataUseCase: GetAccountDataUseCase

    @Inject
    lateinit var accountRepoUseCase: GetAccountReposUseCase

    val composite: CompositeDisposable = CompositeDisposable()

    var accountData: MutableLiveData<TravisAccount> = MutableLiveData()
    var errorData: MutableLiveData<Throwable> = MutableLiveData()
    val reposData: MutableLiveData<List<TravisRepo>> = MutableLiveData()

    fun getTravisAccount(): LiveData<TravisAccount> {
        if (accountData.value == null) {
            val disposable = accountDataUseCase.getAccount()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ account -> onAccountLoaded(account) },
                            { t: Throwable? -> onError(t) })

            composite.add(disposable)
        }
        return accountData;
    }

    fun getTravisRepos(): LiveData<List<TravisRepo>> {
        return reposData
    }

    fun getErrorData(): LiveData<Throwable> {
        return errorData
    }

    fun onAccountLoaded(travisAccount: TravisAccount) {
        accountData.postValue(travisAccount)

        loadRepos(travisAccount.login)
    }

    private fun loadRepos(login: String) {
        val disposable = accountRepoUseCase.getRepos(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t -> onReposLoaded(t) }, { t -> onError(t) })

        composite.add(disposable)
    }

    private fun onReposLoaded(list: List<TravisRepo>) {
        reposData.postValue(list)
    }

    private fun onError(t: Throwable?) {
        errorData.postValue(t)
    }

    override fun onCleared() {
        composite.clear()
        super.onCleared()
    }
}
