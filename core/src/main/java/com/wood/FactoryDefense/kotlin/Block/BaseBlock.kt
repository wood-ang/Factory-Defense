package com.wood.FactoryDefense.kotlin.Block

import com.badlogic.gdx.graphics.Texture

abstract class BaseBlock {
    abstract fun flasher()
    abstract fun AfterBuild()
    abstract fun BeforeBroke()

    var name: String? = null
    var canDestroyed: Boolean = false
    var texture: Texture = Texture("BaseBuild")
}
