package com.alorma.travisapp

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.rule.ActivityTestRule
import com.alorma.travisapp.dagger.IdlingResourceRule
import com.alorma.travisapp.dagger.IdlingRxJava2IoSchedulerTestRule
import com.alorma.travisapp.dagger.TravisDaggerRule
import com.alorma.travisapp.dagger.module.TestNetworkModule
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.rules.RuleChain
import org.junit.rules.TestRule

class TravisTestSetup {
    private val PORT = 8080
    private val wireMockRule = WireMockRule(PORT)

    fun getRule(target: Any, activityTestRule: ActivityTestRule<*>): TestRule {
        val networkModule = TestNetworkModule(PORT)
        val applicationContext: TravisApplication = getInstrumentation().targetContext.applicationContext as TravisApplication
        val travisDaggerRule = TravisDaggerRule(applicationContext, networkModule)

        return RuleChain.outerRule(wireMockRule)
                .around { base, _ -> travisDaggerRule.apply(base, null, target) }
                .around(IdlingResourceRule(networkModule.getIdlingResource()))
                .around(IdlingRxJava2IoSchedulerTestRule())
                .around(activityTestRule)
    }

    fun getWireMockServer(): WireMockServer {
        return wireMockRule
    }
}

