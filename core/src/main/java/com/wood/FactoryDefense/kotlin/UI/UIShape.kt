package com.wood.FactoryDefense.kotlin.UI

/**
 * UI形状基类，用于定义UI元素的基本矩形区域
 *
 * 该类封装了UI元素的几何信息，提供基础的坐标、尺寸管理和点包含检测功能。
 * 适用于按钮、面板、图标等需要边界检测的UI组件。
 *
 * 示例用法：
 * ```
 * val button = UIShape(x = 50f, y = 100f, width = 200f, height = 60f)
 * val isClicked = button.contains(touchX, touchY, parentX, parentY)
 * ```
 *
 * @property x UI元素左上角在其局部坐标系中的X坐标，默认0f
 * @property y UI元素左上角在其局部坐标系中的Y坐标，默认0f
 * @property width UI元素的宽度，默认100f
 * @property height UI元素的高度，默认50f
 */
class UIShape(
    var x: Float = 0f,      // 局部坐标X（相对于父元素或本地坐标系）
    var y: Float = 0f,      // 局部坐标Y（相对于父元素或本地坐标系）
    var width: Float = 100f,
    var height: Float = 50f
) {
    /**
     * 检测给定点是否在该UI形状的世界坐标区域内
     *
     * 将本地坐标转换为世界坐标后，进行边界检测。适用于触摸/点击事件处理。
     * 注意：libGDX坐标系原点通常位于屏幕左下角，而此处检测基于左上角坐标（符合UI惯例）。
     *
     * @param px 待检测点的X坐标（屏幕/世界坐标系）
     * @param py 待检测点的Y坐标（屏幕/世界坐标系）
     * @param worldX 该UI形状所在容器的世界X坐标（用于坐标转换）
     * @param worldY 该UI形状所在容器的世界Y坐标（用于坐标转换）
     * @return 如果点位于形状边界内（包含边界）则返回true，否则返回false
     *
     * 示例：
     * ```
     * // 检测触摸点(120, 130)是否在UI上
     * val isInside = uiShape.contains(120f, 130f, container.x, container.y)
     * ```
     */
    fun contains(px: Float, py: Float, worldX: Float, worldY: Float): Boolean {
        return px >= worldX && px <= worldX + width &&
            py >= worldY && py <= worldY + height
    }
}
