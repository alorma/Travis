package com.alorma.travisapp.ui.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import com.alorma.travisapp.R
import com.alorma.travisapp.data.repos.TravisRepo
import com.alorma.travisapp.data.extension.gone
import com.alorma.travisapp.data.extension.visible
import kotlinx.android.synthetic.main.row_repo.view.*

class ReposAdapter(val inflater: LayoutInflater) : RecyclerView.Adapter<ReposAdapter.Holder>() {

    var callback: Callback? = null
        set
    var repos: MutableList<TravisRepo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        return Holder(inflater.inflate(R.layout.row_repo, parent, false))
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        val repo = repos[position]
        holder?.populate(repo, callback)
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textRepoName: TextView? = itemView.buildInfo
        val textRepoLastBuildState: TextView? = itemView.textRepoLastBuildState
        val textRepoIsActive: Switch? = itemView.textRepoIsActive

        fun populate(repo: TravisRepo, callback: Callback?) {
            textRepoName?.text = repo.slug
            textRepoIsActive?.isChecked = repo.active
            if (repo.active) {
                textRepoLastBuildState?.text = repo.lastBuildState
                textRepoLastBuildState?.visible()
            } else {
                textRepoLastBuildState?.text = ""
                textRepoLastBuildState?.gone()
            }

            itemView.setOnClickListener({
                callback?.repoSelected(repo)
            })
        }
    }

    fun addAll(repos: List<TravisRepo>?) {
        if (repos != null) {
            val calculateDiff = DiffUtil.calculateDiff(ReposDiffUtils(this.repos, repos), true)
            this.repos = repos.toMutableList()
            calculateDiff.dispatchUpdatesTo(this)
        }
    }

    interface Callback {
        fun repoSelected(travisRepo: TravisRepo)
        fun repoActiveStateChanged(travisRepos: TravisRepo, active: Boolean)
    }
}
