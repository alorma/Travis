package com.alorma.travisapp.dagger.component

import com.alorma.travisapp.dagger.ActivityScope
import com.alorma.travisapp.dagger.module.RepoBuildsModule
import com.alorma.travisapp.data.viewmodel.RepoBuildsDataViewModel
import com.alorma.travisapp.ui.activity.RepoBuildActivity
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(RepoBuildsModule::class))
interface RepoBuildsComponent {
    fun inject(viewModel: RepoBuildsDataViewModel)
    fun inject(activity: RepoBuildActivity)

    interface Injectable {
        fun inject(component: RepoBuildsComponent)
    }
}