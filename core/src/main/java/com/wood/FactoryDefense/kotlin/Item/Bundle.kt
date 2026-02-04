package com.wood.FactoryDefense.kotlin.Item

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.kotlin.StaticData.Data.CuverSmoothingParameter

class Bundle(val item: BasicItem, var layout: ItemLayout, var mass_g: Int = 1, var InternalEnergy_J : Int = 0) {
    var isHovered = false
    fun updata(){
        layout.x_ture += (layout.x - layout.x_ture) * 0.1f
        layout.y_ture += (layout.y - layout.y_ture) * 0.1f
    }
    fun render(batch: SpriteBatch){
        item.render(layout.x_ture,layout.y_ture,layout.angle_ture,batch)
    }
    override fun toString(): String {
        return "[item]\n${item.toIndentString(1)}\n[layout]$layout\n[mass g]$mass_g\n[InternalEnergy j]$InternalEnergy_J)"
    }

}
