package com.wood.FactoryDefense

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.ScreenUtils
import com.wood.FactoryDefense.kotlin.StaticData.StaticData.*
import com.wood.FactoryDefense.kotlin.Block.NULL
import com.wood.FactoryDefense.kotlin.Block.WorldMap
import com.wood.FactoryDefense.kotlin.Curve.ThreadCurve
import com.wood.FactoryDefense.kotlin.Manager.CurveManager
import com.wood.FactoryDefense.kotlin.Manager.GameManager
import com.wood.FactoryDefense.kotlin.Manager.Processor
import com.wood.FactoryDefense.kotlin.Manager.UIManager
import com.wood.FactoryDefense.kotlin.StaticData.StaticUIData.*
import com.wood.FactoryDefense.kotlin.UI.Arrangement
import com.wood.FactoryDefense.kotlin.UI.UILayout
import com.wood.FactoryDefense.kotlin.UI.UIPanel
import com.wood.FactoryDefense.kotlin.UI.UIShape

class Main : ApplicationAdapter() {

    lateinit var camera: OrthographicCamera
    lateinit var batchDraw: SpriteBatch
    lateinit var image: Texture
    lateinit var hovered: Texture
    lateinit var font: BitmapFont
    lateinit var cameraCenter: Texture
    private val inputProcessor: Processor = Processor()
    val curve = ThreadCurve(3000, 1.0, 100.0,0.2,20)

    lateinit var mouse: Vector3


    override fun create() {

        // --- 初始化 ---
        initialization()

        // --- 启动线程 ---
        createThreads()

        // --- 创建UI ---
        createUI()
    }

    private fun initialization(){

        // --- 初始化核心资源 ---
        camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        image = Texture("libgdx.png")
        cameraCenter = Texture("BaseBuild.png")
        textureAir = createTransparentTexture(32, 32)
        textureBaseBlock = Texture("BaseBuild.png")
        textureStone = Texture("Stone.png")
        textureUIPanle = Texture("UIPanel.png")
        textureRegion = TextureRegion(textureUIPanle)
        ninePatch = NinePatch(textureRegion)
        ninePatch.setPadding(20f, 20f, 20f, 20f)
        debugFontY.data = 300f

        // 初始化相机并设置视口大小
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f)
        camera.update()

        // 初始化SpriteBatch和字体
        batchDraw = SpriteBatch()
        font = BitmapFont() // 默认字体

        // --- 初始化地图 ---
        worldMap = WorldMap(10, 10)
        worldMap.respawnPointX = 256f
        worldMap.respawnPointY = 256f
        fontX = worldMap.respawnPointX
        fontY = worldMap.respawnPointY

