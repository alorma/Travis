package com.alorma.travisapp;

import android.support.test.espresso.IdlingResource;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleIdlingResource implements IdlingResource {

    // written from main thread, read from any thread.
    private volatile IdlingResource.ResourceCallback mResourceCallback;

    private AtomicBoolean mIsIdleNow = new AtomicBoolean(true);

    public void setIdleNow(boolean idleNow) {
        mIsIdleNow.set(idleNow);
        if (idleNow) {
            mResourceCallback.onTransitionToIdle();
        }
    }

    @Override
    public String getName() {
        return "Simple idling resource";
    }

    @Override
    public boolean isIdleNow() {
        return mIsIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mResourceCallback = callback;
    }
}