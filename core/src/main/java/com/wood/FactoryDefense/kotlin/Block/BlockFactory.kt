package com.wood.FactoryDefense.kotlin.Block

object BlockFactory {
    fun createBlock(type: String): BasicBlock {
        return when (type) {
            "Air" -> Air()
            "BaseBuildingBlock" -> BasicBuildingBlock()
            "Stone" -> Stone()
            else -> Air()  // 默认返回 Air
        }
    }

    fun createBlockById(id: Int): BasicBlock {
        return when (id) {
            0 -> Air()
            1 -> BasicBuildingBlock()
            2 -> Stone()
            else -> Air()
        }
    }
}
