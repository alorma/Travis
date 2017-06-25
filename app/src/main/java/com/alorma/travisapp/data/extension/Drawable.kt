package com.alorma.travisapp.data.extension

import android.graphics.drawable.Drawable
import android.support.v4.graphics.drawable.DrawableCompat

fun Drawable.tint(color: Int): Drawable {
    var drawable: Drawable = DrawableCompat.unwrap(this)
    drawable = drawable.mutate()
    DrawableCompat.setTint(drawable, color)
    return drawable
}
