package io.github.brunogabriel.rickmorty.shared.extensions

import android.content.res.Resources.getSystem

val Int.px: Int get() = (this * getSystem().displayMetrics.density).toInt()
