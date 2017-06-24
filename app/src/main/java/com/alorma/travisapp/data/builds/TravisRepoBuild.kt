package com.alorma.travisapp.data.builds

import com.google.gson.annotations.SerializedName

data class TravisRepoBuild(val id: Long,
                           val state: String,
                           val number: Long,
                           @SerializedName("job_ids") var jobs: Array<Long> = arrayOf())


/*
"commit_id": 6534711,
"config": { },
"duration": 2648,
"finished_at": "2014-04-08T19:52:56Z",
"id": 22555277,
"job_ids": [22555278, 22555279, 22555280, 22555281],
"number": "784",
"pull_request": true,
"pull_request_number": "1912",
"pull_request_title": "Example PR",
"repository_id": 82,
"started_at": "2014-04-08T19:37:44Z",
"state": "failed"
 */

