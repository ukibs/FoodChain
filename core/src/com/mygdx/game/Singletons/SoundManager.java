package com.mygdx.game.Singletons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.ArrayList;

/**
 * Created by USUARIO on 05/06/2018.
 */

public class SoundManager {
    public static final int fruitLost = 0;
    public static final int getFruit = 1;
    public static final int levelLose = 2;
    public static final int trash = 3;
    public static final int angry = 4;
    public static final int levelComplete = 5;


    static SoundManager instance;
    ArrayList<Sound> effects;
    ArrayList<Music> musicTracks;

    private SoundManager()
    {
        // Effects
        effects = new ArrayList<Sound>();
        effects.add(Gdx.audio.newSound(Gdx.files.internal("Effects/fruitLost.wav")));
        effects.add(Gdx.audio.newSound(Gdx.files.internal("Effects/clientFin.wav")));
        effects.add(Gdx.audio.newSound(Gdx.files.internal("Effects/levelLose.mp3")));
        effects.add(Gdx.audio.newSound(Gdx.files.internal("Effects/trash.wav")));
        effects.add(Gdx.audio.newSound(Gdx.files.internal("Effects/angry.wav")));
        effects.add(Gdx.audio.newSound(Gdx.files.internal("Effects/levelComplete.wav")));
        // Music tracks
        musicTracks = new ArrayList<Music>();
        musicTracks.add(Gdx.audio.newMusic(Gdx.files.internal("Music/Menu.ogg")));
        musicTracks.add(Gdx.audio.newMusic(Gdx.files.internal("Music/Harvest.ogg")));
        musicTracks.add(Gdx.audio.newMusic(Gdx.files.internal("Music/Restaurant.ogg")));
        musicTracks.add(Gdx.audio.newMusic(Gdx.files.internal("Music/SuperMarket.ogg")));
    }

    public static SoundManager getInstance()
    {
        if(instance == null)
        {
            instance = new SoundManager();
        }
        return instance;
    }

    public void play(int effect)
    {
        effects.get(effect).play();
    }
}
