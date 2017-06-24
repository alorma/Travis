package com.alorma.travisapp.data.repos;

import com.google.gson.annotations.SerializedName

class TravisRepo(val id: Int,
                 val slug: String,
                 val description: String,
                 val active: Boolean,
                 @SerializedName("last_build_number") val lastBuildNumber: String,
                 @SerializedName("last_build_state") val lastBuildState: String)
    /*
    "id": 82,
    "slug": "sinatra/sinatra",
    "description": "Classy web-development dressed in a DSL",
    "last_build_id": 23436881,
    "last_build_number": "792",
    "last_build_state": "passed",
    "last_build_duration": 2542,
    "last_build_started_at": "2014-04-21T15:27:14Z",
    "last_build_finished_at": "2014-04-21T15:40:04Z",
    "active": "true"
     */
