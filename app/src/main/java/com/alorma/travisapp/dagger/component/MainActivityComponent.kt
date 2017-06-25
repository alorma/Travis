package com.alorma.travisapp.dagger.component

import com.alorma.travisapp.dagger.ActivityScope
import com.alorma.travisapp.data.viewmodel.TravisBasicDataViewModel
import com.alorma.travisapp.ui.activity.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface MainActivityComponent {
    fun inject(viewModel: TravisBasicDataViewModel)
    fun inject(viewModel: MainActivity)
}