package com.alorma.travisapp.dagger.module

import android.content.Context
import android.view.LayoutInflater
import com.alorma.travisapp.dagger.ActivityScope
import com.alorma.travisapp.ui.activity.MainActivity
import com.alorma.travisapp.ui.adapter.ReposAdapter
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(val activity: MainActivity) {

    @Provides
    @ActivityScope
    fun provideActivity(): MainActivity {
        return activity
    }

    @Provides
    @ActivityScope
    fun provideContext(): Context {
        return activity;
    }

    @Provides
    @ActivityScope
    fun getLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }

    @Provides
    @ActivityScope
    fun getRepo(inflater: LayoutInflater): ReposAdapter {
        return ReposAdapter(inflater)
    }


}
