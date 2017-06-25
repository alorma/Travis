package com.alorma.travisapp.dagger.module

import com.alorma.travisapp.logger.AppLogger
import com.alorma.travisapp.logger.ExceptionTracker
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LoggerModule {

    @Singleton
    @Provides
    fun provideLogger(): AppLogger {
        return AppLogger()
    }

    @Singleton
    @Provides
    fun getExceptionTracker(): ExceptionTracker {
        return ExceptionTracker()
    }

}