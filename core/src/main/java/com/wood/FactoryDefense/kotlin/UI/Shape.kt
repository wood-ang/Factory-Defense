package com.wood.FactoryDefense.kotlin.UI

import com.wood.FactoryDefense.kotlin.StaticData.Data.*


class Shape(
    var width: Float,
    var height: Float,
    var transparency: Float = 1f
) {
    var width_ture: Float = width
    var height_ture: Float = height
    var transparency_true: Float = transparency
    fun update() {
        width_ture += (width - width_ture)*CuverSmoothingParameter
        height_ture += (height - height_ture)*CuverSmoothingParameter
        transparency_true += (transparency - transparency_true)*CuverSmoothingParameter
    }
    fun contains(px: Float, py: Float, layout: UILayout): Boolean {
        return px >= layout.x && px <= layout.x + width &&
            py >= layout.y && py <= layout.y + height
    }
}
