package com.alorma.travisapp

import android.app.Application
import com.alorma.travisapp.dagger.component.ApplicationComponent
import com.alorma.travisapp.dagger.component.DaggerApplicationComponent
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
        initDagger()

        appLogger.i(message = "onCreate()")
    }

    private fun initDagger() {
        graph = DaggerApplicationComponent.builder().travisApplicationModule(TravisApplicationModule(this))
                .loggerModule(LoggerModule()).build()
        graph.inject(this)
    }

    fun getComponent(): ApplicationComponent {
        return graph
    }

    fun setComponent(component: ApplicationComponent) {
        graph = component
    }
}