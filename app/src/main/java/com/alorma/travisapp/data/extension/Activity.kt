package com.alorma.travisapp.data.extension

import android.app.Activity
import com.alorma.travisapp.TravisApplication
import com.alorma.travisapp.dagger.component.ApplicationComponent

fun Activity.appComponent(): ApplicationComponent {
    return (application as TravisApplication).getComponent()
}
