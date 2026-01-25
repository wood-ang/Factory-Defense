package com.wood.FactoryDefense.kotlin.UI

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.StaticData.*

/**
 * UI面板容器组件
 *
 * 继承自BasicUIBlock，作为其他UI元素的容器和背景面板。
 * 提供工业风格的视觉背景，常用于组织和管理一组相关UI控件，
 * 如设置菜单、信息面板、工具条等。
 */
class UIPanel(
    x: Float,              // 相对于父容器的X坐标
    y: Float,              // 相对于父容器的Y坐标
    w: Float,              // 面板宽度
    h: Float               // 面板高度
) : BasicUIBlock() {

    init {
        // 初始化面板的几何边界
        shape.x = x
        shape.y = y
        shape.width = w
        shape.height = h
    }

    /**
     * 渲染工业风格面板背景
     *
     * 实现UIBlock的渲染接口，绘制具有工业美学特征的面板背景。
     * 典型工业风格元素包括：金属质感、铆钉细节、边框高光、轻微磨损效果。
     *
     * @param batch SpriteBatch渲染批处理器，用于高效绘制2D图形
     * @param worldX 面板左下角在屏幕坐标系中的绝对X坐标（已计算父容器偏移）
     * @param worldY 面板左下角在屏幕坐标系中的绝对Y坐标（已计算父容器偏移）
     *
     * 工业风格实现建议：
     * 1. 使用深灰色金属纹理作为基底层
     * 2. 添加较浅的边框线条模拟面板边缘
     * 3. 在四角或等间距位置绘制铆钉细节
     * 4. 可叠加轻微噪声纹理或划痕增加真实感
     *
     * 示例实现框架：
     * ```
     * // 1. 绘制面板主体（金属底板）
     * batch.draw(metalTexture, worldX, worldY, shape.width, shape.height)
     *
     * // 2. 绘制边框（工业面板常见的外框）
     * val borderThickness = 3f
     * batch.draw(borderTexture, worldX, worldY, shape.width, borderThickness) // 下边框
     * batch.draw(borderTexture, worldX, worldY + shape.height - borderThickness,
     *            shape.width, borderThickness) // 上边框
     *
     * // 3. 绘制铆钉装饰（四个角）
     * val rivetSize = 8f
     * batch.draw(rivetTexture, worldX - rivetSize/2, worldY - rivetSize/2, rivetSize, rivetSize)
     * batch.draw(rivetTexture, worldX + shape.width - rivetSize/2, worldY - rivetSize/2,
     *            rivetSize, rivetSize)
     *
     * // 4. 可选：添加轻微磨损/油渍叠加层
     * batch.setColor(1f, 1f, 1f, 0.1f) // 半透明叠加
     * batch.draw(wearTexture, worldX, worldY, shape.width, shape.height)
     * batch.setColor(1f, 1f, 1f, 1f) // 恢复颜色
     * ```
     */
    override fun render(batch: SpriteBatch, worldX: Float, worldY: Float) {
        // 预留工业风格面板渲染实现
        // 建议将纹理资源管理移至单独类或通过参数注入

        // 参数：左、右、上、下的padding值（像素）
        ninePatch.setPadding(20f, 20f, 10f, 10f);

        // 示例代码结构：
        // 1. 绘制背景纹理
        // 2. 绘制边框和装饰元素
        // 3. 绘制子UI组件（如果面板作为容器）
    }
}
