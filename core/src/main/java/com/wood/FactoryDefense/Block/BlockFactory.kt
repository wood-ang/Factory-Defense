package com.wood.FactoryDefense.Block

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
            1 -> Air()
            2 -> BasicBuildingBlock()
            3 -> Stone()
            else -> Air()
        }
    }
}
