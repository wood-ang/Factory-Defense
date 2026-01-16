package com.wood.FactoryDefense.kotlin.Block

import com.wood.FactoryDefense.StaticData.*

class BaseBuildingBlock internal constructor() : BaseBlock() {
    public override fun flasher() {
        print("[${times}] 刷新\n")
        times+=1
    }

    public override fun AfterBuild() {
    }

    public override fun BeforeBroke() {
    }

    init {
        canDestroyed = true
    }
}
