package com.wood.FactoryDefense.UI

import com.wood.FactoryDefense.StaticData.Data.*
import com.wood.FactoryDefense.StaticTools.toStringTool
import com.wood.FactoryDefense.Curve.CurveData
import com.wood.FactoryDefense.UI.Arrangement.*
import com.wood.FactoryDefense.UI.HorizontalAlignment.*

class Shape(
    xFloat: Float,
    yFloat: Float,
    var transparency: CurveData = CurveData(1f),
) {
    var width: CurveData = CurveData(xFloat)
    var height: CurveData = CurveData(yFloat)

    fun update() {
        width.update()
        height.update()
        transparency.update()
    }

    fun setWidth(width: Float) {
        this.width.data = width
    }

    fun setHeight(height: Float) {
        this.height.data = height
    }

    fun setTransparency(transparency: Float) {
        if (transparency in 0.0..1.0) {
            this.transparency.data = transparency
        }else if (transparency < 0.0){
            this.transparency.data = 0.0f
        }else {
            this.transparency.data = 1.0f
        }
    }

    fun getWidth() = width.data


    fun getWidth_ture() = width.data_ture

    fun getHeight(): Float = height.data

    fun getHeight_ture() = height.data_ture

    fun getTransparency(): Float {
        return transparency.data
    }

    fun getTransparency_true(): Float {
        return transparency.data_ture
    }

    override fun toString(): String {
        return toStringTool(width, "width") +
            toStringTool(height, "height") +
            toStringTool(transparency, "transparency") +
            toStringTool(getWidth_ture() , "width_ture") +
            toStringTool(getHeight_ture() , "height_ture") +
            toStringTool(getTransparency_true() , "transparency_true")
    }


    fun contains(layout: UILayout): Boolean = mouseX >= layout.xBottomLeft.data_ture && mouseX <= layout.xTopRight.data_ture + getWidth_ture() && mouseY >= layout.yBottomLeft.data_ture && mouseY <= layout.yTopRight.data_ture + getHeight_ture()
}
