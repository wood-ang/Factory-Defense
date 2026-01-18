package com.wood.FactoryDefense

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.ScreenUtils
import com.wood.FactoryDefense.StaticData.*
import com.wood.FactoryDefense.kotlin.Block.NULL
import com.wood.FactoryDefense.kotlin.Block.WorldMap
import com.wood.FactoryDefense.kotlin.Chunk
import com.wood.FactoryDefense.kotlin.Curve.ThreadCurve
import com.wood.FactoryDefense.kotlin.Manager.CurveManager
import com.wood.FactoryDefense.kotlin.Manager.GameManager
import com.wood.FactoryDefense.kotlin.Manager.Processor

class Main : ApplicationAdapter() {

    lateinit var camera: OrthographicCamera
    lateinit var batchDraw: SpriteBatch
    lateinit var image: Texture
    lateinit var hovered: Texture
    lateinit var font: BitmapFont
    lateinit var cameraCenter: Texture
    private val inputProcessor: Processor = Processor()
    val curve = ThreadCurve(1000000, 1.0, 100.0,0.05,20)


    override fun create() {

        // --- 初始化核心资源 ---
        camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        image = Texture("libgdx.png")
        cameraCenter = Texture("BaseBuild.png")
        textureAir = createTransparentTexture(32, 32)
        textureBaseBlock = Texture("BaseBuild.png")
        textureStone = Texture("Stone.png")

        // 初始化相机并设置视口大小
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f)
        camera.update()


        // 初始化SpriteBatch和字体
        batchDraw = SpriteBatch()
        font = BitmapFont() // 默认字体

        // --- 初始化地图 ---
        worldMap = WorldMap(10, 10)

        // --- 其余初始化 ---
        hovered = Texture("Hovered.png")
        choose = NULL()
        Gdx.input.setInputProcessor(inputProcessor);


        // --- 启动线程 ---
        createThreads()

    }

    private fun createThreads() {
        Thread(GameManager()).start()
        Thread(CurveManager()).start()
    }

    override fun resize(width: Int, height: Int) {
        // 调整视口大小
        camera.viewportWidth = width.toFloat()
        camera.viewportHeight = height.toFloat()
        camera.update() // 重新计算相机的矩阵
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


        val mouse = Vector3(
            Gdx.input.x.toFloat(),
            Gdx.input.y.toFloat(),
            0f
        )

        camera.unproject(mouse)

        mouseX = mouse.x
        mouseY = mouse.y


        // --- 世界 ---
        for (i1 in 0 until worldMap.size()) {
            for (i2 in 0 until worldMap.getByIndex(i1).size()) {

                // 获取当前方块坐标
                val x = ((worldMap.indexToCoordinate(i1).x * 16) + worldMap.chunks[i1].indexToCoordinate(i2).x).toFloat() * 32f
                val y = ((worldMap.indexToCoordinate(i1).y * 16) + worldMap.chunks[i1].indexToCoordinate(i2).y).toFloat() * 32f

                // 绘制texture
                batchDraw.draw(
                    worldMap.chunks[i1].blocks[i2].texture, x, y
                )


                if (worldMap.chunks[i1].blocks[i2].isHovered) {

                    val thread = Thread(curve)
                    if (!(lastHovered1 == i1 && lastHovered2 == i2)) {
                        curve.tureValue = .0
                        thread.start()
                    }

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

                if (mouseX in x..(x + 32f) && mouseY in y..(y + 32f)) {   //判断是否hovered
                    worldMap.chunks[i1].blocks[i2].isHovered = true
                    lastHovered1 = i1
                    lastHovered2 = i2
                } else {
                    worldMap.chunks[i1].blocks[i2].isHovered = false
                }
            }
        }
        for (i1 in 0 until worldMap.size()) {
            for (i2 in 0 until worldMap.getByIndex(i1).size()) {
                // 获取当前方块坐标
                val x = ((worldMap.indexToCoordinate(i1).x * 16) + worldMap.chunks[i1].indexToCoordinate(i2).x).toFloat() * 32f
                val y = ((worldMap.indexToCoordinate(i1).y * 16) + worldMap.chunks[i1].indexToCoordinate(i2).y).toFloat() * 32f

                if (mouseX in x..(x + 32f) && mouseY in y..(y + 32f)) {
                    font.draw(batchDraw, worldMap.chunks[i1].blocks[i2].name, mouseX, mouseY)
                }
            }
        }

        // --- 世界中的文字 ---
        font.draw(
            batchDraw,
            "[GameManagerFPS] ${GameManager.GameManagerFPS_true}\n" +
                "[CurveManagerFPS] ${CurveManagerFPS_true}",
            fontX_ture,
            fontY_ture
        )



        //调整batch
        batchDraw.projectionMatrix = Matrix4().setToOrtho2D(0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        font.data.setScale(1f)




        if (debug) {
            font.draw(
                batchDraw,
                "[MouseWorld](${mouse.x.toInt()}, ${mouse.y.toInt()})",
                12f,
                12f
            )
        }

        batchDraw.end()

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

