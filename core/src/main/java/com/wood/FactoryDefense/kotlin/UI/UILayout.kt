package com.wood.FactoryDefense.kotlin.UI

import com.wood.FactoryDefense.kotlin.StaticData.StaticData.CurveLaoderFPS


class UILayout(var arrangement: Arrangement,var weight: Int = 1,var x: Float,var y: Float) {
    var x_ture = x
    var y_ture = y
    fun update() {
        x_ture += (x - x_ture) * CurveLaoderFPS
        y_ture += (y - y_ture) * CurveLaoderFPS
    }
}

enum class Arrangement {
    Horizontal,
    Vertical
}
