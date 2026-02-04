package com.wood.FactoryDefense.kotlin.StaticTools

import java.lang.Math.toDegrees
import kotlin.math.atan2

fun getDegrees(x1: Float, y1: Float, x2: Float, y2: Float): Int {
    // 计算两点之间的差值
    val dx = x2 - x1
    val dy = y2 - y1

    // 使用atan2计算弧度（注意：atan2的参数是(y, x)）
    val radians = atan2(dy.toDouble(), dx.toDouble())

    // 将弧度转换为角度
    var degrees = toDegrees(radians)

    // 将角度转换为0-360度范围
    if (degrees < 0) {
        degrees += 360.0
    }

    return degrees.toInt()
}
