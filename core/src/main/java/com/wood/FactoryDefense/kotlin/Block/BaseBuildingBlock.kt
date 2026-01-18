package com.wood.FactoryDefense.kotlin.Block

import com.badlogic.gdx.graphics.Texture
import com.wood.FactoryDefense.StaticData.*

class BaseBuildingBlock internal constructor() : BaseBlock() {
    public override fun flasher() {
    }

    public override fun AfterBuild() {
    }

    public override fun BeforeBroke() {
    }

    init{
        name= "BaseBuildingBlock"
        canDestroyed= true
        texture= textureBaseBlock
    }

}
