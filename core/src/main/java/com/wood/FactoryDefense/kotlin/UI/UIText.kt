package com.wood.FactoryDefense.kotlin.UI

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.actions.Actions.layout
import com.wood.FactoryDefense.kotlin.StaticData.StaticData.ninePatch

class UIText(shape: UIShape, layout: UILayout, val text: String): BasicUIBlock(shape, layout) {

    override fun render(batch: SpriteBatch,font: BitmapFont) {
        // 绘制文本
        batch.setColor(1f, 1f, 1f, shape.transparency_true)
        font.draw(batch, text, layout.x_ture, layout.y_ture)
        batch.setColor(1f, 1f, 1f, 1f)  // 恢复不透明度
    }
}
