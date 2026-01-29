package com.wood.FactoryDefense.gwt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.wood.FactoryDefense.Main;

/** 启动 GWT 应用程序。 */
public class GwtLauncher extends GwtApplication {
    @Override
    public GwtApplicationConfiguration getConfig () {
        // 可调整大小的应用程序，使用浏览器中无内边距的可用空间：
        GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(true);
        cfg.padVertical = 0;
        cfg.padHorizontal = 0;
        return cfg;
        // 如果您想要一个固定尺寸的应用程序，请注释掉上面的可调整大小部分，
        // 并取消注释下面的代码：
        //return new GwtApplicationConfiguration(640, 480);
    }

    @Override
    public ApplicationListener createApplicationListener () {
        return new Main();
    }
}
