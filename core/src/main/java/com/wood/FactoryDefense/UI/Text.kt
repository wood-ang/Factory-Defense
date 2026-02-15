package com.wood.FactoryDefense.UI

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Text(shape: Shape, layout: UILayout, val text: String): BasicUI(shape, layout) {



    override fun render(batch: SpriteBatch,font: BitmapFont) {
        // 绘制文本
        font.setColor(1f, 1f, 1f, shape.transparency.data_ture)

        font.draw(batch, text, layout.xBottomLeft.data_ture, layout.yBottomLeft.data_ture)

        font.setColor(1f, 1f, 1f, 1f)  // 恢复不透明度
    }
}
