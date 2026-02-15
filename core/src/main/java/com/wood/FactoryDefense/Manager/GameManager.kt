package com.wood.FactoryDefense.Manager

import com.wood.FactoryDefense.kotlin.Main.Companion.indexOut
import com.wood.FactoryDefense.StaticData.Data.*
import com.wood.FactoryDefense.Block.Air
import com.wood.FactoryDefense.Block.BlockFactory
import com.wood.FactoryDefense.Item.BasicItem
import com.wood.FactoryDefense.Item.Bundle
import com.wood.FactoryDefense.Item.ItemLayout
import com.wood.FactoryDefense.Manager.Direction.*
import kotlin.math.sqrt
import com.wood.FactoryDefense.StaticTools.distance
import com.wood.FactoryDefense.terminal.Log

class GameManager : Runnable {
    override fun run() {

        while (true) {
            val startTime = System.currentTimeMillis()
            Thread.sleep(1000 / GameManagerFPS)
            //***************************************************
            blockFlasher()
            ItemFlasher()
            key()

//            uiManager.roots.children[0].layout.x = PanelY_ture
//
//            uiManager.update()
//            uiManager.roots.children[0].children[0] = Text(uiManager.roots.children[0].children[0].shape, uiManager.roots.children[0].layout, "$mouseX $mouseY")


            //***************************************************
            GameManagerFPS_true = (1000 / (System.currentTimeMillis() - startTime))
        }
    }

    fun blockFlasher(){
        for (x in 0 until worldMap.width) {
            for (y in 0 until worldMap.height) {
                if(mouseX in x.toFloat()*32f..(x.toFloat()*32f + 32f) && mouseY in y.toFloat()*32f..(y.toFloat()*32 + 32f)){
                    if (mouseRight){
                        worldMap.blocks[x][y].beforeBroke(x, y)
                        worldMap.blocks[x][y] = Air()
                        worldMap.blocks[x][y].isHovered = true
                        worldMap.blocks[x][y].afterBreak(x, y)
                    }
                    if (mouseLeft){
                        terminal.logs.add(Log(INFO,"You choose ${choose.name}!"))
                        if (choose.ID != -1) {
                            worldMap.blocks[x][y].beforeBuild(x, y)
                            worldMap.blocks[x][y] = BlockFactory.createBlockById(choose.ID)
                            worldMap.blocks[x][y].isHovered = true
                            worldMap.blocks[x][y].afterBuild(x, y)
                        }
                    }
                    if (mouseMIDDLE){
                        choose = worldMap.blocks[x][y]
                    }
                    if(KeyQ){
                        if (!(itemLayout.x < mouseX && itemLayout.x + 16f > mouseX && itemLayout.y < mouseY && itemLayout.y + 16f > mouseY)) {
                            worldMap.items.add(Bundle(BasicItem(),ItemLayout(x = mouseX-8f, y = mouseY-8f, degrees = 0)))
                        }
                    }
                }
            }
        }
    }

    fun ItemFlasher(){
        for (index in 0 until worldMap.items.size){
        indexOut = index
        if (KeyCONTROL_LEFT && distance(worldMap.items[index].layout.x,worldMap.items[index].layout.y,fontX_ture,fontY_ture)<=64f){
            worldMap.items[index].layout.x = fontX_ture - 8f
            worldMap.items[index].layout.y = fontY_ture - 8f
            worldMap.items[index].updata()
        } else {
            worldMap.items[index].layout.x = worldMap.items[index].layout.x_ture
            worldMap.items[index].layout.y = worldMap.items[index].layout.y_ture
        }
    }
    }



    var direction: Direction = Right

    fun key(){
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
        manager()
    }
    fun manager(){
        when(direction){
            Top -> {
                fontY += 5f
            }
            TopRight -> {
                fontY += sqrt(((5*5)/2).toDouble()).toFloat()
                fontX += sqrt(((5*5)/2).toDouble()).toFloat()
            }
            Right -> {
                fontX += 5f
            }
            BottomRight -> {
                fontY -= sqrt(((5*5)/2).toDouble()).toFloat()
                fontX += sqrt(((5*5)/2).toDouble()).toFloat()
            }
            Bottom -> {
                fontY -= 5f
            }
            BottomLeft -> {
                fontX -= sqrt(((5*5)/2).toDouble()).toFloat()
                fontY -= sqrt(((5*5)/2).toDouble()).toFloat()
            }
            Left -> {
                fontX -= 5f
            }
            TopLeft -> {
                fontY += sqrt(((5*5)/2).toDouble()).toFloat()
                fontX -= sqrt(((5*5)/2).toDouble()).toFloat()
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



