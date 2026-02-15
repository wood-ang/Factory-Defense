package com.wood.FactoryDefense.Manager

import com.wood.FactoryDefense.StaticData.Data.*
import com.wood.FactoryDefense.StaticData.UIData.*

class CurveManager : Runnable {
    override fun run() {
        while (true) {
            val startTime = System.currentTimeMillis()

            //***************************************************


            //***************************************************
            Thread.sleep(1000 / CurveLaoderFPS)
            //***************************************************
            fontX_ture += ((fontX - fontX_ture) * 0.05f)
            fontY_ture += ((fontY - fontY_ture) * 0.05f)
            cameraZoom_ture += ((cameraZoom - cameraZoom_ture) * 0.05f)
            //***************************************************
            CurveManagerFPS_true = (1000 / (System.currentTimeMillis() - startTime))
        }
    }
}
