package com.alorma.travisapp.ui.activity

import android.arch.lifecycle.LifecycleActivity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alorma.travisapp.TravisApplication
import com.alorma.travisapp.dagger.component.ApplicationComponent

abstract class BaseActivity : LifecycleActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
    }

    private fun injectComponent() {
        if (application is TravisApplication) {
            val component = (application as TravisApplication).getComponent()
            injectComponent(component)
        }
    }

    abstract fun injectComponent(component: ApplicationComponent)
}
