package com.alorma.travisapp.data.repos;

import com.google.gson.annotations.SerializedName

class TravisRepo(val id: Long,
                 val slug: String,
                 val description: String?,
                 val active: Boolean,
                 @SerializedName("last_build_number") val lastBuildNumber: String?,
                 @SerializedName("last_build_finished_at") val lastBuildFinished: String?,
                 @SerializedName("last_build_state") val lastBuildState: String)