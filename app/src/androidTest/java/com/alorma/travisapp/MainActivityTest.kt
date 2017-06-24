package com.alorma.travisapp

import android.arch.lifecycle.ViewModelProviders
import com.alorma.travisapp.data.viewmodel.TravisBasicDataViewModel
import com.alorma.travisapp.stubs.AccountReposStubs
import com.alorma.travisapp.stubs.AccountStubs
import com.alorma.travisapp.ui.activity.MainActivity
import com.schibsted.spain.barista.BaristaAssertions.assertDisplayed
import com.schibsted.spain.barista.flakyespresso.FlakyActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val activityTestRule = FlakyActivityTestRule(MainActivity::class.java, true, false)
    private val setup = TravisTestSetup()

    @Rule @JvmField val travisRule = setup.getRule(this, activityTestRule)

    val accountsStubs: AccountStubs = AccountStubs(setup.getWireMockServer())
    val accountRepos: AccountReposStubs = AccountReposStubs(setup.getWireMockServer())

    var idlingRes = SimpleIdlingResource()

    @Before
    fun setup() {
        accountsStubs.givenValidAccountsResponse()
        accountRepos.givenValidAccountReposResponse()

        val activity = activityTestRule.activity
        val viewModel = ViewModelProviders.of(activity).get(TravisBasicDataViewModel::class.java)
        viewModel.getTravisAccount()
                .observeForever({
                    if (it != null) {
                        idlingRes.isIdleNow = true
                    }
                })
        viewModel.getTravisRepos()
                .observeForever({
                    if (it != null) {
                        idlingRes.isIdleNow = true
                    }
                })
    }

    @Test fun showAccountName_whenTokenIsValid() {
        activityTestRule.launchActivity(null)

        assertDisplayed("testuser")
    }

    @Test fun showAccountReposNum_whenTokenIsValid() {
        activityTestRule.launchActivity(null)

        assertDisplayed("Repos: 65")
    }

    @Test fun showAccountReposItems_whenTokenIsValid() {
        activityTestRule.launchActivity(null)

        assertDisplayed("alorma/build_stages_test")
    }
}