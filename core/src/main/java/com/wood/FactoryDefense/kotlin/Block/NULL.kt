package com.wood.FactoryDefense.kotlin.Block

import com.badlogic.gdx.graphics.Texture
import com.wood.FactoryDefense.StaticData.*

class NULL internal constructor() : BaseBlock() {
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
