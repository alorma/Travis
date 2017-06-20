package com.alorma.travisapp.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import com.alorma.travisapp.R
import com.alorma.travisapp.dagger.component.DaggerMainActivityComponent
import com.alorma.travisapp.dagger.component.MainActivityComponent
import com.alorma.travisapp.data.extension.appComponent
import com.alorma.travisapp.data.viewmodel.TravisBasicDataViewModel
import com.alorma.travisapp.ui.adapter.ReposAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

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
        viewModel.getTravisAccount().observe(this, Observer {
            account_name_textview.text = it?.login
            account_repos_count_textview.text = it?.reposCount.toString()
        })

        viewModel.getTravisRepos().observe(this, Observer {
            adapter.addAll(it)
        })

        viewModel.getErrorData().observe(this, Observer {

        })
    }

    private fun setupRecyclerView() {
        recyclerRepos.layoutManager = LinearLayoutManager(this)
        recyclerRepos.adapter = adapter
    }
}
