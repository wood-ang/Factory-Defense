package com.wood.FactoryDefense.kotlin.Curve

import com.wood.FactoryDefense.StaticData.*

class ThreadCurve (val time: Long, val begin: Double, val end: Double, val smoothingParameter : Double = 0.2, val FPS: Long = CurveLaoderFPS): Runnable {
    var tureValue: Double = begin
    override fun run() {
        var remainingTime = time
        while (true){
            tureValue += (end - tureValue)*smoothingParameter

            remainingTime -= 1000/FPS
            if (remainingTime <= 0){return}
            Thread.sleep(1000/FPS)
        }
    }
}
