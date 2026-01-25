package com.wood.FactoryDefense.kotlin.Manager

import com.wood.FactoryDefense.StaticData.*
import com.wood.FactoryDefense.kotlin.Block.Air
import com.wood.FactoryDefense.kotlin.Manager.Direction.*
import kotlin.math.sqrt

class GameManager : Runnable {
    override fun run() {
        while (true) {
            val StartTime = System.currentTimeMillis()
            Thread.sleep(1000 / GameManagerFPS)
            //***************************************************

            blockFlasher()
            Key()

            //***************************************************
            GameManagerFPS_true = (1000 / (System.currentTimeMillis() - StartTime))
        }
    }

    fun blockFlasher(){

        for (i1 in 0 until worldMap!!.size()) {
            for (i2 in 0 until worldMap!!.getByIndex(i1).size()) {
                (worldMap!!.chunks[i1]).blocks[i2].flasher()
            }
        }

        for (i1 in 0 until worldMap.size()) {
            for (i2 in 0 until worldMap.getByIndex(i1).size()) {

                val x = ((worldMap.indexToCoordinate(i1).x * 16) + worldMap.chunks[i1].indexToCoordinate(i2).x).toFloat() * 32f
                val y = ((worldMap.indexToCoordinate(i1).y * 16) + worldMap.chunks[i1].indexToCoordinate(i2).y).toFloat() * 32f

                if(mouseX in x..(x + 32f) && mouseY in y..(y + 32f)){
                    if (mouseRight){
                        worldMap.chunks[i1].blocks[i2] = Air()
                    }
                    if (mouseLeft){
                        if (choose.name != "NULL") {
                            worldMap.chunks[i1].blocks[i2] = choose
                        }
                    }
                }
            }
        }
    }
    var direction: Direction = Direction.D

    fun Key(){
        if ((KeyW && !KeyS)&&(!KeyA && !KeyD)){
            direction = W
        }
        if ((!KeyW && KeyS)&&(!KeyA && !KeyD)){
            direction = S
        }
        if ((!KeyW && !KeyS)&&(KeyA && !KeyD)){
            direction = A
        }
        if ((!KeyW && !KeyS)&&(!KeyA && KeyD)){
            direction = D
        }
        if ((KeyW && !KeyS)&&(!KeyA && KeyD)){
            direction = WD
        }
        if ((!KeyW && KeyS)&&(!KeyA && KeyD)){
            direction = DS
        }
        if ((!KeyW && KeyS)&&(KeyA && !KeyD)){
            direction = SA
        }
        if ((KeyW && !KeyS)&&(KeyA && !KeyD)){
            direction = AW
        }
        if ((!KeyW && !KeyS)&&(!KeyA && !KeyD)){
            return
        }
        Manager()
    }
    fun Manager(){
        when(direction){
            W -> {
                fontY += 25f
            }
            WD -> {
                fontY += sqrt(((25*25)/2).toDouble()).toFloat()
                fontX += sqrt(((25*25)/2).toDouble()).toFloat()
            }
            D -> {
                fontX += 25f
            }
            DS -> {
                fontY -= sqrt(((25*25)/2).toDouble()).toFloat()
                fontX += sqrt(((25*25)/2).toDouble()).toFloat()
            }
            S -> {
                fontY -= 25f
            }
            SA -> {
                fontX -= sqrt(((25*25)/2).toDouble()).toFloat()
                fontY -= sqrt(((25*25)/2).toDouble()).toFloat()
            }
            A -> {
                fontX -= 25f
            }
            AW -> {
                fontY += sqrt(((25*25)/2).toDouble()).toFloat()
                fontX -= sqrt(((25*25)/2).toDouble()).toFloat()
            }
        }
    }
    companion object {
        var GameManagerFPS_true: Long = 0
    }



}

enum class Direction{
    W,
    WD,
    D,
    DS,
    S,
    SA,
    A,
    AW
}



