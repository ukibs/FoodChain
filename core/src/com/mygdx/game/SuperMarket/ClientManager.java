package com.mygdx.game.SuperMarket;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Singletons.Assets;
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
    float clientCooldown = 0.5f;
    float elapsedTime;
    float clientStayTime;

    public ClientManager(Supermarket supermarket, int level){
        //
        this.supermarket = supermarket;
        //
        clientStayTime = 3 - (level * 0.1f);
        //
        clients = new ArrayList<Client>(clientsAmount);
        for(int i = 0; i < clientsAmount; i++){
            clients.add(new Client(supermarket, clientStayTime));
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
        // System.out.println("Shelf to use: " + shelfToUse);
        ActivateClient(shelfToUse);
    }

    //
    void ActivateClient(int shelfToUse){
        for(int i = 0; i < clientsAmount; i++){
            if(!clients.get(i).active){
                //
                int clientsInShelf = CheckClientsInShelf(shelfToUse);
                float extraX = 0;
                if(clientsInShelf == 1) extraX = Constants.HEIGHT_RATIO * 0.75f;
                else if(clientsInShelf >= 2) return;

                //Client activatedClient = clients.get(i);
                clients.get(i).position = new Vector2(
                        supermarket.shelfButtons[shelfToUse].position.x + extraX,
                        supermarket.shelfButtons[shelfToUse].position.y - Constants.HEIGHT_RATIO *(6));
                //activatedClient.position = new Vector2(0,0);
                int clientIndex = MathUtils.random(Assets.getInstance().clients.length - 1);
                clients.get(i).face = new Sprite(Assets.getInstance().clients[clientIndex]);
                clients.get(i).active = true;
                clients.get(i).currentShelf = shelfToUse;
                return;
            }
        }

    }

    //
    int CheckClientsInShelf(int shelfIndex){
        int clientsInThisShelf = 0;
        for(int i = 0; i < clients.size(); i++){
            if(clients.get(i).currentShelf == shelfIndex)
                clientsInThisShelf ++;
        }
        return clientsInThisShelf;
    }
}
