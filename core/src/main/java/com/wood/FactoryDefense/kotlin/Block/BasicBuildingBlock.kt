package com.wood.FactoryDefense.kotlin.Block

import com.wood.FactoryDefense.kotlin.StaticData.StaticData.*

class BasicBuildingBlock internal constructor() : BasicBlock() {
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
