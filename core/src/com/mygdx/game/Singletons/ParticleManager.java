package com.mygdx.game.Singletons;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;

import java.util.ArrayList;

/**
 * Created by USUARIO on 06/06/2018.
 */

public class ParticleManager {
    static ParticleManager instance;
    ArrayList<ParticleEffect> effects;

    private ParticleManager()
    {


    }

    public static ParticleManager getInstance()
    {
        if(instance == null)
        {
            instance = new ParticleManager();
        }
        return instance;
    }

    public void play(int effect)
    {
        //effects.get(effect).
    }
}
