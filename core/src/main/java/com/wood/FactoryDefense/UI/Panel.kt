package com.wood.FactoryDefense.UI

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.StaticData.Data.*

/**
 * UI面板容器组件
 *
 * 继承自BasicUIBlock，作为其他UI元素的容器和背景面板。
 * 提供工业风格的视觉背景，常用于组织和管理一组相关UI控件，
 * 如设置菜单、信息面板、工具条等。
 */
class Panel(shape: Shape, layout: UILayout) : BasicUI(shape, layout) {

    override fun updateAll() {
        shape.update()
        layout.update()
        if (isHovered){
            shape.transparency.data = 0.5f
        }
    }

    override fun render(batch: SpriteBatch,font: BitmapFont) {
        batch.setColor(1f, 1f, 1f, shape.transparency.data_ture)



        batch.setColor(1f, 1f, 1f, 1f)  // 恢复不透明度
    }
}
