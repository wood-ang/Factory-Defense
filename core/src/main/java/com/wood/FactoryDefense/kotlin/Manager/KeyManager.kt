package com.wood.FactoryDefense.kotlin.Manager

import com.badlogic.gdx.Input
import com.badlogic.gdx.Input.Keys.*
import com.badlogic.gdx.InputProcessor
import com.wood.FactoryDefense.kotlin.Block.Air
import com.wood.FactoryDefense.kotlin.StaticData.StaticData.*
import com.wood.FactoryDefense.kotlin.Block.BasicBuildingBlock
import com.wood.FactoryDefense.kotlin.Block.BlockFactory.createBlockById
import com.wood.FactoryDefense.kotlin.Block.Stone
import com.wood.FactoryDefense.kotlin.StaticData.StaticUIData.*

class Processor : InputProcessor {

    var chooseNumber: Int = 0
    override fun keyDown(keycode: Int): Boolean {
        when (keycode) {
            W -> {
                KeyW = true
            }
            S -> {
                KeyS = true
            }
            A -> {
                KeyA = true
            }
            D -> {
                KeyD = true
            }
            Q -> {
                KeyQ = true
            }
            TAB -> {
                chooseNumber = choose.ID + 1
                if (chooseNumber >= 4)chooseNumber = 1
                choose = createBlockById(chooseNumber)
            }
            P -> {
                debug = !debug
                if (debug){
                    uiManager.roots.children[0].shape.transparency = 1f
                    uiManager.roots.children[0].children[0].shape.transparency = 1f
                }else{
                    uiManager.roots.children[0].shape.transparency = 0f
                    uiManager.roots.children[0].children[0].shape.transparency = 0f
                }
            }
            V -> {
                fontX = worldMap.respawnPointX
                fontY = worldMap.respawnPointY
            }
            ESCAPE ->{
                choose = Air()
            }
        }
        return true
    }

    override fun keyUp(keycode: Int): Boolean {
        when (keycode) {
            W -> {
                KeyW = false
            }
            S -> {
                KeyS = false
            }
            A -> {
                KeyA = false
            }
            D -> {
                KeyD = false
            }
            Q -> {
                KeyQ = false
            }
        }
        return true
    }

    override fun keyTyped(character: Char): Boolean {
        return true
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        when (button) {
            Input.Buttons.LEFT -> mouseLeft = true
            Input.Buttons.RIGHT -> mouseRight = true
            Input.Buttons.MIDDLE -> mouseMIDDLE = true
        }
        uiManager.touchDown(mouseX, mouseY)
        return true
    }


    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        when (button) {
            Input.Buttons.LEFT -> mouseLeft = false
            Input.Buttons.RIGHT -> mouseRight = false
            Input.Buttons.MIDDLE -> mouseMIDDLE = false
        }
        uiManager.touchDown(mouseX, mouseY)
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
        val newZoom = cameraZoom + amountY * 0.25f

        if (newZoom in cameraZoomMin..cameraZoomMax) {
            cameraZoom = newZoom
        } else if (newZoom < cameraZoomMin) {
            cameraZoom = cameraZoomMin
        } else {
            cameraZoom = cameraZoomMax
        }
        return true
    }
}
