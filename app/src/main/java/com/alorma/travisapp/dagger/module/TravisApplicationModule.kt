package com.alorma.travisapp.dagger.module

import android.app.Application
import android.content.Context
import com.alorma.travisapp.data.account.AccountTokenProvider
import com.alorma.travisapp.data.account.DummyAccountTokenProvider
import dagger.Module
import dagger.Provides

@Module
open class TravisApplicationModule(val application: Application) {

    @Provides
    open fun provideApplication(): Application {
        return application
    }

    @Provides
    open fun providesContext(): Context {
        return application
    }

    @Provides
    open fun providesAccountTokenProvider(): AccountTokenProvider {
        return DummyAccountTokenProvider()
    }

}