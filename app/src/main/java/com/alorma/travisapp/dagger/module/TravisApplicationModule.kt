package com.alorma.travisapp.dagger.module

import android.app.Application
import android.content.Context
import com.alorma.travisapp.data.account.AccountTokenProvider
import com.alorma.travisapp.data.account.DummyAccountTokenProvider
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
        return application
    }

    @Provides
    fun providesAccountTokenProvider(): AccountTokenProvider {
        return DummyAccountTokenProvider()
    }

}