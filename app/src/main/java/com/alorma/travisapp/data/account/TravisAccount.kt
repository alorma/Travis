package com.alorma.travisapp.data.account

import com.google.gson.annotations.SerializedName

data class TravisAccount(val name: String,
                         val login: String,
                         val type: String,
                         @SerializedName("repos_count") val reposCount: Int)