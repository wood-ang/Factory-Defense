package com.wood.FactoryDefense.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.wood.FactoryDefense.kotlin.Main;

/**
 * 启动桌面 (LWJGL3) 应用程序。
 */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return; // 这用于处理 macOS 支持，并在 Windows 上提供帮助。
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new Main(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("FactoryDefense");
        //// 垂直同步将每秒帧数限制在您硬件可以显示的范围内，并有助于消除画面撕裂。此设置在 Linux 上并不总是有效，因此下一行是一个保障措施。
        configuration.useVsync(true);
        //// 将 FPS 限制为当前活动显示器的刷新率，并加 1 以尝试匹配分数刷新率。上面的垂直同步设置应将实际 FPS 限制为与显示器匹配。
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);
        //// 如果您移除上面的行并将垂直同步设置为 false，您将获得无限制的 FPS，这对测试性能很有用，但也可能给某些硬件带来很大压力。
        //// 您可能还需要配置 GPU 驱动程序以完全禁用垂直同步；这可能导致画面撕裂。

        configuration.setWindowedMode(640, 480);
        //// 您可以更改这些文件；它们位于 lwjgl3/src/main/resources/ 目录下。
        //// 也可以从 assets/ 的根目录加载它们。
        configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");

        //// 这应该能提高与 OpenGL 驱动有问题的 Windows 机器、无论如何都必须模拟兼容 OpenGL 的 Apple Silicon Mac 等机器的兼容性。
        //// 这依赖于 `com.badlogicgames.gdx:gdx-lwjgl3-angle` 依赖项来起作用。
        //// 如果需要，您可以选择移除下面这行和提到的依赖项；它们并非为使用 GL30（即兼容 OpenGL ES 3.0）的游戏设计。
        configuration.setOpenGLEmulation(Lwjgl3ApplicationConfiguration.GLEmulation.ANGLE_GLES20, 0, 0);

        return configuration;
    }
}
