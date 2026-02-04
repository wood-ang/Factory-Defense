package com.wood.FactoryDefense.kotlin.StaticData;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.wood.FactoryDefense.kotlin.Block.BasicBlock;
import com.wood.FactoryDefense.kotlin.Block.WorldMap;
import com.wood.FactoryDefense.kotlin.Item.ItemLayout;
import com.wood.FactoryDefense.kotlin.terminal.Terminal;

public class Data {
    public static long DrawManagerFPS = 120;
    public static long GameManagerFPS = 60;
    public static long CurveLaoderFPS = 60;
    public static float CuverSmoothingParameter = 0.2f;
    public static long UIFlashFPS = 60;
    public static long DrawManagerFPS2 = 1;
    public static long CurveManagerFPS_true = 0;


    //************************************************************************//
    public static BitmapFont font;             // 字体，用于绘制文字

    public static float fontX;
    public static float fontY;

    public static float fontX_ture;
    public static float fontY_ture;
    //************************************************************************//
    public static float cameraZoom = 1f;
    public static float cameraZoom_ture = 1f;
    public static float cameraZoomMax = 6f;
    public static float cameraZoomMin = 0.25f;
    //************************************************************************//
    public static float displayWidth;
    public static float displayHeight;

    public static float mouseX = 0f;
    public static float mouseY = 0f;

    public static boolean mouseLeft = false;
    public static boolean mouseRight = false;
    public static boolean mouseMIDDLE = false;
    public static boolean KeyCONTROL_LEFT = false;
    public static boolean KeyQ = false;
    public static boolean KeyW = false;
    public static boolean KeyA = false;
    public static boolean KeyS = false;
    public static boolean KeyD = false;
    public static boolean KeyE = false;
    public static boolean KeyR = false;
    public static boolean KeyT = false;
    public static boolean KeyY = false;
    public static boolean KeyU = false;
    public static boolean KeyI = false;
    public static boolean KeyO = false;
    public static boolean KeyP = false;

    public static WorldMap worldMap;
    //************************************************************************//
    public static Texture textureAir;
    public static Texture textureBaseBlock;
    public static Texture textureStone;
    public static Texture textureUIPanle;
        public static TextureRegion textureRegion;
        public static NinePatch ninePatch;
    public static Texture textureBaseBuildFrame;
    public static Texture textureBaseBuildFrame_B;
    public static Texture textureBaseBuildFrame_BL;
    public static Texture textureBaseBuildFrame_BR;
    public static Texture textureBaseBuildFrame_L;
    public static Texture textureBaseBuildFrame_R;
    public static Texture textureBaseBuildFrame_T;
    public static Texture textureBaseBuildFrame_TL;
    public static Texture textureBaseBuildFrame_TR;

    public static Texture textureStoneFrame_B;
    public static Texture textureStoneFrame_BL;
    public static Texture textureStoneFrame_BR;
    public static Texture textureStoneFrame_L;
    public static Texture textureStoneFrame_R;
    public static Texture textureStoneFrame_T;
    public static Texture textureStoneFrame_TL;
    public static Texture textureStoneFrame_TR;

    public static Texture textureBasicItem;
    //************************************************************************//
    public static boolean debug = true;
    public static Terminal terminal;
    //************************************************************************//
    public static BasicBlock choose;

    public static ItemLayout itemLayout = new ItemLayout(0f, 0f, 0);
    //************************************************************************//
    public static final String WARN = "\033[33mWARN\033[0m";
    public static final String ERROR = "\033[31mERROR\033[0m";
    public static final String INFO = "\033[32mINFO\033[0m";
    public static final String DEBUG = "\033[34mDEBUG\033[0m";
}


