package com.alorma.travisapp.dagger;

import android.support.test.espresso.Espresso
import android.support.test.espresso.IdlingResource
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class IdlingResourceRule(val resource: IdlingResource) : TestRule {
    override fun apply(base: Statement?, description: Description?): Statement = object : Statement() {
        override fun evaluate() {
            Espresso.registerIdlingResources(resource)
            try {
                base?.evaluate()
            } finally {
                Espresso.unregisterIdlingResources(resource)
            }
        }
    }
}