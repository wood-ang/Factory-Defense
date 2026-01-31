package com.wood.FactoryDefense.kotlin.Block

class WorldMap(
    val width: Int,
    val height: Int
) {

    @JvmField
    val blocks = Array(width) { Array<BasicBlock>(height) { BasicBuildingBlock() } }

    var respawnPointX: Float = 0f
    var respawnPointY: Float = 0f

    fun size()= width * height
}
