package com.wood.FactoryDefense.kotlin.Curve

class CurveData(
    val smoothingParameter: Float
){
    var data: Float = 0f
    var data_ture: Float = 0f
    fun update(){
        data_ture += ((data - data_ture) * smoothingParameter)
    }
}
