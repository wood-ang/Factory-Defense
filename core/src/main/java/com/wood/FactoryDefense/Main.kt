package com.wood.FactoryDefense

import Flasher.GameManager
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import Flasher.GameManager.Companion.GameManagerFPS_true

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms.  */
class Main : ApplicationAdapter() {
    private var batch: SpriteBatch? = null
    private var image: Texture? = null
    lateinit var font: BitmapFont


    override fun create() {
        val thread = Thread (GameManager)
        thread.start()



        batch = SpriteBatch()
        image = Texture("libgdx.png")
        batch = SpriteBatch()
        font = BitmapFont() // 默认字体
    }

    override fun render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f)
        batch!!.begin()

        font.draw(batch,"[GameManagerFPS_true]"+GameManagerFPS_true.toString() , 50f, 100f)
        batch!!.draw(image, 140f, 210f)

        batch!!.end()
    }

    override fun dispose() {
        batch!!.dispose()
        image!!.dispose()
    }
}
