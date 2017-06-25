package com.alorma.travisapp.data.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.alorma.travisapp.dagger.component.RepoBuildsComponent
import com.alorma.travisapp.data.builds.GetRepoBuildUseCase
import com.alorma.travisapp.data.builds.TravisRepoBuild
import com.alorma.travisapp.data.live.EitherLiveData
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
    var travisBuildsLiveData: EitherLiveData<List<TravisRepoBuild>> = EitherLiveData()
    val composite: CompositeDisposable = CompositeDisposable()

    fun loadBuilds(slug: String): EitherLiveData<List<TravisRepoBuild>> {
        if (travisBuildsLiveData.value == null) {
            getData(slug)
        }

        return travisBuildsLiveData
    }

    private fun getData(slug: String) {
        val disposable = getRepoBuild.loadRepoBuilds(slug)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ travisBuildsLiveData.post(it) }, { travisBuildsLiveData.post(it) })

        composite.add(disposable)
    }

    override fun onCleared() {
        composite.clear()
        super.onCleared()
    }
}