package com.alorma.travisapp

import android.app.Application
import com.alorma.travisapp.dagger.ApplicationComponent

class TravisApplication : Application() {

    companion object {
        lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        //graph = DaggerApplicationComponent.builder().androidModule(LoggerModule()).build()
        graph.inject(this)
    }

    fun getComponent(): ApplicationComponent {
        return graph
    }
}