package com.wood.FactoryDefense.kotlin

import com.wood.FactoryDefense.StaticData


class GameManager : Runnable {

    override fun run() {
        while (true) {
            val StartTime = System.currentTimeMillis()

            //***************************************************


            //***************************************************
            Thread.sleep(1000 / StaticData.GameManagerFPS)
            //***************************************************


            //***************************************************
                GameManagerFPS_true = (1000/(System.currentTimeMillis() - StartTime))
        }
    }

    companion object {
        @JvmField
        var GameManagerFPS_true: Long = 0
    }

}
