package com.alorma.travisapp.data.repos

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "repos")
class TravisRepoEntity(@ColumnInfo(name = "id") @PrimaryKey() var id: Long,
                       @ColumnInfo(name = "slug") var slug: String,
                       @ColumnInfo(name = "description") var description: String? = null,
                       @ColumnInfo(name = "active") var active: Boolean,
                       @ColumnInfo(name = "last_build_state") var lastBuildState: String) {
}
