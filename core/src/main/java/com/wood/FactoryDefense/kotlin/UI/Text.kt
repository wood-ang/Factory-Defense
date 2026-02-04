package com.wood.FactoryDefense.kotlin.UI

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Text(shape: Shape, layout: UILayout, val text: String): BasicUIBlock(shape, layout) {

    override fun render(batch: SpriteBatch,font: BitmapFont) {
        // 绘制文本
        font.setColor(1f, 1f, 1f, shape.transparency_true)
        font.draw(batch, text, layout.x_ture, layout.y_ture + font.lineHeight)
        font.setColor(1f, 1f, 1f, 1f)  // 恢复不透明度
    }
}
