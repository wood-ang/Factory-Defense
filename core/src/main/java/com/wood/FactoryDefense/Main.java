package com.wood.FactoryDefense;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.wood.FactoryDefense.kotlin.CurveManager;
import com.wood.FactoryDefense.kotlin.GameInput;
import com.wood.FactoryDefense.kotlin.GameManager;
import com.wood.FactoryDefense.StaticData.*;

import static com.wood.FactoryDefense.StaticData.*;
import static com.wood.FactoryDefense.kotlin.GameManager.GameManagerFPS_true;

public class Main extends ApplicationAdapter {

    public static float fontX_ture = 50f;
    public static float fontY_ture = 100f;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture image;
    private BitmapFont font;

    @Override
    public void create() {
        // 初始化相机并设置视口大小
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();

        // 初始化SpriteBatch和字体
        batch = new SpriteBatch();
        font = new BitmapFont(); // 默认字体

        image = new Texture("libgdx.png");  // 这里加载图片

        // 设置输入处理器，直接使用 InputProcessor
        new Thread(new GameInput()).start();

        // 注意：GameManager 和 CurveManager 需要根据实际情况调整
        // 如果它们是单例或静态类，需要相应修改
        Thread thread1 = new Thread(new GameManager());thread1.start();
        Thread thread2 = new Thread(new CurveManager());thread2.start();
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
        input();
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

    private void input() {
        // 使用 Gdx.input 来获取输入
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            fontY += 50;
            System.out.println("[fontY] " + fontY);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            fontY -= 50;
            System.out.println("[fontY] " + fontY);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            fontX -= 50;
            System.out.println("[fontX] " + fontX);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            fontX += 50;
            System.out.println("[fontX] " + fontX);
        }

        try {
            // 让线程休眠200ms
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace(); // 异常处理
        }
    }
}
