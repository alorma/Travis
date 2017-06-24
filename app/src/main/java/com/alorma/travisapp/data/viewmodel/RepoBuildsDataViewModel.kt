package com.alorma.travisapp.data.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.alorma.travisapp.data.builds.TravisRepoBuild


class RepoBuildsDataViewModel(app: Application) : AndroidViewModel(app) {

    var travisBuildsLiveData: MutableLiveData<List<TravisRepoBuild>> = MutableLiveData()

    fun loadBuild(repoId: Int): LiveData<List<TravisRepoBuild>> {
        if (travisBuildsLiveData.value == null) {
            travisBuildsLiveData = MutableLiveData()
            getFakeData(repoId)
        }

        return travisBuildsLiveData
    }

    private fun getFakeData(repoId: Int) {
        val mutableList: MutableList<TravisRepoBuild> = mutableListOf()

        mutableList.add(TravisRepoBuild(121, "passed", 121))
        mutableList.add(TravisRepoBuild(122, "error", 122))
        mutableList.add(TravisRepoBuild(123, "building", 123))

        travisBuildsLiveData.postValue(mutableList)
    }

}
