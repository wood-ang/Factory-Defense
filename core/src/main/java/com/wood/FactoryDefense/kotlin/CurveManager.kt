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
            fontX_ture += ((fontX - fontX_ture) * 0.2).toFloat()
            fontY_ture += ((fontY - fontY_ture) * 0.2).toFloat()


            //***************************************************
            CurveManagerFPS_true = (1000 / (System.currentTimeMillis() - StartTime))
        }
    }
}
