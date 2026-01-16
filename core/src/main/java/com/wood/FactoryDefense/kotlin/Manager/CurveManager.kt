package com.wood.FactoryDefense.kotlin.Manager

import com.wood.FactoryDefense.StaticData

class CurveManager : Runnable {


    override fun run() {


        while (true) {

            var startTime = System.currentTimeMillis()

            //***************************************************


            //***************************************************
            Thread.sleep(1000 / StaticData.CurveLaoderFPS)
            //***************************************************
            StaticData.fontX_ture += ((StaticData.fontX - StaticData.fontX_ture) * 0.05f)
            StaticData.fontY_ture += ((StaticData.fontY - StaticData.fontY_ture) * 0.05f)

            StaticData.cameraZoom_ture += ((StaticData.cameraZoom - StaticData.cameraZoom_ture) * 0.05f)
            //***************************************************
            StaticData.CurveManagerFPS_true = (1000 / (System.currentTimeMillis() - startTime))
        }
    }
}
