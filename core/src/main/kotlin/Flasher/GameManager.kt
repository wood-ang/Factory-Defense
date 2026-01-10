package Flasher

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wood.FactoryDefense.StaticData.GameManagerFPS

class GameManager {

    companion object: Runnable {

        var GameManagerFPS_true: Long = 0
        override fun run() {
            while(true) {
                val StartTime = System.currentTimeMillis()
                //***************************************************

                //***************************************************
                Thread.sleep(1000/ GameManagerFPS)
                GameManagerFPS_true = (1000/(System.currentTimeMillis() - StartTime))
            }
        }
    }
}
