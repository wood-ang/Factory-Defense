package com.wood.FactoryDefense.UI

import com.wood.FactoryDefense.Curve.CurveData
import com.wood.FactoryDefense.StaticData.Data.CurveLaoderFPS
import com.wood.FactoryDefense.UI.Arrangement.*


class UILayout(
    var padding: Padding = Padding(0f),
    weightFloat: Float = 1f,
    var arrangement: Arrangement = Arrangement.Horizontal,
    var horizontalAlignment: HorizontalAlignment = HorizontalAlignment.HorizontalCenter,
    var verticalAlignment: VerticalAlignment = VerticalAlignment.VerticalCenter
) {
    var weight = CurveData(weightFloat)
    var xBottomLeft = CurveData(0f)
    var yBottomLeft = CurveData(0f)
    var xTopRight = CurveData(0f)
    var yTopRight = CurveData(0f)

    var width = CurveData(0f)
    var height = CurveData(0f)
    fun update() {
        weight.update()
        xBottomLeft.update()
        yBottomLeft.update()
        xTopRight.update()
        yTopRight.update()

        width.update()
        height.update()

        padding.update()
    }
}

enum class Arrangement {
    Horizontal,
    Vertical
}
enum class HorizontalAlignment{
    HorizontalLeft,
    HorizontalRight,
    HorizontalCenter
}
enum class VerticalAlignment{
    VerticalTop,
    VerticalBottom,
    VerticalCenter
}
