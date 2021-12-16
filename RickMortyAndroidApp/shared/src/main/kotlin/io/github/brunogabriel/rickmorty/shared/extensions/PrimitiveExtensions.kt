package io.github.brunogabriel.rickmorty.shared.extensions

import android.content.res.Resources.getSystem
import androidx.core.text.HtmlCompat

val Int.px: Int get() = (this * getSystem().displayMetrics.density).toInt()

val Int.dp: Int get() = (this / getSystem().displayMetrics.density).toInt()

fun String.fromHtml() =  HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)