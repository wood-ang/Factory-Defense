package com.wood.FactoryDefense.kotlin.DrawFunction

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.Main.Companion.isItemHovered
import com.wood.FactoryDefense.createTransparentTexture
import com.wood.FactoryDefense.kotlin.Curve.ThreadCurve
import com.wood.FactoryDefense.kotlin.Item.ItemLayout
import com.wood.FactoryDefense.kotlin.StaticData.Data.*

fun drawItems(batch: SpriteBatch) {
    worldMap.items.forEach { bundle ->
        bundle.render(batch)
    }
}

