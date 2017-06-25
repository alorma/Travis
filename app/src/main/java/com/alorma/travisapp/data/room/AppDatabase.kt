package com.alorma.travisapp.data.room;

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.alorma.travisapp.data.repos.TravisRepoEntity

@Database(entities = arrayOf(TravisRepoEntity::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun reposDao(): TravisRepoDao
}