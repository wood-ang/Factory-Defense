package com.wood.FactoryDefense.kotlin.Block

import com.badlogic.gdx.graphics.Texture
import com.wood.FactoryDefense.StaticData.*

abstract class BasicBlock {


    abstract fun flasher()
    abstract fun AfterBuild()
    abstract fun BeforeBroke()

    var name: String = "BaseBlock"
    var canDestroyed: Boolean = false
    var texture: Texture = textureBaseBlock

    var isHovered = false
}
