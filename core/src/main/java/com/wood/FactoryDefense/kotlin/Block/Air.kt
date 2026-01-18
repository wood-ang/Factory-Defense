package com.wood.FactoryDefense.kotlin.Block

import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.wood.FactoryDefense.StaticData.*

class Air internal constructor() : BaseBlock() {
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
