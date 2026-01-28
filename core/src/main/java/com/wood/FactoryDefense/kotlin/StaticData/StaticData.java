package com.wood.FactoryDefense.kotlin.StaticData;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.wood.FactoryDefense.kotlin.Block.BasicBlock;
import com.wood.FactoryDefense.kotlin.Block.WorldMap;

public class StaticData {
    public static long GameManagerFPS = 20;
    public static long CurveLaoderFPS = 60;
    public static long DrawManagerFPS2 = 0;
    public static long CurveManagerFPS_true = 0;


    //************************************************************************//
    public static Float fontX;
    public static Float fontY;

    public static float fontX_ture;
    public static float fontY_ture;
    //************************************************************************//
    public static float cameraZoom = 1f;
    public static float cameraZoom_ture = 1f;
    public static float cameraZoomMax = 6f;
    public static float cameraZoomMin = 0.25f;
    //************************************************************************//
    public static float mouseX = 0f;
    public static float mouseY = 0f;

    public static boolean mouseLeft = false;
    public static boolean mouseRight = false;
    public static boolean mouseMIDDLE = false;
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

    //************************************************************************//
    public static boolean debug = true;
    //************************************************************************//
    public static BasicBlock choose;
}


