package com.wood.FactoryDefense.kotlin.Block

import com.wood.FactoryDefense.kotlin.StaticData.StaticData.*

class NULL internal constructor() : BasicBlock() {
    fun flasher() {
    }

    override fun afterBuild(x: Int, y: Int) {
    }

    override fun beforeBroke(x: Int, y: Int) {
    }
    init{
        name = "NULL"
        canDestroyed = false
        texture = textureAir
        ID = -1
    }
}
