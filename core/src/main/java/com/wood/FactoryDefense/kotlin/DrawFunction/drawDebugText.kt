package com.wood.FactoryDefense.kotlin.DrawFunction

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Matrix4
import com.wood.FactoryDefense.Main.Companion.curveBlockHovered
import com.wood.FactoryDefense.Main.Companion.curveItemHovered
import com.wood.FactoryDefense.kotlin.Manager.GameManager.Companion.GameManagerFPS_true
import com.wood.FactoryDefense.kotlin.StaticData.Data.CurveManagerFPS_true
import com.wood.FactoryDefense.kotlin.StaticData.Data.debug
import com.wood.FactoryDefense.kotlin.StaticData.Data.font
import com.wood.FactoryDefense.kotlin.StaticData.Data.fontX_ture
import com.wood.FactoryDefense.kotlin.StaticData.Data.fontY_ture
import com.wood.FactoryDefense.kotlin.StaticData.UIData.uiManager


/**
 * drawDebugText() - 绘制调试信息
 * 私有方法，显示FPS、鼠标位置等调试信息
 */
fun drawDebugText(batch: SpriteBatch) {
    // 如果调试模式开启，显示FPS信息
    if (debug) {
        font.draw(
            batch,
            "[GameManagerFPS] $GameManagerFPS_true \n" +
                "[CurveManagerFPS] $CurveManagerFPS_true\n" +
                "[curveBlockHovered] "+String.format("%.4f", curveBlockHovered.tureValue)+"\n"+
                "[curveItemHovered] "+String.format("%.4f", curveItemHovered.tureValue),  // 曲线管理器FPS
            fontX_ture,  // 位置：相机中心X
            fontY_ture   // 位置：相机中心Y
        )
    }

    // 切换到屏幕坐标系（用于绘制UI和调试信息）
    // 创建一个正交2D矩阵，原点在左下角
    batch.projectionMatrix = Matrix4().setToOrtho2D(
        0f,
        0f,
        Gdx.graphics.width.toFloat(),  // 屏幕宽度
        Gdx.graphics.height.toFloat()  // 屏幕高度
    )

    // 恢复字体大小为原始大小（不受相机缩放影响）
    font.data.setScale(1f)

    // 绘制UI（UI管理器会处理所有UI元素的绘制）
    uiManager.render()
}
