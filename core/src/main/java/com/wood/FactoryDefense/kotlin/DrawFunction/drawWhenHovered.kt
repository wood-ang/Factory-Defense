package com.wood.FactoryDefense.kotlin.DrawFunction

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.Main.Companion.curveBlockHovered
import com.wood.FactoryDefense.Main.Companion.curveItemHovered
import com.wood.FactoryDefense.Main.Companion.indexOut
import com.wood.FactoryDefense.Main.Companion.isBlockHovered
import com.wood.FactoryDefense.Main.Companion.isItemHovered
import com.wood.FactoryDefense.Main.Companion.lastHoveredItemLayout
import com.wood.FactoryDefense.Main.Companion.lastHoveredX
import com.wood.FactoryDefense.Main.Companion.lastHoveredY
import com.wood.FactoryDefense.createTransparentTexture
import com.wood.FactoryDefense.kotlin.Block.BlockFactory.createBlockById
import com.wood.FactoryDefense.kotlin.StaticData.Data.choose
import com.wood.FactoryDefense.kotlin.StaticData.Data.*
import com.wood.FactoryDefense.kotlin.StaticData.Data.mouseX
import com.wood.FactoryDefense.kotlin.StaticData.Data.mouseY
import com.wood.FactoryDefense.kotlin.StaticData.Data.worldMap



var hoveredBlockX: Int = 0
var hoveredBlockY: Int = 0
var hoveredItemIndex: Int = 0
/**
 * drawHovered() - 绘制方块悬停效果
 * 私有方法，为悬停的方块添加高亮或预览效果
 */
fun whenHovered(batch: SpriteBatch) {
    // 遍历所有区块和方块
    for (x in 0 until worldMap.width) {
        for (y in 0 until worldMap.width) {
            drawHovered(x, y, batch)
        }
    }
    isItemHovered = false
    for (index in 0 until worldMap.items.size){
        indexOut = index
        drawHoveredItem(batch, index)
    }
    if (debug) drawHoveredText(batch)
}
fun drawHovered(x: Int, y: Int, batch: SpriteBatch){

    // 如果方块被悬停
    if (worldMap.blocks[x][y].isHovered && !isItemHovered) {
        isBlockHovered = true

        // 如果悬停的方块发生了变化（不是同一个方块）
        if (!(lastHoveredX == x && lastHoveredY == y)) {
            curveBlockHovered.reStart()
        }

        drawHoveredTexture(batch, x, y)

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
        hoveredBlockX = x
        hoveredBlockY = y
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

        hoveredItemIndex = index

        if (lastHoveredItemLayout != itemLayout) {
            curveItemHovered.reStart()
        }

        batch.setColor(1f, 1f, 1f, ((curveItemHovered.tureValue) / 150).toFloat())
        batch.draw(createTransparentTexture(16, 16, 1f, 1f, 1f, 1f), itemLayout.x, itemLayout.y)
        batch.setColor(1f, 1f, 1f, 1f)
    }

    if (itemLayout.x < mouseX && itemLayout.x + 16f > mouseX && itemLayout.y < mouseY && itemLayout.y + 16f > mouseY) {
        lastHoveredItemLayout = itemLayout
    }
}

fun drawHoveredText(batch: SpriteBatch) {
    if (isBlockHovered && !isItemHovered) {
        font.draw(batch, worldMap.blocks[hoveredBlockX][hoveredBlockY].toString(), mouseX, mouseY)
    }
    if (isItemHovered){
        font.draw(batch, worldMap.items[hoveredItemIndex].toString(), mouseX, mouseY)
    }
}
