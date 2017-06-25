package com.alorma.travisapp.data.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.alorma.travisapp.data.repos.TravisRepoEntity

@Dao
interface TravisRepoDao {
    @Query("SELECT * FROM repos")
    fun getAll(): List<TravisRepoEntity>

    @Insert(onConflict = REPLACE)
    fun insert(repoEntity: TravisRepoEntity)
}
