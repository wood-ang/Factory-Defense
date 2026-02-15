package com.wood.FactoryDefense.UI

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.StaticData.Data.*

abstract class BasicUI (
    var shape: Shape,
    var layout: UILayout ){

    var isHovered: Boolean = false

    var parent: BasicUI? = null

    val children = mutableListOf<BasicUI>()


    /* ---------------- 层级管理 ---------------- */

    fun addUIChild(child: BasicUI) {
        child.parent = this
        children.add(child)
    }

    fun getUIChildren(): List<BasicUI> = children

    /* ---------------- 生命周期 ---------------- */

    open fun updateAll() {
        children.forEach { it.updateAll() }
        isHovered = shape.contains(layout)
        if (isHovered) {
            shape.transparency.data = 0.5f
        } else {
            shape.transparency.data = 1f
        }
        print(shape.toString())
    }

    fun renderAll(batch: SpriteBatch,font: BitmapFont) {
        render(batch, font)
        children.forEach { it.renderAll(batch, font) }
    }

    abstract fun render(batch: SpriteBatch,font: BitmapFont)

    /* ---------------- 输入 ---------------- */

    open fun handleClick(px: Float, py: Float) {
        // 先让子UI优先处理（UI层级覆盖关系）
        for (child in children.reversed()) {
            child.handleClick(mouseX, mouseY)
        }

        isHovered = shape.contains(layout)

        if (shape.contains(layout)){
            print(1)
        }
    }
}
