package com.jadencode.main.renderengine.toolbox;

import org.lwjgl.Sys;

/**
 * Created by gtrpl on 7/4/2016.
 */
public class Time {
    private static long lastFrameTime = 0;
    private static long lastFrameRenderTime;

    public static long getRenderMs() {
        return lastFrameRenderTime;
    }
    public static float getFrameTimeSeconds() {
        return (float) getRenderMs() / 1000F;
    }
    private static long getCurrentTime() {
        return Sys.getTime() * 1000 / Sys.getTimerResolution();
    }
    public static void update() {
        if(lastFrameTime == 0) lastFrameTime = getCurrentTime();
        long currentFrameTime = getCurrentTime();
        lastFrameRenderTime = (currentFrameTime - lastFrameTime);
        lastFrameTime = currentFrameTime;
    }
}
