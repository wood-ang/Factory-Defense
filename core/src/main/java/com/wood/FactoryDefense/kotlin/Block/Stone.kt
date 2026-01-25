package com.wood.FactoryDefense.kotlin.Block

import com.wood.FactoryDefense.StaticData.*

class Stone: BasicBlock() {
    override fun flasher() {
    }

    override fun AfterBuild() {
    }

    override fun BeforeBroke() {
    }

    init {
        name = "Stone"
        canDestroyed = true
        texture = textureStone
    }
}
