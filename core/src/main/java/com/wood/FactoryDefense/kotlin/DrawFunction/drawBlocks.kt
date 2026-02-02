package com.wood.FactoryDefense.kotlin.DrawFunction

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.kotlin.StaticData.Data.mouseX
import com.wood.FactoryDefense.kotlin.StaticData.Data.mouseY
import com.wood.FactoryDefense.kotlin.StaticData.Data.worldMap


/**
 * drawTexture() - 绘制世界中的所有方块
 * 私有方法，遍历并绘制每个方块
 */
fun drawBlocks(batch: SpriteBatch) {
    // 遍历所有区块
    for (x in 0 until worldMap.width) {
        // 遍历当前区块内的所有方块
        for (y in 0 until worldMap.width) {
            // 计算方块在世界坐标系中的坐标
            // 公式：世界坐标 = (区块坐标 * 16 + 区块内坐标) * 32
            // 32是一个方块的像素大小
            // 绘制方块的纹理
            worldMap.blocks[x][y].render(x*32f, y*32f, batch)
            // 判断鼠标是否悬停在此方块上
            // 通过检查鼠标坐标是否在方块区域内
            if (mouseX in x.toFloat()*32f..(x.toFloat()*32f + 32f) && mouseY in y.toFloat()*32f..(y.toFloat()*32 + 32f)) {
                worldMap.blocks[x][y].isHovered = true  // 标记为悬停
            } else {
                worldMap.blocks[x][y].isHovered = false // 清除悬停标记
            }
        }
    }
}
