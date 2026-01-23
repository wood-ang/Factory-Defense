package com.wood.FactoryDefense.kotlin

import com.wood.FactoryDefense.Coordinate
import com.wood.FactoryDefense.kotlin.Block.*

class Chunk{
    val width: Int = 16
    val height: Int = 16

    var AlwaysLoadedChunk = false

     @JvmField
     val blocks: MutableList<BaseBlock> =
        MutableList(width * height) { BaseBuildingBlock() }


    private fun index(x: Int, y: Int): Int {
        require(x in 0 until width && y in 0 until height) {
            "Coordinate out of bounds: ($x, $y)"
        }
        return y * width + x
    }

    fun setByXY(x: Int, y: Int, value: BaseBlock) {
        blocks[index(x, y)] = value
    }

    fun setByCoordinate(coordinate: Coordinate, value: BaseBlock) {
        setByXY(coordinate.x.toInt(), coordinate.y.toInt(), value)
    }

    fun getByXY(x: Int, y: Int): BaseBlock {
        return blocks[index(x, y)]
    }

    fun getByCoordinate(coordinate: Coordinate): BaseBlock {
        return getByXY(coordinate.x.toInt(), coordinate.y.toInt())
    }

    fun indexToCoordinate(index: Int): Coordinate {
        val x = index % width
        val y = index / width
        return Coordinate(x.toDouble(), y.toDouble())
    }
    fun size() = width * height

}
