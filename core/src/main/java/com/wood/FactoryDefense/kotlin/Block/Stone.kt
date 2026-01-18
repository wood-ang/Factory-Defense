package com.wood.FactoryDefense.kotlin.Block

import com.badlogic.gdx.graphics.Texture
import com.wood.FactoryDefense.StaticData.*

class Stone: BaseBlock() {
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
