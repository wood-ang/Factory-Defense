package com.wood.FactoryDefense.kotlin.Manager

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.kotlin.UI.BasicUIBlock

class UIManager {

    private val roots = mutableListOf<BasicUIBlock>()


    fun addRoot(ui: BasicUIBlock) {
        roots.add(ui)
    }

    fun update() {
        roots.forEach { it.update() }
    }

    fun render(batch: SpriteBatch) {
        roots.forEach { it.renderAll(batch) }
    }

    fun touchDown(x: Float, y: Float) {
        for (ui in roots.reversed()) {
            ui.handleClick(x, y)
        }
    }
}
