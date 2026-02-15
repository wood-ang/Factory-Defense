package com.wood.FactoryDefense.StaticTools

import kotlin.math.hypot

fun distance(x1: Float, y1: Float, x2: Float, y2: Float): Float {
    return hypot((x1 - x2).toDouble(), (y1 - y2).toDouble()).toFloat()
}
