package com.wood.FactoryDefense.kotlin.Block

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.kotlin.Manager.Direction
import com.wood.FactoryDefense.kotlin.Manager.Direction.*
import com.wood.FactoryDefense.kotlin.StaticData.StaticData.*

open class BasicBlock {

    // 方向顺序固定（使用正确的拼写）
    var DIR_Top: Boolean = false
    var DIR_TopRight: Boolean = false
    var DIR_Right: Boolean = false
    var DIR_BottomRight: Boolean = false
    var DIR_Bottom: Boolean = false
    var DIR_BottomLeft: Boolean = false
    var DIR_Left: Boolean = false
    var DIR_TopLeft: Boolean = false

    var name: String = "BaseBlock"
    var canDestroyed: Boolean = false
    var texture: Texture = textureBaseBlock
    var ID: Int = -1
    var isHovered = false

    override fun toString(): String {
        return "[name]$name,\n[canDestroyed]$canDestroyed,\n[texture]$texture,\n[ID]$ID,\n[isHovered]$isHovered\n[DIR_Top]$DIR_Top,\n[DIR_TopRight]$DIR_TopRight,\n[DIR_Right]$DIR_Right,\n[DIR_BottomRight]$DIR_BottomRight,\n[DIR_Bottom]$DIR_Bottom,\n[DIR_BottomLeft]$DIR_BottomLeft,\n[DIR_Left]$DIR_Left,\n[DIR_TopLeft]$DIR_TopLeft"
    }

    open fun update(x: Int, y: Int) {
    }

    fun updateConnections(x: Int, y: Int) {
        // 使用正确的方向枚举值
        DIR_Top =           getNeighborByDirection(x, y, Top).ID == ID
        DIR_TopRight =      getNeighborByDirection(x, y, TopRight).ID == ID
        DIR_Right =         getNeighborByDirection(x, y, Right).ID == ID
        DIR_BottomRight =   getNeighborByDirection(x, y, BottomRight).ID == ID
        DIR_Bottom =        getNeighborByDirection(x, y, Bottom).ID == ID
        DIR_BottomLeft =    getNeighborByDirection(x, y, BottomLeft).ID == ID
        DIR_Left =          getNeighborByDirection(x, y, Left).ID == ID
        DIR_TopLeft =       getNeighborByDirection(x, y, TopLeft).ID == ID
    }

    open fun afterBuild(x: Int, y: Int) {
        updateSelfAndNeighbors(x, y)
    }

    open fun beforeBuild(x: Int, y: Int) {
    }

    open fun afterBreak(x: Int, y: Int) {
        updateSelfAndNeighbors(x, y)
    }

    open fun beforeBroke(x: Int, y: Int) {
    }

    fun updateSelfAndNeighbors(x: Int, y: Int) {
        updateConnections(x, y)
        if (y + 1 < worldMap.height)                            worldMap.blocks[x][y + 1].updateConnections(x ,y + 1)
        if (y - 1 >= 0)                                         worldMap.blocks[x][y - 1].updateConnections(x ,y - 1)
        if (x + 1 < worldMap.width)                             worldMap.blocks[x + 1][y].updateConnections(x + 1, y)
        if (x - 1 >= 0)                                         worldMap.blocks[x - 1][y].updateConnections(x - 1, y)
        if (x + 1 < worldMap.width && y + 1 < worldMap.height)  worldMap.blocks[x + 1][y + 1].updateConnections(x + 1, y + 1)
        if (x + 1 < worldMap.width && y - 1 >= 0)               worldMap.blocks[x + 1][y - 1].updateConnections(x + 1, y - 1)
        if (x - 1 >= 0 && y + 1 < worldMap.height)              worldMap.blocks[x - 1][y + 1].updateConnections(x - 1, y + 1)
        if (x - 1 >= 0 && y - 1 >= 0)                           worldMap.blocks[x - 1][y - 1].updateConnections(x - 1, y - 1)
    }

    open fun render(x: Float, y: Float, batch: SpriteBatch) {
        batch.draw(texture, x, y)
    }

    fun getNeighborByDirection(x: Int, y: Int, direction: Direction): BasicBlock{
        return when (direction) {
            Top -> {
                if (y + 1 >= worldMap.height) Air()
                else worldMap.blocks[x][y + 1]
            }
            TopRight -> {
                if (x + 1 >= worldMap.width || y + 1 >= worldMap.height) Air()
                else worldMap.blocks[x + 1][y + 1]
            }
            Right -> {
                if (x + 1 >= worldMap.width) Air()
                else worldMap.blocks[x + 1][y]
            }
            BottomRight -> {
                if (x + 1 >= worldMap.width || y - 1 < 0) Air()
                else worldMap.blocks[x + 1][y - 1]
            }
            Bottom -> {
                if (y - 1 < 0) Air()
                else worldMap.blocks[x][y - 1]
            }
            BottomLeft -> {
                if (x - 1 < 0 || y - 1 < 0) Air()
                else worldMap.blocks[x - 1][y - 1]
            }
            Left -> {
                if (x - 1 < 0) Air()
                else worldMap.blocks[x - 1][y]
            }
            TopLeft -> {
                if (x - 1 < 0 || y + 1 >= worldMap.height) Air()
                else worldMap.blocks[x - 1][y + 1]
            }
        }
    }
}
