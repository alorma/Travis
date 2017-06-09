package com.alorma.travisapp.data.repos

data class ReposSearch(val key: String, val value: Any?) {
    constructor(key: String) : this(key, null)
}