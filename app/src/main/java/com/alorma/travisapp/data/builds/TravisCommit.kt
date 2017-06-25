package com.alorma.travisapp.data.builds

import com.google.gson.annotations.SerializedName

data class TravisCommit(val id: Long,
                        val sha: String,
                        val branch: String,
                        val message: String,
                        @SerializedName("author_name") val authorName: String,
                        @SerializedName("author_email") val authorEmail: String)