package com.alorma.travisapp.data.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.alorma.travisapp.dagger.component.RepoBuildsComponent
import com.alorma.travisapp.data.builds.GetRepoBuildUseCase
import com.alorma.travisapp.data.builds.TravisRepoBuild
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class RepoBuildsDataViewModel(val app: Application)
    : AndroidViewModel(app), RepoBuildsComponent.Injectable {

    override fun inject(component: RepoBuildsComponent) {
        component.inject(this)
    }

    @Inject lateinit var getRepoBuild: GetRepoBuildUseCase
    var travisBuildsLiveData: MutableLiveData<List<TravisRepoBuild>> = MutableLiveData()
    val composite: CompositeDisposable = CompositeDisposable()

    fun loadBuilds(slug: String): LiveData<List<TravisRepoBuild>> {
        if (travisBuildsLiveData.value == null) {
            travisBuildsLiveData = MutableLiveData()
            getData(slug)
        }

        return travisBuildsLiveData
    }

    private fun getData(slug: String) {
        val disposable = getRepoBuild.loadRepoBuilds(slug)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t -> onBuildsLoaded(t) }, { t -> onError(t) })

        composite.add(disposable)
    }

    private fun onBuildsLoaded(t: List<TravisRepoBuild>) {
        travisBuildsLiveData.postValue(t)
    }

    private fun onError(t: Throwable?) {

    }

    override fun onCleared() {
        composite.clear()
        super.onCleared()
    }
}