package com.wood.FactoryDefense.Manager

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.Curve.CurveData
import com.wood.FactoryDefense.StaticData.UIData.*
import com.wood.FactoryDefense.UI.Arrangement
import com.wood.FactoryDefense.UI.BasicUI
import com.wood.FactoryDefense.UI.UILayout
import com.wood.FactoryDefense.UI.Shape

class UIManager (
    val batch: SpriteBatch,
    val font: BitmapFont
) {

    val roots: BasicUI = object : BasicUI(Shape(windowsWidth, windowsHeight), UILayout()) {
        override fun render(batch: SpriteBatch, font: BitmapFont) {}
    }

    init {
        roots.layout.xBottomLeft = CurveData(0f)
        roots.layout.yBottomLeft = CurveData(0f)
        roots.layout.xTopRight = CurveData(windowsWidth)
        roots.layout.yTopRight = CurveData(windowsHeight)
    }


    fun add(ui: BasicUI) {
        roots.addUIChild(ui)
    }

    fun update() {
        var residualPixels = if (roots.layout.arrangement == Arrangement.Horizontal) windowsWidth else windowsHeight
        var denominator = 0f
        var leftDistance = if (roots.layout.arrangement == Arrangement.Horizontal) windowsWidth else windowsHeight
        roots.children.forEach {
            if ((it.layout.width.data != -2f) && (it.layout.width.data != -1f)) {
                residualPixels -= it.layout.width.data
            }
            if (it.layout.weight.data_ture != 0f){
                denominator += it.layout.weight.data
            }
        }
        roots.children.forEach {
            if (it.layout.width.data == -2f) {
                it.layout.width.data = it.shape.width.data
            }else if (it.layout.width.data == -1f) {
                it.layout.width.data = roots.layout.width.data
            }
            it.updateAll()
            leftDistance -= it.layout.width.data
        }
        roots.updateAll()
    }

    fun render() {
        roots.renderAll(batch, font)
    }

    fun touchDown(touchX: Float, touchY: Float) {
    }

}
