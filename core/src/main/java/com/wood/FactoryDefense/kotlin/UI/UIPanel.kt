package com.wood.FactoryDefense.kotlin.UI

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.kotlin.StaticData.StaticData.*

/**
 * UI面板容器组件
 *
 * 继承自BasicUIBlock，作为其他UI元素的容器和背景面板。
 * 提供工业风格的视觉背景，常用于组织和管理一组相关UI控件，
 * 如设置菜单、信息面板、工具条等。
 */
class UIPanel(   // 面板高度
    shape: UIShape, layout: UILayout
) : BasicUIBlock(shape, layout) {

    override fun render(batch: SpriteBatch) {
        if (layout.x>1f && layout.y>1f){
            batch.setColor(1f, 1f, 1f, shape.transparency)
            ninePatch.draw(batch, layout.y ,layout.x, shape.width, shape.height)
        }
    }
}
