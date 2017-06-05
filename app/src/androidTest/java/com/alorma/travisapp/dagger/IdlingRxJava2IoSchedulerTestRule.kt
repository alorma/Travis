package com.alorma.travisapp.dagger;

import android.os.AsyncTask
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class IdlingRxJava2IoSchedulerTestRule : TestRule {
    override fun apply(base: Statement?, description: Description?): Statement = object : Statement() {
        override fun evaluate() {
            hookSchedulers();
            try {
                base?.evaluate();
            } finally {
                restoreSchedulers();
            }
        }

    }

    fun hookSchedulers() {
        RxJavaPlugins.reset();
        RxJavaPlugins.initIoScheduler { Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR) };
    }

    fun restoreSchedulers() {
        RxJavaPlugins.reset();
    }
}