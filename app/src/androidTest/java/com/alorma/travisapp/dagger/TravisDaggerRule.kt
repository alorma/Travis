package com.alorma.travisapp.dagger

import android.support.test.InstrumentationRegistry
import com.alorma.travisapp.TravisApplication
import com.alorma.travisapp.dagger.component.ApplicationComponent
import com.alorma.travisapp.dagger.module.NetworkModule
import it.cosenonjaviste.daggermock.DaggerMockRule

class TravisDaggerRule(networkModule: NetworkModule)
    : DaggerMockRule<ApplicationComponent>(ApplicationComponent::class.java, networkModule) {

    fun getApp(): TravisApplication {
        return InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TravisApplication
    }
}