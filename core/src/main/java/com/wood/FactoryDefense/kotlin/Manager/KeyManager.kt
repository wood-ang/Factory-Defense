package com.wood.FactoryDefense.kotlin.Manager

import com.badlogic.gdx.InputProcessor
import com.wood.FactoryDefense.StaticData.*


fun KeyW(){
    fontY += 15f
}
fun KeyS(){
    fontY -= 15f
}
fun KeyA(){
    fontX -= 15f
}
fun KeyD(){
    fontX += 15f
}
fun Scrolled(amountX: Float, amountY: Float){
    val newZoom = cameraZoom + amountY * 0.25f

    if (newZoom in cameraZoomMin..cameraZoomMax) {
        cameraZoom = newZoom
    } else if (newZoom < cameraZoomMin) {
        cameraZoom = cameraZoomMin
    } else {
        cameraZoom = cameraZoomMax
    }
}
class Processor : InputProcessor {

    override fun keyDown(keycode: Int): Boolean {
        return true
    }

    override fun keyUp(keycode: Int): Boolean {
        return true
    }

    override fun keyTyped(character: Char): Boolean {
        return true
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return true
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return true
    }

    override fun touchCancelled(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return true
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        return true
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        return true
    }

    override fun scrolled(amountX: Float, amountY: Float): Boolean {
        Scrolled(amountX, amountY)
        return true
    }

}
