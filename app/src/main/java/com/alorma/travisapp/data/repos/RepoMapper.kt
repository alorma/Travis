package com.alorma.travisapp.data.repos

import javax.inject.Inject

class RepoMapper @Inject constructor() {
    fun toEntity(repo: TravisRepo): TravisRepoEntity
            = TravisRepoEntity(repo.id, repo.slug, repo.description, repo.active, repo.lastBuildState)

    fun toMap(repo: TravisRepoEntity): TravisRepo
            = TravisRepo(repo.id, repo.slug, repo.description, repo.active, null, null, repo.lastBuildState)
}
