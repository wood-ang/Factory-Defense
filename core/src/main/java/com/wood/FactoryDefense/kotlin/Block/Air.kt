package com.wood.FactoryDefense.kotlin.Block

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.kotlin.StaticData.StaticData.*

class Air internal constructor() : BasicBlock() {

    override fun afterBuild(x: Int, y: Int) {
    }

    override fun beforeBroke(x: Int, y: Int) {
    }
    override fun render(x: Float, y: Float, batch: SpriteBatch) {
        batch.draw(texture, x, y)
    }
    init {
        name = "Air"
        canDestroyed = false
        texture = textureAir
        ID = 1
    }
}
