package com.wood.FactoryDefense.kotlin.Item

class Bundle(val item: BasicItem,var mass_g: Int = 1,var InternalEnergy_N : Int = 0) {
    open fun updata(){}
}
