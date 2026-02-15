package com.wood.FactoryDefense.UI

import com.wood.FactoryDefense.Curve.CurveData
import com.wood.FactoryDefense.UI.Padding.Companion.horizontal
import com.wood.FactoryDefense.UI.Padding.Companion.vertical
import java.util.*

/**
 * 表示内边距的类，所有值以float为单位
 */
class Padding(top: Float, right: Float, bottom: Float, left: Float) {
    private var top: CurveData = CurveData(top)
    private var right: CurveData = CurveData(right)
    private var bottom: CurveData = CurveData(bottom)
    private var left: CurveData = CurveData(left)

    fun update() {
        top.update()
        right.update()
        bottom.update()
        left.update()
    }

    /**
     * 创建所有边都相同的的内边距
     * @param all 所有边的值
     */
    // ========== 构造方法 ==========
    /**
     * 创建所有边都为0的内边距
     */
    @JvmOverloads
    constructor(all: Float = 0f) : this(all, all, all, all)

    /**
     * 创建垂直和水平对称的内边距
     * @param vertical 上下边的值
     * @param horizontal 左右边的值
     */
    constructor(vertical: Float, horizontal: Float) : this(vertical, horizontal, vertical, horizontal)

    // ========== Getter方法 ==========
    fun getTop(): Float {
        return top.data_ture
    }

    fun getRight(): Float {
        return right.data_ture
    }

    fun getBottom(): Float {
        return bottom.data_ture
    }

    fun getLeft(): Float {
        return left.data_ture
    }

    val average: Float
        /**
         * 获取所有边的平均值
         * @return 四边平均值
         */
        get() = (top.data_ture + right.data_ture + bottom.data_ture + left.data_ture) / 4f

    // ========== Setter方法 ==========
    fun setTop(top: Float) {
        this.top.data = top
    }

    fun setRight(right: Float) {
        this.right.data = right
    }

    fun setBottom(bottom: Float) {
        this.bottom.data = bottom
    }

    fun setLeft(left: Float) {
        this.left.data = left
    }

    /**
     * 设置所有边的值
     * @param all 所有边的值
     */
    fun setAll(all: Float) {
        this.top.data = all
        this.right.data = all
        this.bottom.data = all
        this.left.data = all
    }

    // ========== 实用方法 ==========
    val isSymmetric: Boolean
        /**
         * 检查是否对称（上下相等且左右相等）
         * @return 如果对称则返回true
         */
        get() = top == bottom && right == left

    val isUniform: Boolean
        /**
         * 检查是否所有边都相等
         * @return 如果所有边都相等则返回true
         */
        get() = top == right && right == bottom && bottom == left

    override fun toString(): String {
        return if (this.isUniform) {
            String.format("Padding(all=%.2f)", top.data)
        } else if (this.isSymmetric) {
            String.format("Padding(vertical=%.2f, horizontal=%.2f)", top.data, right.data)
        } else {
            String.format(
                "Padding(top=%.2f, right=%.2f, bottom=%.2f, left=%.2f)",
                top.data, right.data, bottom.data, left.data
            )
        }
    }

    companion object {
        // ========== 工厂方法 ==========
        /**
         * 创建对称的内边距
         * @param value 所有边的值
         * @return 新的Padding对象
         */
        fun symmetric(value: Float): Padding {
            return Padding(value)
        }

        /**
         * 创建仅水平方向有内边距
         * @param horizontal 左右边距
         * @return 新的Padding对象
         */
        fun horizontal(horizontal: Float): Padding {
            return Padding(0f, horizontal, 0f, horizontal)
        }

        /**
         * 创建仅垂直方向有内边距
         * @param vertical 上下边距
         * @return 新的Padding对象
         */
        fun vertical(vertical: Float): Padding {
            return Padding(vertical, 0f, vertical, 0f)
        }
    }
}
