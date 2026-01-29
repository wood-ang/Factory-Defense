package com.wood.FactoryDefense.kotlin.UI

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.kotlin.StaticData.StaticData.*

abstract class BasicUIBlock (
    var shape: UIShape,
    var layout: UILayout,){

    var isHovered: Boolean = false

    var parent: BasicUIBlock? = null
    val children = mutableListOf<BasicUIBlock>()

    var onClick: (() -> Unit)? = null

    /* ---------------- 坐标系统 ---------------- */

    fun getWorldX(): Float = (parent?.getWorldX() ?: 0f) + layout.x
    fun getWorldY(): Float = (parent?.getWorldY() ?: 0f) + layout.y

    /* ---------------- 层级管理 ---------------- */

    fun addUIChild(child: BasicUIBlock) {
        child.parent = this
        children.add(child)
    }

    fun removeUIChild(child: BasicUIBlock) {
        child.parent = null
        children.remove(child)
    }

    fun getUIChildren(): List<BasicUIBlock> = children

    /* ---------------- 生命周期 ---------------- */

    open fun update() {
        children.forEach { it.update() }
        isHovered = shape.contains(mouseX, mouseY, layout)
    }

    fun renderAll(batch: SpriteBatch) {
        children.forEach { it.renderAll(batch) }
        render(batch)
    }

    protected abstract fun render(batch: SpriteBatch)

    /* ---------------- 输入 ---------------- */

    open fun handleClick(px: Float, py: Float) {
        // 先让子UI优先处理（UI层级覆盖关系）
        for (child in children.reversed()) {
            child.handleClick(mouseX, mouseY)
        }

        if (shape.contains(mouseX, mouseY, layout)) {
            onClick?.invoke()
        }
    }
}
