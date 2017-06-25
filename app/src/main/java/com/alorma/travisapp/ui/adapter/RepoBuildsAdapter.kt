package com.alorma.travisapp.ui.adapter

import android.graphics.Color
import android.support.annotation.ColorRes
import android.support.v4.graphics.drawable.DrawableCompat
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
import com.alorma.travisapp.ui.StateColor
import com.alorma.travisapp.ui.gone
import com.alorma.travisapp.ui.visible
import kotlinx.android.synthetic.main.row_repo_build.view.*


class RepoBuildsAdapter(val inflater: LayoutInflater) : RecyclerView.Adapter<RepoBuildsAdapter.Holder>() {

    var callback: Callback? = null
        set

    val builds: MutableList<TravisRepoBuild> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        return Holder(inflater.inflate(R.layout.row_repo_build, parent, false))
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        val repoBuild = builds[position]
        holder?.populate(repoBuild, callback)
    }

    override fun getItemCount(): Int {
        return builds.size
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: CardView? = itemView.repoBuildCard
        val buildInfo: TextView? = itemView.buildInfo
        val layoutPrInfo: View? = itemView.layoutPrInfo
        val prIcon: ImageView? = itemView.prIcon
        val prInfo: TextView? = itemView.prInfo

        fun populate(repoBuild: TravisRepoBuild, callback: Callback?) {
            val buildNumber = repoBuild.build.number

            val commit = repoBuild.commit
            val message = commit?.message
            val branch = commit?.branch

            var content = message
            if (branch != null) {
                content = branch + " " + message
            }

            buildInfo?.text = "[$buildNumber] $content"

            if (repoBuild.build.isPullRequest) {
                layoutPrInfo?.visible()
                val pullRequestNumber = repoBuild.build.pullRequestNumber
                val pullRequestTitle = repoBuild.build.pullRequestTitle
                prInfo?.text = "[#$pullRequestNumber] $pullRequestTitle"
            } else {
                layoutPrInfo?.gone()
            }

            val colorFromBuild = getColorFromBuild(repoBuild.build)

            val drawable = prIcon?.drawable?.mutate()
            if (drawable != null) {
                DrawableCompat.setTint(drawable, colorFromBuild)
                prIcon?.background = drawable
            }

            card?.setCardBackgroundColor(colorFromBuild)

            itemView.setOnClickListener({
                callback?.repoBuildSelected(repoBuild.build)
            })
        }

        fun getColorFromBuild(build: ApiTravisRepoBuild): Int {
            val stateColor = StateColor.fromState(build.state)

            @ColorRes val color: Int
            when (stateColor) {
                StateColor.GREEN -> color = Color.parseColor("#00FF00")
                StateColor.YELLOW -> color = Color.parseColor("#FFFF00")
                StateColor.RED -> color = Color.parseColor("#FF0000")
                else -> color = Color.parseColor("#666666")
            }
            return color
        }
    }

    fun addAll(builds: Collection<TravisRepoBuild>?) {
        if (builds != null) {
            this.builds.addAll(builds)
            notifyDataSetChanged()
        }
    }

    interface Callback {
        fun repoBuildSelected(repoBuild: ApiTravisRepoBuild)
    }
}
