package com.alorma.travisapp.ui.activity

import android.os.Bundle
import com.alorma.travisapp.R
import com.alorma.travisapp.dagger.component.ApplicationComponent
import com.alorma.travisapp.dagger.component.DaggerMainActivityComponent
import com.alorma.travisapp.data.MainPresenter
import com.alorma.travisapp.data.repos.TravisRepo
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainPresenter.Screen {

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        presenter.screen = this
        presenter.start()
    }

    override fun onStop() {
        presenter.stop()
        super.onStop()
    }

    override fun injectComponent(component: ApplicationComponent) {
        DaggerMainActivityComponent.builder()
                .applicationComponent(component)
                .build()
                .inject(this)
    }

    override fun showAccount(login: String, reposNumber: Int) {
        account_name_textview.text = login
        account_repos_count_textview.text = reposNumber.toString()
    }

    override fun showRepos(repos: List<TravisRepo>) {

    }
}
