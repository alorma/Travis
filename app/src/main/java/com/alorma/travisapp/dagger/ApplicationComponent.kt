package com.alorma.travisapp.dagger

import com.alorma.travisapp.ui.activity.MainActivity
import com.alorma.travisapp.TravisApplication
import com.alorma.travisapp.dagger.module.LoggerModule
import com.alorma.travisapp.dagger.module.TravisApplicationModule
import com.alorma.travisapp.logger.AppLogger
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(TravisApplicationModule::class, LoggerModule::class))
interface ApplicationComponent {
    fun inject(application: TravisApplication)

    fun inject(mainActivity: MainActivity)

    fun getAppLogger(): AppLogger
}