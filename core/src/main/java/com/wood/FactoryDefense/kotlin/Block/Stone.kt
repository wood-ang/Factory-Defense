package com.wood.FactoryDefense.kotlin.Block

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.kotlin.StaticData.Data.*

class Stone: BasicBlock() {
    fun flasher() {
    }

    override fun afterBuild(x: Int, y: Int) {
    }

    override fun beforeBroke(x: Int, y: Int) {
    }

    override fun render(x: Float, y: Float, batch: SpriteBatch) {
        batch.draw(texture, x, y)
        if (!DIR_Top){
            batch.draw(textureStoneFrame_T, x, y)
        }
        if (!DIR_Right){
            batch.draw(textureStoneFrame_R, x, y)
        }
        if (!DIR_Bottom){
            batch.draw(textureStoneFrame_B, x, y)
        }
        if (!DIR_Left){
            batch.draw(textureStoneFrame_L, x, y)
        }
    }

    init {
        name = "Stone"
        canDestroyed = true
        texture = textureStone
        ID = 3
    }
}
