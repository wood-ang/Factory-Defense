package com.wood.FactoryDefense.kotlin.StaticTools

import kotlin.math.sqrt

fun distance(x1: Float, y1: Float, x2: Float, y2: Float): Float {
    return sqrt(((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)).toDouble()).toFloat()
}
