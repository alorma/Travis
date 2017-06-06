package com.alorma.travisapp

import com.alorma.travisapp.stubs.AccountStubs
import com.alorma.travisapp.ui.activity.MainActivity
import com.schibsted.spain.barista.BaristaAssertions.assertDisplayed
import com.schibsted.spain.barista.flakyespresso.FlakyActivityTestRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val activityTestRule = FlakyActivityTestRule(MainActivity::class.java, true, false)
    private val setup = TravisTestSetup()

    @Rule @JvmField val travisRule = setup.getRule(this, activityTestRule)

    val stubs: AccountStubs = AccountStubs(setup.getWireMockServer())

    @Test fun showAccountData_whenTokenIsValid() {
        stubs.givenValidAccountsResponse()
        activityTestRule.launchActivity(null)

        assertDisplayed("testuser")
    }
}