package com.wood.FactoryDefense.kotlin.UI

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.kotlin.StaticData.Data.*

/**
 * UI面板容器组件
 *
 * 继承自BasicUIBlock，作为其他UI元素的容器和背景面板。
 * 提供工业风格的视觉背景，常用于组织和管理一组相关UI控件，
 * 如设置菜单、信息面板、工具条等。
 */
class Panel(   // 面板高度
    shape: Shape, layout: UILayout
) : BasicUIBlock(shape, layout) {

    override fun render(batch: SpriteBatch,font: BitmapFont) {
        batch.setColor(1f, 1f, 1f, shape.transparency_true)
        ninePatch.draw(batch, layout.y_ture ,layout.x_ture, shape.width_ture, shape.height_ture)
        batch.setColor(1f, 1f, 1f, 1f)  // 恢复不透明度
    }
}
