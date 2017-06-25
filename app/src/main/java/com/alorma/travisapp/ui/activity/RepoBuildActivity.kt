package com.alorma.travisapp.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import com.alorma.travisapp.R
import com.alorma.travisapp.dagger.component.DaggerRepoBuildsActivityComponent
import com.alorma.travisapp.dagger.component.RepoBuildsActivityComponent
import com.alorma.travisapp.data.builds.TravisRepoBuild
import com.alorma.travisapp.data.extension.appComponent
import com.alorma.travisapp.data.viewmodel.RepoBuildsDataViewModel
import com.alorma.travisapp.ui.DummyStateColorMapper
import com.alorma.travisapp.ui.adapter.RepoBuildsAdapter
import kotlinx.android.synthetic.main.activity_repo_builds.*

class RepoBuildActivity : BaseActivity() {

    val component: RepoBuildsActivityComponent by lazy {
        DaggerRepoBuildsActivityComponent.builder()
                .applicationComponent(appComponent())
                .build()
    }

    val adapter: RepoBuildsAdapter by lazy {
        RepoBuildsAdapter(LayoutInflater.from(this), DummyStateColorMapper())
    }

    val viewModel: RepoBuildsDataViewModel by lazy {
        ViewModelProviders.of(this).get(RepoBuildsDataViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_builds)

        if (intent.extras != null) {
            component.inject(viewModel)

            val repoSlug = intent.extras.getString(Extras.EXTRA_SLUG)

            toolbar.title = repoSlug

            setupRecyclerView()
            viewModel.loadBuilds(repoSlug).observe(this, Observer {
                if (it != null) {
                    showResult(it)
                }
            })
        }
    }

    fun setupRecyclerView() {
        recyclerRepoBuilds.layoutManager = LinearLayoutManager(this)
        recyclerRepoBuilds.adapter = adapter
    }

    private fun showResult(it: List<TravisRepoBuild>) {
        adapter.addAll(it)
    }

    object Extras {
        val EXTRA_SLUG = "extra_slug"
    }
}