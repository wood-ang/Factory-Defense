package com.wood.FactoryDefense.kotlin.Block

import com.wood.FactoryDefense.Coordinate
import com.wood.FactoryDefense.kotlin.Block.Chunk

class WorldMap(
    val width: Int,
    val height: Int
) {

    @JvmField
    var chunks: MutableList<Chunk> = MutableList(width * height) { Chunk() }

    var respawnPointX: Float = 0f
    var respawnPointY: Float = 0f

    fun size()= width * height

    private fun index(x: Int, y: Int): Int {
        require(x in 0 until width && y in 0 until height) {
            "Coordinate out of bounds: ($x, $y)"
        }
        return y * width + x
    }

    fun setByXY(x: Int, y: Int, value: Chunk) {
        chunks[index(x, y)] = value
    }

    fun setByCoordinate(coordinate: Coordinate, value: Chunk) {
        setByXY(coordinate.x.toInt(), coordinate.y.toInt(), value)
    }

    fun getByXY(x: Int, y: Int): Chunk {
        return chunks[index(x, y)]
    }

    fun getByCoordinate(coordinate: Coordinate): Chunk {
        return getByXY(coordinate.x.toInt(), coordinate.y.toInt())
    }
    fun getByIndex(index: Int): Chunk {
        return getByCoordinate(indexToCoordinate(index))
    }

    fun indexToCoordinate(index: Int): Coordinate {
        val x = index % width
        val y = index / width
        return Coordinate(x.toDouble(), y.toDouble())
    }
}
