package com.alorma.travisapp.data.extension

import android.support.v4.content.ContextCompat
import android.widget.ImageView

fun ImageView.tintDrawable(drawableRes: Int, color: Int) {
    val drawable = ContextCompat.getDrawable(context, drawableRes)
    setImageDrawable(drawable.tint(color))
}
