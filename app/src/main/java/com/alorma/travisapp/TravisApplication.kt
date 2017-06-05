package com.alorma.travisapp

import android.app.Application
import com.alorma.travisapp.dagger.component.ApplicationComponent
import com.alorma.travisapp.dagger.component.DaggerApplicationComponent
import com.alorma.travisapp.dagger.module.LoggerModule
import com.alorma.travisapp.dagger.module.NetworkModule
import com.alorma.travisapp.dagger.module.TravisApplicationModule

class TravisApplication : Application() {

    companion object {
        @JvmStatic lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        graph = DaggerApplicationComponent.builder()
                .travisApplicationModule(TravisApplicationModule(this))
                .loggerModule(LoggerModule())
                .networkModule(NetworkModule())
                .build()
        graph.inject(this)
    }

    fun getComponent(): ApplicationComponent {
        return graph
    }

    fun setComponent(component: ApplicationComponent) {
        graph = component
    }
}