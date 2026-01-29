package com.wood.FactoryDefense.kotlin.Manager

import com.wood.FactoryDefense.kotlin.StaticData.StaticData.*
import com.wood.FactoryDefense.kotlin.StaticData.StaticUIData.*

class CurveManager : Runnable {
    override fun run() {
        while (true) {
            var startTime = System.currentTimeMillis()

            //***************************************************


            //***************************************************
            Thread.sleep(1000 / CurveLaoderFPS)
            //***************************************************
            fontX_ture += ((fontX - fontX_ture) * 0.05f)
            fontY_ture += ((fontY - fontY_ture) * 0.05f)
            debugFontY.update()
            cameraZoom_ture += ((cameraZoom - cameraZoom_ture) * 0.05f)
            //***************************************************
            CurveManagerFPS_true = (1000 / (System.currentTimeMillis() - startTime))
        }
    }
}
class CurveData(
    val smoothingParameter: Float
){
    var data: Float = 0f
    var data_ture: Float = 0f
    fun update(){
        data_ture += ((data - data_ture) * smoothingParameter)
    }
}
