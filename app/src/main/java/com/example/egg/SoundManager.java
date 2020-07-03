package com.example.egg;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

public class SoundManager {
    private SoundPool soundPool;
    private HashMap<Integer, Integer> soundPoolMap;
    private AudioManager audioManager;
    private Context context;

    public SoundManager(){

    }

    public void initSound(Context theContext){
        context = theContext;
        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public void addSound(int index, int soundID){
        //soundPoolMap.put(index, soundPool.load(context, soundID, 1));
    }

    public void playSound(int index){
        int streamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        soundPool.play(soundPoolMap.get(index), streamVolume, streamVolume, 1, 0,1f);
    }

    public void playLoopedSound(int index){
        int streamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        soundPool.play(soundPoolMap.get(index), streamVolume, streamVolume, 1,-1,1f);
    }
}
