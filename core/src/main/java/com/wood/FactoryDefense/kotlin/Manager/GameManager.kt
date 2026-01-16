package com.wood.FactoryDefense.kotlin.Manager

import com.wood.FactoryDefense.Main.worldMap
import com.wood.FactoryDefense.StaticData
import com.wood.FactoryDefense.kotlin.Chunk

class GameManager() : Runnable {
    override fun run() {
//        while (true) {
            val StartTime = System.currentTimeMillis()
            Thread.sleep(1000 / StaticData.GameManagerFPS)
            //***************************************************

            for (i1 in 0 until worldMap.size()) {
                for (i2 in 0 until worldMap.getByIndex(i1).size()) {
                    (worldMap.chunks[i1]).blocks[i2].flasher()
                }
            }

            //***************************************************
                GameManagerFPS_true = (1000/(System.currentTimeMillis() - StartTime))
//        }
    }

    companion object {
        @JvmField
        var GameManagerFPS_true: Long = 0
    }

}
