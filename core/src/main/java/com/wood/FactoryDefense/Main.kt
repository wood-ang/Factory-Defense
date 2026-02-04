// 导入包声明
package com.wood.FactoryDefense  // 游戏主包，FactoryDefense 意为"工厂防御"

// 导入LibGDX框架核心类
import com.badlogic.gdx.ApplicationAdapter  // LibGDX游戏主类基类
import com.badlogic.gdx.Gdx  // LibGDX全局工具类，提供图形、输入、文件等访问
import com.badlogic.gdx.graphics.OrthographicCamera  // 2D正交相机
import com.badlogic.gdx.graphics.Pixmap  // 像素图，用于创建纹理
import com.badlogic.gdx.graphics.Texture  // 纹理类，用于存储图像数据
import com.badlogic.gdx.graphics.g2d.BitmapFont  // 位图字体类
import com.badlogic.gdx.graphics.g2d.NinePatch  // 九宫格纹理，用于UI拉伸
import com.badlogic.gdx.graphics.g2d.SpriteBatch  // 精灵批处理器，用于高效绘制
import com.badlogic.gdx.graphics.g2d.TextureRegion  // 纹理区域，用于截取纹理的一部分
import com.badlogic.gdx.math.Vector3  // 三维向量，用于坐标表示
import com.badlogic.gdx.utils.ScreenUtils  // 屏幕工具类，提供清屏等功能
import com.wood.FactoryDefense.kotlin.Block.Air

// 导入游戏自定义类
import com.wood.FactoryDefense.kotlin.StaticData.Data.*  // 静态数据，包含游戏全局变量
import com.wood.FactoryDefense.kotlin.Block.WorldMap  // 世界地图类
import com.wood.FactoryDefense.kotlin.Curve.ThreadCurve  // 曲线动画线程类
import com.wood.FactoryDefense.kotlin.DrawFunction.drawDebugText
import com.wood.FactoryDefense.kotlin.DrawFunction.whenHovered
import com.wood.FactoryDefense.kotlin.DrawFunction.drawBlocks
import com.wood.FactoryDefense.kotlin.DrawFunction.drawItems
import com.wood.FactoryDefense.kotlin.Item.ItemLayout
import com.wood.FactoryDefense.kotlin.Manager.CurveManager  // 曲线管理器
import com.wood.FactoryDefense.kotlin.Manager.GameManager  // 游戏管理器
import com.wood.FactoryDefense.kotlin.Manager.Processor  // 输入处理器
import com.wood.FactoryDefense.kotlin.Manager.UIManager  // UI管理器
import com.wood.FactoryDefense.kotlin.StaticData.UIData.*  // 静态UI数据
import com.wood.FactoryDefense.kotlin.UI.Arrangement  // UI排列方式枚举
import com.wood.FactoryDefense.kotlin.UI.UILayout  // UI布局类
import com.wood.FactoryDefense.kotlin.UI.Panel  // UI面板类
import com.wood.FactoryDefense.kotlin.UI.Shape  // UI形状类
import com.wood.FactoryDefense.kotlin.UI.Text
import com.wood.FactoryDefense.kotlin.terminal.Log
import com.wood.FactoryDefense.kotlin.terminal.Terminal

/**
 * Main类 - 游戏主入口类
 * 继承自ApplicationAdapter，是LibGDX游戏的起点
 */
class Main : ApplicationAdapter() {

    companion object{

        var lastHoveredX = 0  // 区块索引
        var lastHoveredY = 0  // 方块在区块内的索引
        var isItemHovered: Boolean = false
        var isBlockHovered: Boolean = false

        // --- 动画效果 ---
        val curveBlockHovered = ThreadCurve(3000, 1.0, 100.0, 0.2, 20)  // 曲线动画线程，用于悬停效果
        val curveItemHovered  = ThreadCurve(3000, 1.0, 100.0, 0.3, 20)  // 曲线动画线程，用于悬停效果

        var indexOut: Int = 0

        // 参数说明：持续时间3000ms，起始值1.0，结束值100.0，初始速度0.2，加速度20
        var lastHoveredItemLayout = ItemLayout(0f, 0f)
    }

