package com.alorma.travisapp.ui.activity

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alorma.travisapp.R
import com.alorma.travisapp.dagger.component.ApplicationComponent
import com.alorma.travisapp.dagger.component.DaggerMainActivityComponent
import com.alorma.travisapp.dagger.module.MainActivityModule
import com.alorma.travisapp.data.MainPresenter
import com.alorma.travisapp.data.live.AccountLiveData
import com.alorma.travisapp.data.repos.TravisRepo
import com.alorma.travisapp.ui.adapter.ReposAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainPresenter.Screen {

    @Inject
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var adapter: ReposAdapter

    val accountLiveData = AccountLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupData()

        setupRecyclerView()
    }

    private fun setupData() {
        accountLiveData.observe(this, Observer { t ->
            account_name_textview.text = t?.login
            account_repos_count_textview.text = t?.reposCount.toString()
        })
    }

    private fun setupRecyclerView() {
        recyclerRepos.layoutManager = LinearLayoutManager(this)
        recyclerRepos.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        presenter.screen = this
        presenter.start(accountLiveData)
    }

    override fun onStop() {
        presenter.stop()
        super.onStop()
    }

    override fun injectComponent(component: ApplicationComponent) {
        DaggerMainActivityComponent.builder()
                .applicationComponent(component)
                .mainActivityModule(MainActivityModule(this))
                .build()
                .inject(this)
    }

    override fun showRepos(repos: List<TravisRepo>) {
        adapter.addAll(repos)
    }
}
