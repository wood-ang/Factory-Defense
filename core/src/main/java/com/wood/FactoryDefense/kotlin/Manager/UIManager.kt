package com.wood.FactoryDefense.kotlin.Manager

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.kotlin.StaticData.StaticData.CurveLaoderFPS
import com.wood.FactoryDefense.kotlin.StaticData.StaticData.CurveManagerFPS_true
import com.wood.FactoryDefense.kotlin.StaticData.StaticData.*
import com.wood.FactoryDefense.kotlin.StaticData.StaticData.cameraZoom
import com.wood.FactoryDefense.kotlin.StaticData.StaticData.cameraZoom_ture
import com.wood.FactoryDefense.kotlin.StaticData.StaticData.fontX
import com.wood.FactoryDefense.kotlin.StaticData.StaticData.fontX_ture
import com.wood.FactoryDefense.kotlin.StaticData.StaticData.fontY
import com.wood.FactoryDefense.kotlin.StaticData.StaticData.fontY_ture
import com.wood.FactoryDefense.kotlin.StaticData.StaticUIData.debugFontY
import com.wood.FactoryDefense.kotlin.UI.Arrangement
import com.wood.FactoryDefense.kotlin.UI.BasicUIBlock
import com.wood.FactoryDefense.kotlin.UI.UILayout
import com.wood.FactoryDefense.kotlin.UI.UIShape

class UIManager (
    val batch: SpriteBatch,
    val font: BitmapFont
) {
    // 假设有一个具体的UIBlock实现类
    private val displayWidth = 800f  // 需要根据实际情况设置
    private val displayHeight = 600f // 需要根据实际情况设置

    init {

    }

    val roots: BasicUIBlock = object : BasicUIBlock(UIShape(displayWidth, displayHeight), UILayout(Arrangement.Vertical, 1, 0f, 0f)) {
        override fun render(batch: SpriteBatch, font: BitmapFont) {}
    }


    fun addRoot(ui: BasicUIBlock) {
        roots.addUIChild(ui)
    }

    fun update() {
        roots.update()
    }

    fun render() {
        roots.renderAll(batch, font)
    }

    fun touchDown(TouchX: Float, TouchY: Float) {
    }

}
