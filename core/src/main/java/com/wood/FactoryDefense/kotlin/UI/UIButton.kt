package com.wood.FactoryDefense.kotlin.UI

import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
 * UI按钮组件
 *
 * 继承自BasicUIBlock，实现一个可交互的文本按钮。
 * 负责管理按钮的几何形状、文本内容和渲染逻辑。
 * 通常用于菜单、对话框、控制面板等交互界面。
 */
class UIButton(
    var text: String,      // 按钮显示的文本内容
    x: Float,              // 相对于父容器的X坐标
    y: Float,              // 相对于父容器的Y坐标
    w: Float,              // 按钮宽度
    h: Float               // 按钮高度
) : BasicUIBlock() {

    init {
        // 初始化按钮的几何形状
        // 注意：所有坐标均为相对于父容器的局部坐标
        shape.x = x
        shape.y = y
        shape.width = w
        shape.height = h
    }

    /**
     * 渲染按钮到屏幕
     *
     * 实现UIBlock的渲染接口，将按钮绘制到指定世界位置。
     * 需要先绘制背景纹理，再叠加文本内容。
     *
     * @param batch SpriteBatch渲染批处理器，用于高效绘制2D图形
     * @param worldX 按钮在屏幕坐标系中的绝对X坐标（已包含父容器偏移）
     * @param worldY 按钮在屏幕坐标系中的绝对Y坐标（已包含父容器偏移）
     *
     * 实现说明：
     * 1. 使用batch.draw绘制按钮背景纹理
     * 2. 使用font.draw在按钮上绘制居中或对齐的文本
     * 3. 可根据按钮状态（正常/悬停/按下）切换不同纹理
     *
     * 示例实现（取消注释并替换yourTexture）：
     * ```
     * // 绘制按钮背景
     * batch.draw(buttonTexture, worldX, worldY, shape.width, shape.height)
     *
     * // 计算文本居中位置
     * val textWidth = font.getWidth(text)
     * val textHeight = font.getHeight(text)
     * val textX = worldX + (shape.width - textWidth) / 2
     * val textY = worldY + (shape.height + textHeight) / 2
     *
     * // 绘制按钮文本
     * font.draw(batch, text, textX, textY)
     * ```
     */
    override fun render(batch: SpriteBatch, worldX: Float, worldY: Float) {
        // worldX/worldY 是按钮左下角在屏幕上的绝对坐标
        // 此处预留渲染实现，需要添加纹理资源和字体管理

        // 示例渲染代码（需要先初始化纹理和字体）：
        // batch.draw(buttonTexture, worldX, worldY, shape.width, shape.height)
        // font.draw(batch, text, worldX + 10f, worldY + shape.height - 10f)


    }
}
