package com.wood.FactoryDefense.kotlin.Manager

import com.wood.FactoryDefense.kotlin.StaticData.StaticData.*
import com.wood.FactoryDefense.kotlin.Block.Air
import com.wood.FactoryDefense.kotlin.Block.BlockFactory
import com.wood.FactoryDefense.kotlin.Manager.Direction.*
import kotlin.math.sqrt
import com.wood.FactoryDefense.kotlin.StaticData.StaticUIData.*
import com.wood.FactoryDefense.kotlin.StaticData.StaticUIData.uiManager
import com.wood.FactoryDefense.kotlin.UI.UIText
import com.wood.FactoryDefense.kotlin.terminal.Log

class GameManager : Runnable {
    override fun run() {

        while (true) {
            val StartTime = System.currentTimeMillis()
            Thread.sleep(1000 / GameManagerFPS)
            //***************************************************
            blockFlasher()
            Key()

            uiManager.roots.children[0].layout.x = PanelY_ture

            uiManager.update()
            uiManager.roots.children[0].children[0] = UIText(uiManager.roots.children[0].children[0].shape, uiManager.roots.children[0].layout, "${mouseX } ${mouseY}")


            //***************************************************
            GameManagerFPS_true = (1000 / (System.currentTimeMillis() - StartTime))
        }
    }

    fun blockFlasher(){

        for (x in 0 until worldMap.width) {
            for (y in 0 until worldMap.height) {
                worldMap.blocks[x][y].updateSelfAndNeighbors(x, y)
            }
        }

        for (x in 0 until worldMap.width) {
            for (y in 0 until worldMap.height) {

                if(mouseX in x.toFloat()*32f..(x.toFloat()*32f + 32f) && mouseY in y.toFloat()*32f..(y.toFloat()*32 + 32f)){
                    if (mouseRight){
                        worldMap.blocks[x][y].beforeBroke(x, y)
                        worldMap.blocks[x][y] = Air()
                        worldMap.blocks[x][y].afterBreak(x, y)
                    }
                    if (mouseLeft){
                        terminal.logs.add(Log(INFO,"You choose ${choose.name}!"))
                        if (choose.ID != -1) {
                            worldMap.blocks[x][y].beforeBuild(x, y)
                            worldMap.blocks[x][y] = BlockFactory.createBlockById(choose.ID)
                            worldMap.blocks[x][y].afterBuild(x, y)
                        }
                    }
                }
                worldMap.blocks[x][y].updateSelfAndNeighbors(x, y)
            }
        }
    }



    var direction: Direction = Right

    fun Key(){
        if ((KeyW && !KeyS)&&(!KeyA && !KeyD)){
            direction = Top
        }
        if ((!KeyW && KeyS)&&(!KeyA && !KeyD)){
            direction = Bottom
        }
        if ((!KeyW && !KeyS)&&(KeyA && !KeyD)){
            direction = Left
        }
        if ((!KeyW && !KeyS)&&(!KeyA && KeyD)){
            direction = Right
        }
        if ((KeyW && !KeyS)&&(!KeyA && KeyD)){
            direction = TopRight
        }
        if ((!KeyW && KeyS)&&(!KeyA && KeyD)){
            direction = BottomRight
        }
        if ((!KeyW && KeyS)&&(KeyA && !KeyD)){
            direction = BottomLeft
        }
        if ((KeyW && !KeyS)&&(KeyA && !KeyD)){
            direction = TopLeft
        }
        if ((!KeyW && !KeyS)&&(!KeyA && !KeyD)){
            return
        }
        Manager()
    }
    fun Manager(){
        when(direction){
            Top -> {
                fontY += 25f
            }
            TopRight -> {
                fontY += sqrt(((25*25)/2).toDouble()).toFloat()
                fontX += sqrt(((25*25)/2).toDouble()).toFloat()
            }
            Right -> {
                fontX += 25f
            }
            BottomRight -> {
                fontY -= sqrt(((25*25)/2).toDouble()).toFloat()
                fontX += sqrt(((25*25)/2).toDouble()).toFloat()
            }
            Bottom -> {
                fontY -= 25f
            }
            BottomLeft -> {
                fontX -= sqrt(((25*25)/2).toDouble()).toFloat()
                fontY -= sqrt(((25*25)/2).toDouble()).toFloat()
            }
            Left -> {
                fontX -= 25f
            }
            TopLeft -> {
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
    Top,
    TopRight,
    Right,
    BottomRight,
    Bottom,
    BottomLeft,
    Left,
    TopLeft
}



