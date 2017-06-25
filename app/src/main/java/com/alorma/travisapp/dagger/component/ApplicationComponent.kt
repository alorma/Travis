package com.alorma.travisapp.dagger.component

import android.content.Context
import com.alorma.travisapp.TravisApplication
import com.alorma.travisapp.dagger.module.DBModule
import com.alorma.travisapp.dagger.module.LoggerModule
import com.alorma.travisapp.dagger.module.NetworkModule
import com.alorma.travisapp.dagger.module.TravisApplicationModule
import com.alorma.travisapp.data.network.TravisEndpoints
import com.alorma.travisapp.data.room.TravisRepoDao
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(TravisApplicationModule::class,
        LoggerModule::class,
        NetworkModule::class,
        DBModule::class))
interface ApplicationComponent {
    fun inject(application: TravisApplication)

    fun getContext(): Context

    fun getTravisEndpoints(): TravisEndpoints

    fun getTravisReposDao(): TravisRepoDao
}