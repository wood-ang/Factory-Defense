package com.wood.FactoryDefense.kotlin.Item

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.kotlin.StaticData.StaticData.*

open class BasicItem {

    var name: String = "BaseBlock"
    var canDestroyed: Boolean = false
    var texture: Texture = textureBasicItem
    var ID: Int = -1
    var isHovered = false

    override fun toString(): String {
        return "[name]$name,\n[canDestroyed]$canDestroyed,\n[texture]$texture,\n[ID]$ID,\n[isHovered]$isHovered"
    }

    open fun update(x: Int, y: Int) {
    }

    open fun render(x: Float, y: Float, batch: SpriteBatch) {
        batch.draw(texture, x, y)
    }

}
