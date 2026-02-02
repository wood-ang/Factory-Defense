package com.wood.FactoryDefense.kotlin.Block

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.kotlin.StaticData.StaticData.*

class BasicBuildingBlock internal constructor() : BasicBlock() {



     override fun render(x: Float, y: Float, batch: SpriteBatch){
         batch.draw(texture, x, y)
         if (!DIR_Top){
             batch.draw(textureBaseBuildFrame_T, x, y)
         }
         if (!DIR_Right){
             batch.draw(textureBaseBuildFrame_R, x, y)
         }
         if (!DIR_Bottom){
             batch.draw(textureBaseBuildFrame_B, x, y)
         }
         if (!DIR_Left){
             batch.draw(textureBaseBuildFrame_L, x, y)
         }
         if (!DIR_TopRight){
             batch.draw(textureBaseBuildFrame_TR, x, y)
         }
         if (!DIR_BottomRight){
             batch.draw(textureBaseBuildFrame_BR, x, y)
         }
         if (!DIR_BottomLeft){
             batch.draw(textureBaseBuildFrame_BL, x, y)
         }
         if (!DIR_TopLeft){
             batch.draw(textureBaseBuildFrame_TL, x, y)
         }
    }

    init{
        name= "BaseBuildingBlock"
        canDestroyed= true
        texture= textureBaseBlock
        ID= 2
    }

}
