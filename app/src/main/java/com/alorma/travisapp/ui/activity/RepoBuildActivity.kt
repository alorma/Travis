package com.alorma.travisapp.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.widget.Toast
import com.alorma.travisapp.R
import com.alorma.travisapp.dagger.component.DaggerRepoBuildsComponent
import com.alorma.travisapp.dagger.component.RepoBuildsComponent
import com.alorma.travisapp.dagger.module.RepoBuildsModule
import com.alorma.travisapp.data.builds.TravisRepoBuild
import com.alorma.travisapp.data.extension.appComponent
import com.alorma.travisapp.data.viewmodel.RepoBuildsDataViewModel
import com.alorma.travisapp.ui.StateColorMapper
import com.alorma.travisapp.ui.adapter.RepoBuildsAdapter
import kotlinx.android.synthetic.main.activity_repo_builds.*
import javax.inject.Inject

class RepoBuildActivity : BaseActivity() {

    val adapter: RepoBuildsAdapter by lazy {
        RepoBuildsAdapter(LayoutInflater.from(this), colorMapper)
    }

    val component: RepoBuildsComponent by lazy {
        DaggerRepoBuildsComponent.builder()
                .applicationComponent(appComponent())
                .repoBuildsModule(RepoBuildsModule(this))
                .build()
    }

    @Inject
    lateinit var viewModel: RepoBuildsDataViewModel

    @Inject
    lateinit var colorMapper: StateColorMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_builds)

        if (intent.extras != null) {
            component.inject(this)

            viewModel.inject(component)

            val repoSlug = intent.extras.getString(Extras.EXTRA_SLUG)

            toolbar.title = repoSlug

            setupRecyclerView()

            val liveData = viewModel.loadBuilds(repoSlug)
            liveData.observe(this, { showResult(it) }, { showError(it) })
        }
    }

    fun  showError(t: Throwable) {
        Toast.makeText(this, "Error loading builds: " + t.message, Toast.LENGTH_SHORT).show()
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