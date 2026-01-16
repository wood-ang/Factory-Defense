package com.wood.FactoryDefense.kotlin

import com.wood.FactoryDefense.StaticData.*

class CurveManager : Runnable {


    override fun run() {


        while (true) {

            var StartTime = System.currentTimeMillis()

            //***************************************************


            //***************************************************
            Thread.sleep(1000 / CurveLaoderFPS)
            //***************************************************
            fontX_ture += ((fontX - fontX_ture) * 0.05f)
            fontY_ture += ((fontY - fontY_ture) * 0.05f)

            cameraZoom_ture += ((cameraZoom - cameraZoom_ture) * 0.05f)
            //***************************************************
            CurveManagerFPS_true = (1000 / (System.currentTimeMillis() - StartTime))
        }
    }
}