        // --- 其余初始化 ---
        hovered = Texture("Hovered.png")
        choose = NULL()
        Gdx.input.setInputProcessor(inputProcessor)


    }

    private fun createThreads() {
        Thread(GameManager()).start()
        Thread(CurveManager()).start()
    }

    private fun createUI(){
        uiManager = UIManager(batchDraw)

        // 创建一个面板
        val panel = UIPanel(UIShape(400f, 300f), UILayout(Arrangement.Vertical, 1, x = 8f,y = 8f))

        uiManager.addRoot(panel)

    }

    override fun resize(width: Int, height: Int) {
        // 调整视口大小
        camera.viewportWidth = width.toFloat()
        camera.viewportHeight = height.toFloat()
        camera.update() // 重新计算相机的矩阵

        displayWidth = width.toFloat()
        displayHeight = height.toFloat()
    }

    var lastHovered1 = 0
    var lastHovered2 = 0

    override fun render() {

        // 更新相机
        camera.zoom = cameraZoom_ture
        camera.position.set(
            fontX_ture,
            fontY_ture,
            0f
        )
        camera.update()

        // 清屏
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f)

        // 设置投影矩阵
        batchDraw.projectionMatrix = camera.combined

        // 开始绘制前重置颜色为不透明
        batchDraw.setColor(1f, 1f, 1f, 1f)

        // 开始绘制
        batchDraw.begin()

        // 字符大小调整
        font.data.setScale(cameraZoom_ture * 2f)

        mouse = Vector3(
            Gdx.input.x.toFloat(),
            Gdx.input.y.toFloat(),
            0f
        )



        camera.unproject(mouse)

        mouseX = mouse.x
        mouseY = mouse.y


        // --- 世界 ---

        // 绘制方块
        drawTexture()

        // 绘制hovered
        drawHovered()

        // 绘制debug文字
        drawDebugText()

        // --- 世界中的文字 ---

        batchDraw.end()

    }


    private fun drawTexture() {
        for (i1 in 0 until worldMap.size()) {
            for (i2 in 0 until worldMap.getByIndex(i1).size()) {

                // 获取当前方块坐标
                val x = ((worldMap.indexToCoordinate(i1).x * 16) + worldMap.chunks[i1].indexToCoordinate(i2).x).toFloat() * 32f
                val y = ((worldMap.indexToCoordinate(i1).y * 16) + worldMap.chunks[i1].indexToCoordinate(i2).y).toFloat() * 32f

                // 绘制texture
                batchDraw.draw(
                    worldMap.chunks[i1].blocks[i2].texture, x, y
                )

                //判断是否hovered，并设置hovered
                if (mouseX in x..(x + 32f) && mouseY in y..(y + 32f)) {
                    worldMap.chunks[i1].blocks[i2].isHovered = true
                } else {
                    worldMap.chunks[i1].blocks[i2].isHovered = false
                }
            }
        }
    }
    private fun drawHovered() {

        for (i1 in 0 until worldMap.size()) {
            for (i2 in 0 until worldMap.getByIndex(i1).size()) {
                // 获取当前方块坐标
                val x = ((worldMap.indexToCoordinate(i1).x * 16) + worldMap.chunks[i1].indexToCoordinate(i2).x).toFloat() * 32f
                val y = ((worldMap.indexToCoordinate(i1).y * 16) + worldMap.chunks[i1].indexToCoordinate(i2).y).toFloat() * 32f
                if (worldMap.chunks[i1].blocks[i2].isHovered) {

                    Thread(curve).start()
                    if (!(lastHovered1 == i1 && lastHovered2 == i2)) {
                        curve.reStart()
                    }

                    // 绘制hovered
                    batchDraw.setColor(1f, 1f, 1f, ((curve.tureValue)/100).toFloat())

                    if (choose.name == "NULL") {
                        batchDraw.draw(hovered, x, y)
                    }else{
                        batchDraw.setColor(1f, 1f, 1f, ((curve.tureValue)/200).toFloat())
                        batchDraw.draw(choose.texture, x, y)
                        batchDraw.setColor(1f, 1f, 1f, 1f)
                    }

                    batchDraw.setColor(1f, 1f, 1f, 1f)
                }

                // 绘制hovered方块的状态文字
                if (mouseX in x..(x + 32f) && mouseY in y..(y + 32f)) {
                    font.draw(batchDraw, worldMap.chunks[i1].blocks[i2].name, mouseX, mouseY)
                }

                if (mouseX in x..(x + 32f) && mouseY in y..(y + 32f)) {
                    lastHovered1 = i1
                    lastHovered2 = i2
                }
            }
        }
    }
    private fun drawDebugText() {
        if (debug) {
            font.draw(
                batchDraw,
                "[GameManagerFPS] ${GameManager.GameManagerFPS_true}\n" +
                    "[CurveManagerFPS] ${CurveManagerFPS_true}",
                fontX_ture,
                fontY_ture
            )
        }


        //调整batch
        batchDraw.projectionMatrix = Matrix4().setToOrtho2D(0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        font.data.setScale(1f)

        uiManager.render()


        font.draw(
            batchDraw,
            "[MouseWorld](${mouse.x.toInt()}, ${mouse.y.toInt()})",
            12f,
            debugFontY.data_ture
        )

    }

    override fun dispose() {
        // 释放资源
        batchDraw.dispose()
        image.dispose()
        font.dispose()
        Gdx.input.inputProcessor = null
    }
}


private fun createTransparentTexture(width: Int, height: Int): Texture {
    if (!Gdx.files.internal("transparent.png").exists()) {
        // 创建透明 Pixmap
        val pixmap = Pixmap(width, height, Pixmap.Format.RGBA8888)
        pixmap.setColor(0f, 0f, 0f, 0f)
        pixmap.fill()
        val texture = Texture(pixmap)
        pixmap.dispose()
        return texture
    } else {
        return Texture("transparent.png")
    }
}

