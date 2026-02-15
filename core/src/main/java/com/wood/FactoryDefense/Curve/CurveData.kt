package com.wood.FactoryDefense.Curve

class CurveData(

    var data: Float = 0f,
    val smoothingParameter: Float = 0.2f
){
    var data_ture: Float = 0f
    fun update(){
        data_ture += ((data - data_ture) * smoothingParameter)
    }
}
