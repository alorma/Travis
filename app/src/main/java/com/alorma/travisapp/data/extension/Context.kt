package com.alorma.travisapp.data.extension

import android.content.Context
import com.alorma.travisapp.TravisApplication
import com.alorma.travisapp.dagger.component.ApplicationComponent

fun Context.appComponent(): ApplicationComponent {
    return (applicationContext as TravisApplication).getComponent()
}