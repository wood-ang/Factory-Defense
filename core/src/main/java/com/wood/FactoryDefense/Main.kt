package com.wood.FactoryDefense;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.wood.FactoryDefense.kotlin.Block.WorldMap;
import com.wood.FactoryDefense.kotlin.Manager.CurveManager;
import com.wood.FactoryDefense.kotlin.GameInput;
import com.wood.FactoryDefense.kotlin.Manager.GameManager;
import com.wood.FactoryDefense.kotlin.Manager.Processor;
import static com.wood.FactoryDefense.StaticData.*;
import static com.wood.FactoryDefense.kotlin.Manager.GameManager.GameManagerFPS_true;
import static java.net.NetworkInterface.getByIndex;

public class Main extends ApplicationAdapter {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture image;
    private BitmapFont font;
    private Texture cameraCenter;
    public static WorldMap worldMap;

    @Override
    public void create() {
        // 初始化相机并设置视口大小
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();

        // 初始化SpriteBatch和字体
        batch = new SpriteBatch();
        font = new BitmapFont(); // 默认字体

        //其余初始化
        worldMap = new WorldMap(4,4);
        cameraCenter = new Texture("BaseBuild.png");

        Gdx.input.setInputProcessor(new Processor());


        image = new Texture("libgdx.png");  // 这里加载图片

        // 设置输入处理器
        new Thread(new GameInput()).start();

        // 注意：GameManager 和 CurveManager 需要根据实际情况调整
        // 如果它们是单例或静态类，需要相应修改
        Thread thread1 = new Thread(new GameManager());
        thread1.start();
        Thread thread2 = new Thread(new CurveManager());
        thread2.start();
    }

    @Override
    public void resize(int width, int height) {
        // 调整视口大小
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();  // 重新计算相机的矩阵
    }

    @Override
    public void render() {
        try {
            input();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 清空屏幕
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        // 设置投影矩阵
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // 渲染文本和图像
        font.draw(
            batch,
            "[GameManagerFPS] " + GameManagerFPS_true + "\n" + "[CurveManagerFPS]" + CurveManagerFPS_true,
            fontX_ture,
            fontY_ture
        );

        for (int i1 = 0 ;worldMap.size() < 0 ; i1++) {
            for (int i2 = 0 ;worldMap.getByIndex(i1).size() < 0 ; i2++) {
                (worldMap.chunks[i1]).blocks[i2].flasher();
            }
        }

        camera.zoom = cameraZoom_ture;

        camera.position.x = fontX_ture;
        camera.position.y = fontY_ture;

        camera.update();

        batch.draw(image, 140f, 210f);

        batch.end();
    }

    @Override
    public void dispose() {
        // 释放资源
        batch.dispose();
        image.dispose();
        font.dispose();
        Gdx.input.setInputProcessor(null);
    }

    private void input() throws InterruptedException {
        // 使用 Gdx.input 来获取输入
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            com.wood.FactoryDefense.kotlin.Manager.KeyManagerKt.KeyW();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            com.wood.FactoryDefense.kotlin.Manager.KeyManagerKt.KeyS();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            com.wood.FactoryDefense.kotlin.Manager.KeyManagerKt.KeyA();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            com.wood.FactoryDefense.kotlin.Manager.KeyManagerKt.KeyD();
        }

        Thread.sleep(25);
    }


}
