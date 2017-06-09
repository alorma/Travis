package com.alorma.travisapp.dagger.component

import com.alorma.travisapp.dagger.ActivityScope
import com.alorma.travisapp.dagger.module.MainActivityModule
import com.alorma.travisapp.ui.activity.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {
    fun inject(mainActivity: MainActivity)
}