package com.wood.FactoryDefense.kotlin.Block

import com.wood.FactoryDefense.StaticData.*

class Air internal constructor() : BasicBlock() {
    override fun flasher() {
    }

    override fun AfterBuild() {
    }

    override fun BeforeBroke() {
    }
    init {
        name = "Air"
        canDestroyed = false
        texture = textureAir
    }
}
