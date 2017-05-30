package com.alorma.travisapp.dagger.module

import com.alorma.travisapp.data.account.AccountAgent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AccountModule {

    @Singleton
    @Provides
    fun providesAccountAgent(): AccountAgent {
        return AccountAgent()
    }

}