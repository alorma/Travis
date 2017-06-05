package com.alorma.travisapp.dagger.module

import com.alorma.travisapp.data.account.AccountTokenProvider
import com.alorma.travisapp.data.account.DummyAccountTokenProvider
import dagger.Module
import dagger.Provides

@Module
class AccountModule {

    @Provides
    fun providesAccountTokenProvider(): AccountTokenProvider {
        return DummyAccountTokenProvider()
    }

}