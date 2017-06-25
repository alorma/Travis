package com.alorma.travisapp.dagger.module

import android.app.Application
import android.arch.persistence.room.Room
import com.alorma.travisapp.data.room.AppDatabase
import com.alorma.travisapp.data.room.TravisRepoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
open class DBModule {

    @Singleton
    @Provides
    fun getAppDataBase(context: Application): AppDatabase
            = Room.databaseBuilder(context, AppDatabase::class.java, "travis-database").build()

    @Provides
    fun reposDao(appDatabase: AppDatabase): TravisRepoDao = appDatabase.reposDao()
}
