package com.alorma.travisapp.ui.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.alorma.travisapp.R
import com.alorma.travisapp.data.builds.ApiTravisRepoBuild
import com.alorma.travisapp.data.builds.TravisRepoBuild
import com.alorma.travisapp.data.builds.pullRequest
import com.alorma.travisapp.data.builds.stateColor
import com.alorma.travisapp.data.extension.gone
import com.alorma.travisapp.data.extension.visible
import com.alorma.travisapp.ui.StateColorMapper
import kotlinx.android.synthetic.main.row_repo_build.view.*

class RepoBuildsAdapter(val inflater: LayoutInflater, val stateColorMapper: StateColorMapper)
    : RecyclerView.Adapter<RepoBuildsAdapter.Holder>() {

    var callback: Callback? = null
        set

    val builds: MutableList<TravisRepoBuild> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(inflater.inflate(R.layout.row_repo_build, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val repoBuild = builds[position]
        holder.populate(repoBuild, callback, stateColorMapper)
    }

    override fun getItemCount(): Int {
        return builds.size
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: CardView = itemView.repoBuildCard
        val buildInfo: TextView = itemView.buildInfo
        val layoutPrInfo: View = itemView.layoutPrInfo
        val prIcon: ImageView = itemView.prIcon
        val prInfo: TextView = itemView.prInfo

        fun populate(repoBuild: TravisRepoBuild, callback: Callback?, stateColorMapper: StateColorMapper) {
            val commit = repoBuild.commit
            val message = commit?.message
            val branch = commit?.branch

            var content = message
            if (branch != null) {
                content = branch + " " + message
            }

            buildInfo.text = content

            repoBuild.pullRequest(
                    { _: Long, title: String ->
                        layoutPrInfo.visible()
                        prInfo.text = title
                    },
                    { layoutPrInfo.gone() }
            )

            repoBuild.stateColor(
                    {
                        stateColorMapper.map(it)
                    },
                    {
                        prIcon.setImageResource(R.drawable.ic_git_pull_request)
                        card.setCardBackgroundColor(it)
                    })


            itemView.setOnClickListener({
                callback?.repoBuildSelected(repoBuild.build)
            })
        }
    }

    fun addAll(builds: Collection<TravisRepoBuild>) {
        this.builds.addAll(builds)
        notifyDataSetChanged()
    }

    interface Callback {
        fun repoBuildSelected(repoBuild: ApiTravisRepoBuild)
    }
}
