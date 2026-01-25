package com.wood.FactoryDefense.kotlin.UI

import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class BasicUIBlock {

    var shape = UIShape()

    private var parent: BasicUIBlock? = null
    private val children = mutableListOf<BasicUIBlock>()

    var onClick: (() -> Unit)? = null

    /* ---------------- 坐标系统 ---------------- */

    fun getWorldX(): Float = (parent?.getWorldX() ?: 0f) + shape.x
    fun getWorldY(): Float = (parent?.getWorldY() ?: 0f) + shape.y

    /* ---------------- 层级管理 ---------------- */

    fun addChild(child: BasicUIBlock) {
        child.parent = this
        children.add(child)
    }

    fun removeChild(child: BasicUIBlock) {
        child.parent = null
        children.remove(child)
    }

    fun getChildren(): List<BasicUIBlock> = children

    /* ---------------- 生命周期 ---------------- */

    open fun update() {
        children.forEach { it.update() }
        shape.contains(shape.x,shape.y,shape.width,shape.height)
    }

    fun renderAll(batch: SpriteBatch) {
        children.forEach { it.renderAll(batch) }
        render(batch, getWorldX(), getWorldY())
    }

    protected abstract fun render(batch: SpriteBatch, worldX: Float, worldY: Float)

    /* ---------------- 输入 ---------------- */

    fun handleClick(px: Float, py: Float) {
        // 先让子UI优先处理（UI层级覆盖关系）
        for (child in children.reversed()) {
            child.handleClick(px, py)
        }

        val wx = getWorldX()
        val wy = getWorldY()

        if (shape.contains(px, py, wx, wy)) {
            onClick?.invoke()
        }
    }
}
