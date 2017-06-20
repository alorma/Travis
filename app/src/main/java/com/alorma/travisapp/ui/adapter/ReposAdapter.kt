package com.alorma.travisapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alorma.travisapp.R
import com.alorma.travisapp.data.repos.TravisRepo
import kotlinx.android.synthetic.main.row_repo.view.*

class ReposAdapter(val inflater: LayoutInflater) : RecyclerView.Adapter<ReposAdapter.Holder>() {

    val repos: MutableList<TravisRepo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        return Holder(inflater.inflate(R.layout.row_repo, parent, false))
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        val repo = repos.get(position)
        holder?.itemView?.textRepoName?.text = repo.slug
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun addAll(repos: Collection<TravisRepo>?) {
        if (repos != null) {
            this.repos.addAll(repos)
            notifyDataSetChanged()
        }
    }
}
