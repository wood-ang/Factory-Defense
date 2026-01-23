package com.wood.FactoryDefense.kotlin.Curve

import com.wood.FactoryDefense.StaticData.*

class ThreadCurve (val time: Long, val begin: Double, val end: Double, val smoothingParameter : Double = 0.2, val FPS: Long = CurveLaoderFPS): Runnable {
    var tureValue: Double = begin
    var remainingTime = time
    override fun run() {
        while (true){
            tureValue += (end - tureValue)*smoothingParameter

            remainingTime -= 1000/FPS
            if (remainingTime <= 0){
                tureValue = end
                return
            }
            Thread.sleep(1000/FPS)
        }
    }
    fun reStart(){
        tureValue = begin
        remainingTime = time
    }
}
