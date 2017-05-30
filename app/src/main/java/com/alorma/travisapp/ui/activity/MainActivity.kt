package com.alorma.travisapp.ui.activity

import android.os.Bundle
import com.alorma.travisapp.R
import com.alorma.travisapp.dagger.component.ApplicationComponent
import com.alorma.travisapp.dagger.component.DaggerMainActivityComponent
import com.alorma.travisapp.logger.AppLogger
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var appLogger: AppLogger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appLogger.i(localClassName, "onCreate()")
    }

    override fun onStart() {
        super.onStart()
        appLogger.i(localClassName, "onStart()")
    }

    override fun injectComponent(component: ApplicationComponent) {
        DaggerMainActivityComponent.builder()
                .applicationComponent(component)
                .build()
                .inject(this)
    }
}
