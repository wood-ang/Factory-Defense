package com.wood.FactoryDefense.kotlin.Block

import com.wood.FactoryDefense.kotlin.StaticData.StaticData.*

class NULL internal constructor() : BasicBlock() {
    override fun flasher() {
    }

    override fun AfterBuild() {
    }

    override fun BeforeBroke() {
    }
    init{
        name = "NULL"
        canDestroyed = false
        texture = textureAir
    }
}
