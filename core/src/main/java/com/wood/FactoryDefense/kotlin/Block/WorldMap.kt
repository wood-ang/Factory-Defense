package com.wood.FactoryDefense.kotlin.Block

import com.wood.FactoryDefense.kotlin.Curve.Coordinate
import com.wood.FactoryDefense.kotlin.Item.Bundle

class WorldMap(
    val width: Int,
    val height: Int
) {

    @JvmField
    val blocks = Array(width) { Array<BasicBlock>(height) { Air() } }
    val items = HashMap<Coordinate, Bundle>()

    var respawnPointX: Float = 0f
    var respawnPointY: Float = 0f

    fun size()= width * height
}
