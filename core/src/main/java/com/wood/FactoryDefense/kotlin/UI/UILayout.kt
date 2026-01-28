package com.wood.FactoryDefense.kotlin.UI

import com.wood.FactoryDefense.kotlin.UI.Arrangement.*


class UILayout(var arrangement: Arrangement,var weight: Int = 1,var x: Float,var y: Float) {

}

enum class Arrangement {
    Horizontal,
    Vertical
}
