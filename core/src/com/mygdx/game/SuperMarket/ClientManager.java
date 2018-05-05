package com.mygdx.game.SuperMarket;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;
import com.mygdx.game.Constants;
import com.mygdx.game.GameObject;

import java.util.ArrayList;

/**
 * Created by Martin on 05/05/2018.
 */

public class ClientManager extends GameObject {

    Supermarket supermarket;
    ArrayList<Client> clients;
    int clientsAmount = 10;
    float clientCooldown = 1;
    float elapsedTime;

    public ClientManager(Supermarket supermarket){
        this.supermarket = supermarket;
        clients = new ArrayList<Client>(clientsAmount);
        for(int i = 0; i < clientsAmount; i++){
            clients.add(new Client());
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        // Not render a shit

        //
        for(int i = 0; i < clientsAmount; i++){
            clients.get(i).render(batch);
        }
    }

    @Override
    public void update(float elpasedTime) {
        this.elapsedTime += elpasedTime;
        if(this.elapsedTime > clientCooldown){
            DecideShelf();
            this.elapsedTime = 0;
        }
        //
        for(int i = 0; i < clientsAmount; i++){
            clients.get(i).update(elpasedTime);
        }
    }

    //
    void DecideShelf(){
        int shelfToUse = MathUtils.random(supermarket.shelfButtons.length - 1);
        ActivateClient(shelfToUse);
    }

    //
    void ActivateClient(int shelfToUse){
        for(int i = 0; i < clientsAmount; i++){
            if(!clients.get(i).active){
                Client activatedClient = clients.get(i);
                activatedClient.position = new Vector2(supermarket.shelfButtons[shelfToUse].position.x,
                        supermarket.shelfButtons[shelfToUse].position.y - Constants.HEIGHT_RATIO *(2));
                //activatedClient.position = new Vector2(0,0);
                activatedClient.face = new Sprite(Assets.getInstance().GetRandomFace());
                activatedClient.active = true;
                break;
            }
        }

    }
}
