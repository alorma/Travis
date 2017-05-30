package com.alorma.travisapp.dagger.component

import com.alorma.travisapp.dagger.PerActivity
import com.alorma.travisapp.ui.activity.MainActivity
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface MainActivityComponent {
    fun inject(mainActivity: MainActivity)
}