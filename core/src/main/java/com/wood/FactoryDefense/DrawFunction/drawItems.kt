package com.wood.FactoryDefense.DrawFunction

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.StaticData.Data.*

fun drawItems(batch: SpriteBatch) {
    worldMap.items.forEach { bundle ->
        bundle.render(batch)
    }
}

