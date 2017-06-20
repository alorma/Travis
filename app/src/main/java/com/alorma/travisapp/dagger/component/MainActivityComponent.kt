package com.alorma.travisapp.dagger.component

import com.alorma.travisapp.dagger.ActivityScope
import com.alorma.travisapp.data.viewmodel.TravisBasicDataViewModel
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface MainActivityComponent {
    fun inject(viewModel: TravisBasicDataViewModel)
}