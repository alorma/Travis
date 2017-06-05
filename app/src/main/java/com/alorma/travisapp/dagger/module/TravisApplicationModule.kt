package com.alorma.travisapp.dagger.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class TravisApplicationModule(val application: Application) {

    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Provides
    fun providesContext(): Context {
        return application;
    }

}