package com.alorma.travisapp.ui.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.widget.Toast
import com.alorma.travisapp.R
import com.alorma.travisapp.dagger.component.DaggerMainActivityComponent
import com.alorma.travisapp.dagger.component.MainActivityComponent
import com.alorma.travisapp.data.account.TravisAccount
import com.alorma.travisapp.data.extension.appComponent
import com.alorma.travisapp.data.repos.TravisRepo
import com.alorma.travisapp.data.viewmodel.TravisBasicDataViewModel
import com.alorma.travisapp.ui.adapter.ReposAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity(), ReposAdapter.Callback {
    val component: MainActivityComponent by lazy {
        DaggerMainActivityComponent.builder()
                .applicationComponent(appComponent())
                .build()
    }

    val adapter: ReposAdapter by lazy {
        ReposAdapter(LayoutInflater.from(this))
    }

    val viewModel: TravisBasicDataViewModel by lazy {
        ViewModelProviders.of(this).get(TravisBasicDataViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component.inject(viewModel)

        setupRecyclerView()
        setupData()
    }

    private fun setupData() {
        viewModel.getTravisAccount().observe(this, {
            setAccount(it)
        }, { onError(it) })

        viewModel.getTravisRepos().observe(this, {
            adapter.addAll(it)
        }, { onError(it) })
    }

    fun onError(t: Throwable) {
        Toast.makeText(this, "Show error: " + t.message, Toast.LENGTH_SHORT).show()
    }

    private fun setAccount(it: TravisAccount) {
        toolbar.title = it.login
        val countRepos = it.reposCount.toString()
        toolbar.subtitle = "Repos: $countRepos"
    }

    private fun setupRecyclerView() {
        recyclerRepos.layoutManager = LinearLayoutManager(this)
        recyclerRepos.adapter = adapter
        adapter.callback = this
    }

    override fun repoSelected(travisRepo: TravisRepo) {
        startActivity<RepoBuildActivity>(RepoBuildActivity.Extras.EXTRA_SLUG to travisRepo.slug)
    }

    override fun repoActiveStateChanged(travisRepos: TravisRepo, active: Boolean) {

    }
}
