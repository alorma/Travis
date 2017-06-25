package com.alorma.travisapp.ui.adapter

import android.support.v7.util.DiffUtil
import com.alorma.travisapp.data.repos.TravisRepo

class ReposDiffUtils(val oldRepos: List<TravisRepo>, val newRepos: List<TravisRepo>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldRepos[oldItemPosition].id == newRepos[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldRepos.size
    }

    override fun getNewListSize(): Int {
        return newRepos.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldRepos[oldItemPosition].slug == newRepos[newItemPosition].slug
                && oldRepos[oldItemPosition].active == newRepos[newItemPosition].active
    }
}