    // --- 图形渲染相关变量 ---
    lateinit var camera: OrthographicCamera  // 2D相机，控制视图
    lateinit var batchDraw: SpriteBatch      // 精灵批处理器，用于绘制所有图形
    lateinit var image: Texture              // 测试用的纹理（可能已弃用）
    lateinit var hovered: Texture            // 鼠标悬停时显示的纹理
    lateinit var cameraCenter: Texture       // 相机中心点纹理（可能用于调试）


    // --- 输入处理 ---
    private val inputProcessor: Processor = Processor()  // 自定义输入处理器


    // --- 鼠标位置 ---
    lateinit var mouse: Vector3  // 存储鼠标在世界坐标系中的位置


    /**
     * create() - LibGDX生命周期方法，游戏初始化时调用
     * 当游戏启动时自动执行，只执行一次
     */
    override fun create() {
        // 初始化游戏所有组件
        init()

        // 创建用户界面
        createUI()

        // 启动后台管理线程
        createThreads()


        for (x in 0 until worldMap.width) {
            for (y in 0 until worldMap.width) {
                worldMap.blocks[x][y].updateConnections(x,y)
            }
        }

    }

    /**
     * initialization() - 初始化游戏核心资源
     * 私有方法，用于组织初始化代码
     */
    private fun init() {
        // --- 初始化核心资源 ---

        // 创建正交相机，视口大小设置为屏幕大小
        camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

        // 加载纹理资源
        image = Texture("libgdx.png")  // LibGDX Logo纹理
        cameraCenter = Texture("BasicBuild.png")  // 基础建筑纹理
        textureAir = createTransparentTexture(32, 32,0f,0f,0f,0f)  // 创建透明纹理（空气方块）
        textureBaseBlock = Texture("BasicBuild.png")  // 基础方块纹理
        textureStone = Texture("Stone.png")  // 石头纹理
            textureBaseBuildFrame = Texture("BasicBuildFrame.png")
            textureBaseBuildFrame_B = Texture("frame_B.png")
            textureBaseBuildFrame_BL = Texture("frame_BL.png")
            textureBaseBuildFrame_BR = Texture("frame_BR.png")
            textureBaseBuildFrame_L = Texture("frame_L.png")
            textureBaseBuildFrame_R = Texture("frame_R.png")
            textureBaseBuildFrame_T = Texture("frame_T.png")
            textureBaseBuildFrame_TL = Texture("frame_TL.png")
            textureBaseBuildFrame_TR = Texture("frame_TR.png")

            textureStoneFrame_T = Texture("StoneFrame_T.png")
            textureStoneFrame_B = Texture("StoneFrame_B.png")
            textureStoneFrame_R = Texture("StoneFrame_R.png")
            textureStoneFrame_L = Texture("StoneFrame_L.png")

        textureBasicItem = Texture("BasicItem.png")
        textureUIPanle = Texture("UIPanel.png")  // UI面板背景纹理
        textureRegion = TextureRegion(textureUIPanle)  // 创建纹理区域
        ninePatch = NinePatch(textureRegion)  // 创建九宫格（用于UI拉伸）
        ninePatch.setPadding(20f, 20f, 20f, 20f)  // 设置九宫格内边距

        // 调试字体位置
        debugFontY.data = 300f

        // 初始化相机位置到屏幕中心
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f)
        camera.update()  // 应用相机变换

        // 初始化绘制工具
        batchDraw = SpriteBatch()  // 创建精灵批处理器
        font = BitmapFont()  // 创建默认字体

        // --- 初始化地图 ---
        worldMap = WorldMap(246, 256)  // 创建10x10区块的世界地图
        worldMap.respawnPointX = 256f  // 设置重生点X坐标
        worldMap.respawnPointY = 256f  // 设置重生点Y坐标
        fontX = worldMap.respawnPointX  // 设置字体起始X坐标
        fontY = worldMap.respawnPointY  // 设置字体起始Y坐标

