package com.wood.FactoryDefense.Item

import com.wood.FactoryDefense.StaticData.Data.CurveLaoderFPS

class ItemLayout(var x: Float, var y: Float,var degrees: Int = 0) {


    var x_ture = x
    var y_ture = y
    var angle_ture = degrees
    fun update() {
        x_ture += (x - x_ture) * CurveLaoderFPS
        y_ture += (y - y_ture) * CurveLaoderFPS
        angle_ture += ((degrees - angle_ture) * CurveLaoderFPS).toInt()
    }
}
