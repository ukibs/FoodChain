package com.mygdx.game.RestaurantCode;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    @Override
    public void init() {
        clients = new ClientRestaurant[4];

        food1 = new BaseButton(Assets.getInstance().button, "", worldController, Constants.dimension(-3, -4), Constants.dimension(0.8f, 2)) {
            @Override
            public void buttonFuction() {
                serveToClient(0);
            }
        };

        food2 = new BaseButton(Assets.getInstance().button, "", worldController, Constants.dimension(-2, -4), Constants.dimension(0.8f, 2)) {
            @Override
            public void buttonFuction() {
                serveToClient(1);
            }
        };

        food3 = new BaseButton(Assets.getInstance().button, "", worldController, Constants.dimension(-1, -4), Constants.dimension(0.8f, 2)) {
            @Override
            public void buttonFuction() {
                serveToClient(2);
            }
        };

        food4 = new BaseButton(Assets.getInstance().button, "", worldController, Constants.dimension(0, -4), Constants.dimension(0.8f, 2)) {
            @Override
            public void buttonFuction() {
                serveToClient(3);
            }
        };

        foodBox = new BaseButton(Assets.getInstance().button, "", worldController, Constants.dimension(1.5f, -4.2f), Constants.dimension(3, 2.4f)) {
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
    }

    @Override
    public void LevelUpdate(float elapsedTime) {
        food1.update(elapsedTime);
        food2.update(elapsedTime);
        food3.update(elapsedTime);
        food4.update(elapsedTime);
        foodBox.update(elapsedTime);
    }

    private void serveToClient(int typeFood)
    {
        for(int i = 0; i < clients.length; i++)
        {
            if(clients[i].currentOrder == typeFood)
            {
                clients[i].takeOrder();
            }
        }
    }
}
