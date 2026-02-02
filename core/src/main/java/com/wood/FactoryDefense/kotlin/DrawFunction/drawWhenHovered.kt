package com.wood.FactoryDefense.kotlin.DrawFunction

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.Main.Companion.curveBlockHovered
import com.wood.FactoryDefense.Main.Companion.curveItemHovered
import com.wood.FactoryDefense.Main.Companion.indexOut
import com.wood.FactoryDefense.Main.Companion.isBlockHovered
import com.wood.FactoryDefense.Main.Companion.isItemHovered
import com.wood.FactoryDefense.Main.Companion.lastHoveredItem
import com.wood.FactoryDefense.Main.Companion.lastHoveredX
import com.wood.FactoryDefense.Main.Companion.lastHoveredY
import com.wood.FactoryDefense.createTransparentTexture
import com.wood.FactoryDefense.kotlin.Block.BlockFactory.createBlockById
import com.wood.FactoryDefense.kotlin.Item.ItemLayout
import com.wood.FactoryDefense.kotlin.StaticData.Data.choose
import com.wood.FactoryDefense.kotlin.StaticData.Data.*
import com.wood.FactoryDefense.kotlin.StaticData.Data.mouseX
import com.wood.FactoryDefense.kotlin.StaticData.Data.mouseY
import com.wood.FactoryDefense.kotlin.StaticData.Data.worldMap


/**
 * drawHovered() - 绘制方块悬停效果
 * 私有方法，为悬停的方块添加高亮或预览效果
 */
fun whenHovered(batch: SpriteBatch) {
    // 遍历所有区块和方块
    for (x in 0 until worldMap.width) {
        for (y in 0 until worldMap.width) {
            drawHovered(x, y, batch, indexOut)
        }
    }
    for (index in 0 until worldMap.items.size){
        indexOut = index
        drawHoveredItem(batch, index)
    }
}
fun drawHovered(x: Int, y: Int, batch: SpriteBatch, index: Int){

    // 如果方块被悬停
    if (worldMap.blocks[x][y].isHovered && !isItemHovered) {
        isBlockHovered = true

        // 如果悬停的方块发生了变化（不是同一个方块）
        if (!(lastHoveredX == x && lastHoveredY == y)) {
            curveBlockHovered.reStart()
        }

        drawHoveredTexture(batch, x, y)

        drawHoveredText(batch, x, y, index)
    }
}
fun drawHoveredTexture(batch: SpriteBatch, x: Int,y: Int) {
    // 如果没有选择任何方块，绘制悬停高亮
    if (choose.name == "NULL") {
        batch.setColor(1f, 1f, 1f, ((curveBlockHovered.tureValue) / 150).toFloat())
        batch.draw(createTransparentTexture(32, 32,1f,1f,1f,1f), x.toFloat()*32f, y.toFloat()*32f)
        batch.setColor(1f, 1f, 1f, 1f)  // 恢复不透明度
    } else {
        // 如果有选择的方块，绘制该方块的预览（半透明）
        batch.setColor(1f, 1f, 1f, ((curveBlockHovered.tureValue) / 100).toFloat())

        val imagineBlock = createBlockById(choose.ID)

        imagineBlock.updateSelfAndNeighbors(x, y)

        imagineBlock.render(x.toFloat()*32f, y.toFloat()*32f, batch)
        batch.setColor(1f, 1f, 1f, ((curveBlockHovered.tureValue) / 250).toFloat())
        batch.draw(createTransparentTexture(32, 32,1f,1f,1f,1f), x.toFloat()*32f, y.toFloat()*32f)
        batch.setColor(1f, 1f, 1f, 1f)  // 恢复不透明度
    }
    // 记录当前悬停的方块位置（用于下次判断是否变化）
    if (mouseX in x.toFloat()*32f..(x.toFloat()*32f + 32f) && mouseY in y.toFloat()*32f..(y.toFloat()*32 + 32f)) {
        lastHoveredX = x
        lastHoveredY = y
    }
}
fun drawHoveredItem(batch: SpriteBatch, index: Int) {
    val item = worldMap.items[index]
    itemLayout = item.layout

    // 计算一次悬停状态，避免重复计算
    val isHovered = itemLayout.x < mouseX && itemLayout.x + 16f > mouseX &&
                   itemLayout.y < mouseY && itemLayout.y + 16f > mouseY

    // 更新悬停状态
    worldMap.items[index].isHovered = isHovered

    if (isHovered) {
        isItemHovered = true

        if (lastHoveredItem != itemLayout) {
            curveItemHovered.reStart()
        }

        batch.setColor(1f, 1f, 1f, ((curveItemHovered.tureValue) / 250).toFloat())
//        batch.setColor(1f, 1f, 1f,1f)
        batch.draw(createTransparentTexture(16, 16, 1f, 1f, 1f, 1f), itemLayout.x, itemLayout.y)
        batch.setColor(1f, 1f, 1f, 1f)
    }else{
        isItemHovered = false
    }

    if (itemLayout.x < mouseX && itemLayout.x + 16f > mouseX &&
        itemLayout.y < mouseY && itemLayout.y + 16f > mouseY) {
        lastHoveredItem = itemLayout
    }
}

fun drawHoveredText(batch: SpriteBatch, x: Int, y: Int,index: Int) {
    if (isBlockHovered) {
        // 绘制悬停方块的名字（在鼠标位置显示）
        if ((mouseX in x.toFloat() * 32f..(x.toFloat() * 32f + 32f) && mouseY in y.toFloat() * 32f..(y.toFloat() * 32 + 32f))) {
            font.draw(batch, worldMap.blocks[x][y].toString(), mouseX, mouseY)
        }
    }else if (isItemHovered){
        if (mouseX in worldMap.items[index].layout.x..(worldMap.items[index].layout.x + 16f) && mouseY in worldMap.items[index].layout.y..(worldMap.items[index].layout.y + 16f)) {
            font.draw(batch, worldMap.items[index].toString(), mouseX, mouseY)
        }
    }
}
