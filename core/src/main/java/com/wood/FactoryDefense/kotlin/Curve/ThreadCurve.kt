package com.wood.FactoryDefense.kotlin.Curve

import com.wood.FactoryDefense.kotlin.StaticData.Data.*

class ThreadCurve (val time: Long, val begin: Double, val end: Double, val smoothingParameter : Double = 0.2, val fps: Long = CurveLaoderFPS): Runnable {
    var tureValue: Double = begin
    var remainingTime = time
    override fun run() {
        while (true){
            tureValue += (end - tureValue)*smoothingParameter
            Thread.sleep(1000/fps)
        }
    }
    fun reStart(){
        tureValue = begin
        remainingTime = time
    }
}
