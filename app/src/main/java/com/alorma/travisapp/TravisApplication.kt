package com.alorma.travisapp

import android.app.Application
import com.alorma.travisapp.dagger.ApplicationComponent
import com.alorma.travisapp.dagger.DaggerApplicationComponent
import com.alorma.travisapp.dagger.module.LoggerModule
import com.alorma.travisapp.dagger.module.TravisApplicationModule
import com.alorma.travisapp.logger.AppLogger
import javax.inject.Inject

class TravisApplication : Application() {

    companion object {
        @JvmStatic lateinit var graph: ApplicationComponent
    }

    @Inject
    lateinit var appLogger: AppLogger

    override fun onCreate() {
        super.onCreate()
        graph = DaggerApplicationComponent.builder().travisApplicationModule(TravisApplicationModule(this))
                .loggerModule(LoggerModule()).build()
        graph.inject(this)

        appLogger.i(message = "onCreate()")
    }

    fun getComponent(): ApplicationComponent {
        return graph
    }
}