        // --- 其余初始化 ---
        hovered = Texture("Hovered.png")  // 加载悬停高亮纹理
        choose = Air()  // 初始化当前选择的方块为空
        Gdx.input.inputProcessor = inputProcessor  // 设置输入处理器
        terminal = Terminal()
        terminal.logs.add(Log(WARN,"Log1" ))
    }

    /**
     * createThreads() - 创建并启动后台线程
     * 私有方法，启动游戏管理和曲线管理线程
     */
    private fun createThreads() {
        Thread(GameManager()).start()  // 启动游戏管理线程
        Thread(CurveManager()).start()  // 启动曲线管理线程
        Thread(curveItemHovered).start()
        Thread(curveBlockHovered).start()
    }

    /**
     * createUI() - 创建用户界面
     * 私有方法，初始化UI系统
     */
    private fun createUI() {
        // 创建UI管理器，传入精灵批处理器用于绘制
        uiManager = UIManager(batchDraw,font)

        // 创建一个面板：大小为400x300，垂直排列，内边距1，位置(8,8)
        val panel = Panel(
            Shape(400f, 300f),  // 面板形状
            UILayout(Arrangement.Vertical, 1, x = 8f, y = 8f)  // 布局设置
        )
        val text = Text(
            Shape(400f, 50f),
            UILayout(Arrangement.Vertical, 1, x = 0f, y = 32f),
            "$mouseX $mouseY"
        )

        // 将面板添加到UI管理器的根节点
        uiManager.roots.addUIChild(panel)
        uiManager.roots.children[0].addUIChild(text)
    }

    /**
     * resize() - LibGDX生命周期方法，窗口大小改变时调用
     * @param width 新的窗口宽度（像素）
     * @param height 新的窗口高度（像素）
     */
    override fun resize(width: Int, height: Int) {
        // 更新相机视口大小
        camera.viewportWidth = width.toFloat()
        camera.viewportHeight = height.toFloat()
        camera.update()  // 重新计算相机的变换矩阵

        // 更新全局显示尺寸（可能用于UI布局）
        displayWidth = width.toFloat()
        displayHeight = height.toFloat()
    }

    /**
     * render() - LibGDX生命周期方法，每一帧调用
     * 游戏的主循环，负责绘制和更新游戏状态
     */
    override fun render() {

        renderInit()

        // 开始绘制批次
        batchDraw.begin()

        // --- 绘制游戏世界 ---
        drawBlocks(batchDraw)       // 绘制所有方块
        drawItems(batchDraw)        // 绘制掉落物
        whenHovered(batchDraw)      // 绘制悬停效果
        drawDebugText(batchDraw)    // 绘制调试信息

        // 结束绘制批次
        batchDraw.end()

        Thread.sleep(1000/DrawManagerFPS)

    }

    fun renderInit(){

        // --- 更新相机 ---
        // 设置相机缩放（使用平滑后的值，避免抖动）
        camera.zoom = cameraZoom_ture
        // 设置相机位置（使用平滑后的值）
        camera.position.set(fontX_ture, fontY_ture, 0f)
        camera.update()  // 应用相机变换

        // 清屏：设置背景颜色为深蓝色（RGB: 0.15, 0.15, 0.2）
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f)

        // 设置精灵批处理器的投影矩阵为相机矩阵
        // 这样所有绘制都会按照相机视角进行变换
        batchDraw.projectionMatrix = camera.combined

        // 重置绘制颜色为不透明白色（避免之前的透明度设置影响）
        batchDraw.setColor(1f, 1f, 1f, 1f)


        // 根据相机缩放调整字体大小（缩放越大，字体越小）
        font.data.setScale(cameraZoom_ture * 2f)

        // --- 更新鼠标位置 ---
        // 获取屏幕坐标系的鼠标位置
        mouse = Vector3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
        // 将鼠标位置从屏幕坐标系转换到世界坐标系
        camera.unproject(mouse)
        // 存储到全局变量中（可能供其他系统使用）
        mouseX = mouse.x
        mouseY = mouse.y
    }


    /**
     * dispose() - LibGDX生命周期方法，游戏退出时调用
     * 释放所有资源，防止内存泄漏
     */
    override fun dispose() {
        // 释放所有LibGDX资源
        batchDraw.dispose()  // 释放精灵批处理器
        image.dispose()      // 释放纹理
        font.dispose()       // 释放字体

        // 清理输入处理器
        Gdx.input.inputProcessor = null
    }
}

fun createTransparentTexture(width: Int, height: Int, r: Float,g: Float,b: Float,a: Float): Texture {
        // 如果没有，则动态创建
        // 创建指定大小的Pixmap（像素图）
        val pixmap = Pixmap(width, height, Pixmap.Format.RGBA8888)
        pixmap.setColor(r,g,b,a)
        pixmap.fill()  // 填充整个Pixmap
        val texture = Texture(pixmap)  // 从Pixmap创建纹理
        pixmap.dispose()  // 释放Pixmap内存
        return texture
}
