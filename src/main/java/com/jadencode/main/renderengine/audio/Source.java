package com.jadencode.main.renderengine.audio;

import org.lwjgl.openal.AL10;

/**
 * Created by gtrpl on 7/9/2016.
 */
public class Source {
    private final int sourceID;
    public Source() {
        this.sourceID = AL10.alGenSources();
        AL10.alSourcef(sourceID, AL10.AL_ROLLOFF_FACTOR, 2);
        AL10.alSourcef(sourceID, AL10.AL_REFERENCE_DISTANCE, 6);
        AL10.alSourcef(sourceID, AL10.AL_MAX_DISTANCE, 50);
    }
    public void pause() {
        AL10.alSourcePause(sourceID);
    }
    public void continuePlaying() {
        AL10.alSourcePlay(sourceID);
    }
    public void stop() {
        AL10.alSourceStop(sourceID);
    }
    public void setParams(float volume, float pitch, float x, float y, float z) {
        this.setVolume(volume);
        this.setPitch(pitch);
        this.setPosition(x, y, z);
    }
    public void setVelocity(float x, float y, float z) {
        AL10.alSource3f(sourceID, AL10.AL_VELOCITY, x, y, z);
    }
    public void setLooping(boolean loop) {
        AL10.alSourcei(sourceID, AL10.AL_LOOPING, loop ? AL10.AL_TRUE : AL10.AL_FALSE);
    }
    public boolean isPlaying() {
        return AL10.alGetSourcei(sourceID, AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING;
    }
    public void setVolume(float volume) {
        AL10.alSourcef(sourceID, AL10.AL_GAIN, volume);
    }
    public void setPitch(float pitch) {
        AL10.alSourcef(sourceID, AL10.AL_PITCH, pitch);
    }
    public void setPosition(float x, float y, float z) {
        AL10.alSource3f(sourceID, AL10.AL_POSITION, x, y, z);
    }
    public void play(int buffer) {
        stop();
        AL10.alSourcei(this.sourceID, AL10.AL_BUFFER, buffer);
        continuePlaying();
    }
    public void delete() {
        stop();
        AL10.alDeleteSources(this.sourceID);
    }
}
