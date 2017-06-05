package com.alorma.travisapp.dagger.component

import com.alorma.travisapp.TravisApplication
import com.alorma.travisapp.dagger.module.AccountModule
import com.alorma.travisapp.dagger.module.LoggerModule
import com.alorma.travisapp.dagger.module.NetworkModule
import com.alorma.travisapp.dagger.module.TravisApplicationModule
import com.alorma.travisapp.logger.AppLogger
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(TravisApplicationModule::class, LoggerModule::class, AccountModule::class, NetworkModule::class))
interface ApplicationComponent {
    fun inject(application: TravisApplication)

    fun getAppLogger(): AppLogger
}