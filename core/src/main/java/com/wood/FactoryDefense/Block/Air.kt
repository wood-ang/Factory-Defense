package com.wood.FactoryDefense.Block

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.StaticData.Data.*

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
