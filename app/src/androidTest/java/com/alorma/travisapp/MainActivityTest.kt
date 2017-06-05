package com.alorma.travisapp

import com.alorma.travisapp.ui.activity.MainActivity
import com.schibsted.spain.barista.flakyespresso.FlakyActivityTestRule
import org.junit.Rule
import org.junit.Test


class MainActivityTest {
    private val activityTestRule = FlakyActivityTestRule(MainActivity::class.java, true, false)
    private val setup = TravisTestSetup()

    @Rule var travisRule = setup.getRule(this, activityTestRule)

    @Test
    public fun showAccountData_whenTokenIsValid() {

    }
}
