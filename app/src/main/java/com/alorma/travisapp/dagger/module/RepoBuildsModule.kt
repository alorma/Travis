package com.alorma.travisapp.dagger.module

import android.arch.lifecycle.ViewModelProviders
import com.alorma.travisapp.dagger.ActivityScope
import com.alorma.travisapp.data.viewmodel.RepoBuildsDataViewModel
import com.alorma.travisapp.ui.AndroidStateColorMapper
import com.alorma.travisapp.ui.StateColorMapper
import com.alorma.travisapp.ui.activity.RepoBuildActivity
import dagger.Module
import dagger.Provides

@Module
class RepoBuildsModule(val activity: RepoBuildActivity) {

    @ActivityScope
    @Provides
    fun provideViewModel(): RepoBuildsDataViewModel {
        return ViewModelProviders.of(activity).get(RepoBuildsDataViewModel::class.java)
    }

    @ActivityScope
    @Provides
    fun provideStateColorMapper(): StateColorMapper {
        return AndroidStateColorMapper(activity)
    }

}