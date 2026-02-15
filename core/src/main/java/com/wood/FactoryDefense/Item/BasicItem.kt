package com.wood.FactoryDefense.Item

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.StaticData.Data.*

open class BasicItem {

    var name: String = "BaseBlock"
    var canDestroyed: Boolean = false
    var texture: Texture = textureBasicItem
    var ID: Int = -1

    override fun toString(): String {
        return "[name]$name,\n[canDestroyed]$canDestroyed,\n[texture]$texture,\n[ID]$ID"
    }
    fun toIndentString(indent : Int = 0): String {
        return "    ".repeat(indent)+"[name]$name,\n"+"    ".repeat(indent)+"[canDestroyed]$canDestroyed,\n"+"    ".repeat(indent)+"[texture]$texture,\n"+"    ".repeat(indent)+"[ID]$ID"
    }

    open fun update(x: Int, y: Int) {
    }

    open fun render(x: Float, y: Float, angle: Int, batch: SpriteBatch) {
        batch.draw(texture, x, y)
    }

}
