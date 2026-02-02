package com.wood.FactoryDefense.kotlin.Item

import com.wood.FactoryDefense.kotlin.StaticData.Data.CurveLaoderFPS

class ItemLayout(var x: Float, var y: Float,var angle: Int = 0) {


    var x_ture = x
    var y_ture = y
    var angle_ture = angle
    fun update() {
        x_ture += (x - x_ture) * CurveLaoderFPS
        y_ture += (y - y_ture) * CurveLaoderFPS
        angle_ture += ((angle - angle_ture) * CurveLaoderFPS).toInt()
    }
}
