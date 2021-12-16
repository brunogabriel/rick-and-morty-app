package io.github.brunogabriel.rickmorty.shared.extensions

import android.graphics.Point
import android.view.View

fun View.getLocationOnScreen(): Point {
    val coordinates = IntArray(2)
    getLocationOnScreen(coordinates)
    return Point(coordinates[0], coordinates[1])
}