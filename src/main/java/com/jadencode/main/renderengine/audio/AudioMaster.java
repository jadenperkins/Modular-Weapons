package com.jadencode.main.renderengine.audio;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtrpl on 7/9/2016.
 */
public class AudioMaster {
    private static final List<Integer> buffers = new ArrayList<>();
    public static void init() {
        try {
            AL.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }
    public static void setListenerData(float x, float y, float z) {
        AL10.alListener3f(AL10.AL_POSITION, x, y, z);
        AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
    }
    public static int loadSound(String file) {
        int buffer = AL10.alGenBuffers();
        buffers.add(buffer);

        try {
            WaveData waveFile = WaveData.create(new BufferedInputStream(new FileInputStream(file)));
            AL10.alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);
            waveFile.dispose();
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return buffer;
    }
    public static void cleanUp() {
        buffers.forEach(AL10::alDeleteBuffers);
        AL.destroy();
    }
}