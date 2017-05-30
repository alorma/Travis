package com.alorma.travisapp

import android.os.Bundle
import com.alorma.travisapp.dagger.ApplicationComponent
import com.alorma.travisapp.logger.AppLogger
import com.alorma.travisapp.ui.activity.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var appLogger: AppLogger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appLogger.i(localClassName, "onCreate()")
    }

    override fun injectComponent(component: ApplicationComponent) {
        component.inject(this)
    }
}
