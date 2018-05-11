package com.mygdx.game.RestaurantCode;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Assets;
import com.mygdx.game.BaseButton;
import com.mygdx.game.BaseLevel;
import com.mygdx.game.Constants;
import com.mygdx.game.GameObject;

/**
 * Created by USUARIO on 01/05/2018.
 */

public class Restaurant extends BaseLevel {

    BaseButton food1;
    BaseButton food2;
    BaseButton food3;
    BaseButton food4;

    BaseButton foodBox;

    ClientRestaurant[] clients;

    float elpasedTime = 0;
    float time = 0;

    @Override
    public void init() {
        clients = new ClientRestaurant[4];
        for(int i = 0; i < clients.length; i++)
        {
            clients[i] = new ClientRestaurant(this, Constants.dimension(2.5f+i*(-2), -1));
        }
        food1 = new BaseButton(Assets.getInstance().food[0], "", worldController, Constants.dimension(-3.4f, -4), Constants.dimension(1f, 2)) {
            @Override
            public void buttonFuction() {
                serveToClient(0);
            }
        };

        food2 = new BaseButton(Assets.getInstance().food[1], "", worldController, Constants.dimension(-2.2f, -4), Constants.dimension(1f, 2)) {
            @Override
            public void buttonFuction() {
                serveToClient(1);
            }
        };

        food3 = new BaseButton(Assets.getInstance().food[2], "", worldController, Constants.dimension(-1f, -4), Constants.dimension(1f, 2)) {
            @Override
            public void buttonFuction() {
                serveToClient(2);
            }
        };

        food4 = new BaseButton(Assets.getInstance().food[3], "", worldController, Constants.dimension(0.4f, -4), Constants.dimension(1f, 2)) {
            @Override
            public void buttonFuction() {
                serveToClient(3);
            }
        };

        foodBox = new BaseButton(Assets.getInstance().food[4], "", worldController, Constants.dimension(1.8f, -4.2f), Constants.dimension(2.5f, 2.4f)) {
            @Override
            public void buttonFuction() {
                serveToClient(4);
            }
        };
    }

    @Override
    public void GUI(SpriteBatch batch) {
        food1.render(batch);
        food2.render(batch);
        food3.render(batch);
        food4.render(batch);
        foodBox.render(batch);

        for(ClientRestaurant clientRestaurant: clients) clientRestaurant.render(batch);
    }

    @Override
    public void LevelUpdate(float elapsedTime) {
        time += elapsedTime;
        if(time < Constants.LEVEL_TIME) {
            elpasedTime += elapsedTime;
            food1.update(elapsedTime);
            food2.update(elapsedTime);
            food3.update(elapsedTime);
            food4.update(elapsedTime);
            foodBox.update(elapsedTime);

            for (ClientRestaurant clientRestaurant : clients) clientRestaurant.update(elapsedTime);

            if (elpasedTime > 4) {
                elpasedTime = 0;
                while (getSpace()) {
                    int random = MathUtils.random(0, 3);
                    if (!clients[random].active) {
                        clients[random].init();
                        break;
                    }
                }
            }
        }
        else nextLevel = true;
    }

    @Override
    public void changeLevel(SpriteBatch batch) {
        finishLevelText = "He terminado el restaurante y me voy al supermercado";
    }

    private boolean getSpace()
    {
        for (ClientRestaurant clientRestaurant: clients)
            if(!clientRestaurant.active) return true;
        return false;
    }

    private void serveToClient(int typeFood)
    {
        for(int i = 0; i < clients.length; i++)
        {
            if(clients[i].currentOrder == typeFood && clients[i].clientState != ClientRestaurant.ClientState.Eating && clients[i].active)
            {
                clients[i].takeOrder();
                break;
            }
        }
    }

    public void wastedFood()
    {
        worldController.currentScore += 10;
    }
}
