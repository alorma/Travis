package com.alorma.travisapp.dagger

import android.app.Application
import android.support.test.InstrumentationRegistry
import com.alorma.travisapp.TravisApplication
import com.alorma.travisapp.dagger.component.ApplicationComponent
import com.alorma.travisapp.dagger.module.NetworkModule
import com.alorma.travisapp.dagger.module.TravisApplicationModule
import it.cosenonjaviste.daggermock.DaggerMockRule

class TravisDaggerRule(application: Application, networkModule: NetworkModule)
    : DaggerMockRule<ApplicationComponent>(ApplicationComponent::class.java
        , TravisApplicationModule(application)
        , networkModule) {

    init {
        set({ c -> getApp().setComponent(c) })
    }

    fun getApp(): TravisApplication {
        return InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TravisApplication
    }
}