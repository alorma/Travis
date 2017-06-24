package com.alorma.travisapp.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.Toast
import com.alorma.travisapp.R
import com.alorma.travisapp.dagger.component.DaggerRepoBuildsActivityComponent
import com.alorma.travisapp.dagger.component.RepoBuildsActivityComponent
import com.alorma.travisapp.data.builds.TravisRepoBuild
import com.alorma.travisapp.data.extension.appComponent
import com.alorma.travisapp.data.viewmodel.RepoBuildsDataViewModel
import kotlinx.android.synthetic.main.activity_repo_builds.*

class RepoBuildActivity : BaseActivity() {

    val component: RepoBuildsActivityComponent by lazy {
        DaggerRepoBuildsActivityComponent.builder()
                .applicationComponent(appComponent())
                .build()
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

            viewModel.loadBuild(repoSlug).observe(this, Observer {
                if (it != null) {
                    showResult(it)
                }
            })
        }
    }

    private fun showResult(it: List<TravisRepoBuild>) {
        val elements = it.size
        Toast.makeText(this, "Elements: $elements", Toast.LENGTH_LONG).show()
    }

    object Extras {
        val EXTRA_SLUG = "extra_slug"
    }
}