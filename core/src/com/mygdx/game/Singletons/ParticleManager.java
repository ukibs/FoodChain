package com.mygdx.game.Singletons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by USUARIO on 06/06/2018.
 */

public class ParticleManager {
    static ParticleManager instance;
    ParticleEffect [] effects;

    private ParticleManager()
    {
        effects = new ParticleEffect[3];
        for(int  i = 0; i < effects.length; i++)
        {
            effects[i] = new ParticleEffect();
            effects[i].scaleEffect(0.1f, 0.1f);
        }

        effects[0].loadEmitterImages(Gdx.files.internal("ParticleEffects/Fireworks Blue"));
        effects[1].loadEmitterImages(Gdx.files.internal("ParticleEffects/Fireworks Green"));
        effects[2].loadEmitterImages(Gdx.files.internal("ParticleEffects/Fireworks White"));
    }

    public static ParticleManager getInstance()
    {
        if(instance == null)
        {
            instance = new ParticleManager();
        }
        return instance;
    }

    public void play(int effect, Vector2 position)
    {
        effects[effect].setPosition(position.x, position.y);
        effects[effect].start();
    }

    public void update(int i, float dt)
    {
        if(effects[i].isComplete())
            effects[i].start();
        effects[i].update(dt);
    }

    public void render(SpriteBatch batch, int i)
    {
        effects[i].draw(batch);
    }
}
