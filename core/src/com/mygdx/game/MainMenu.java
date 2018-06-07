package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by USUARIO on 30/04/2018.
 */

public class MainMenu extends GameObject {
    WorldController worldController;
    Texture background;
    BaseButton [] menuButtons;
    int menuButtonSelected;
    BaseButton [][] practiceButtons;
    int practiceButtonsSelectedX;
    int practiceButtonsSelectedY;
    BaseButton backMenu;

    BitmapFont text;

    MainMenu(WorldController worldController)
    {
        background = com.mygdx.game.Singletons.Assets.getInstance().menuBackground;
        this.worldController = worldController;
        menuButtons = new BaseButton[2];
        menuButtons[0] = new BaseButton(com.mygdx.game.Singletons.Assets.getInstance().button, "Play", worldController, Constants.dimension(-4, 1), Constants.dimension(3, 2)) {
            @Override
            public void buttonFuction() {
                worldController.gameMode = WorldController.GameMode.Harvest;
                worldController.InitiateLevel();
            }
        };

        menuButtons[1] = new BaseButton(com.mygdx.game.Singletons.Assets.getInstance().button, "Practice", worldController, Constants.dimension(-4, -2), Constants.dimension(3, 2)) {
            @Override
            public void buttonFuction() {
                worldController.inPractice = true;
            }
        };

        practiceButtons = new BaseButton[2][2];
        practiceButtons[0][0] = new BaseButton(com.mygdx.game.Singletons.Assets.getInstance().button, "Harvest", worldController, Constants.dimension(-4, 1), Constants.dimension(3, 2)) {
            @Override
            public void buttonFuction() {
                worldController.gameMode = WorldController.GameMode.Harvest;
                worldController.InitiateLevel();
            }
        };

        practiceButtons[1][0] = new BaseButton(com.mygdx.game.Singletons.Assets.getInstance().button, "Transport", worldController, Constants.dimension(1,1), Constants.dimension(3, 2)) {
            @Override
            public void buttonFuction() {
                worldController.gameMode = WorldController.GameMode.Transport;
                worldController.InitiateLevel();
            }
        };

        practiceButtons[0][1] = new BaseButton(com.mygdx.game.Singletons.Assets.getInstance().button, "Restaurant", worldController, Constants.dimension(-4, -2), Constants.dimension(3,2)) {
            @Override
            public void buttonFuction() {
                worldController.gameMode = WorldController.GameMode.Restaurant;
                worldController.InitiateLevel();
            }
        };

        practiceButtons[1][1] = new BaseButton(com.mygdx.game.Singletons.Assets.getInstance().button, "Super", worldController, Constants.dimension(1, -2), Constants.dimension(3,2)) {
            @Override
            public void buttonFuction() {
                worldController.gameMode = WorldController.GameMode.Supermarket;
                worldController.InitiateLevel();
            }
        };

        backMenu = new BaseButton(com.mygdx.game.Singletons.Assets.getInstance().button, "Menu", worldController, Constants.dimension(-4.5f, -4), Constants.dimension(1.5f, 1)) {
            @Override
            public void buttonFuction() {
                worldController.gameMode = WorldController.GameMode.MainMenu;
                worldController.InitiateLevel();
            }
        };
        text = new BitmapFont(Gdx.files.internal("Fonts/Test.fnt"), false);
    }

    public void init()
    {
        worldController.inPractice = false;
        worldController.currentScore = worldController.maxScore();
        menuButtonSelected = 0;
        practiceButtonsSelectedX = 0;
        practiceButtonsSelectedY = 0;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(com.mygdx.game.Singletons.Assets.getInstance().header, -Gdx.graphics.getWidth() / 2, -Gdx.graphics.getHeight() / 2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if(!worldController.inPractice)
        {
            batch.draw(background, Constants.WIDTH_RATIO*-5, Constants.HEIGHT_RATIO*-5, Constants.WIDTH_RATIO*10, Constants.HEIGHT_RATIO*10);
            batch.draw(com.mygdx.game.Singletons.Assets.getInstance().score, Constants.WIDTH_RATIO * 0f, Constants.HEIGHT_RATIO*-2.5f, Constants.WIDTH_RATIO *3f, Constants.HEIGHT_RATIO*6);

            batch.draw(com.mygdx.game.Singletons.Assets.getInstance().score, menuButtons[menuButtonSelected].position.x - Constants.WIDTH_RATIO*0.1f,
                    menuButtons[menuButtonSelected].position.y - Constants.WIDTH_RATIO * 0.1f,
                    menuButtons[menuButtonSelected].dimension.x + Constants.WIDTH_RATIO * 0.2f,
                    menuButtons[menuButtonSelected].dimension.y + Constants.WIDTH_RATIO * 0.2f);

            menuButtons[0].render(batch);
            menuButtons[1].render(batch);
            text.draw(batch, "Level " + worldController.level, Constants.WIDTH_RATIO *3f, Constants.HEIGHT_RATIO*4.5f);
            text.draw(batch, "Ranking:", Constants.WIDTH_RATIO *1f, Constants.HEIGHT_RATIO*3);
            for(int i = 0; i < 5; i++)
            {
                text.draw(batch, (i+1)+"", Constants.WIDTH_RATIO * 0.5f, Constants.HEIGHT_RATIO * (2f - i * 1f));
                text.draw(batch, worldController.scoreNames.get(i), Constants.WIDTH_RATIO * 1f, Constants.HEIGHT_RATIO * (2f - i * 1f), Constants.WIDTH_RATIO, 1, false);
                text.draw(batch, worldController.scores[i] + "", Constants.WIDTH_RATIO * 2.5f, Constants.HEIGHT_RATIO * (2f - i * 1f));
            }
        }
        else {
            batch.draw(com.mygdx.game.Singletons.Assets.getInstance().score, practiceButtons[practiceButtonsSelectedX][practiceButtonsSelectedY].position.x - Constants.WIDTH_RATIO*0.1f,
                    practiceButtons[practiceButtonsSelectedX][practiceButtonsSelectedY].position.y - Constants.WIDTH_RATIO * 0.1f,
                    practiceButtons[practiceButtonsSelectedX][practiceButtonsSelectedY].dimension.x + Constants.WIDTH_RATIO * 0.2f,
                    practiceButtons[practiceButtonsSelectedX][practiceButtonsSelectedY].dimension.y + Constants.WIDTH_RATIO * 0.2f);

            for(int i = 0; i < 2; i++)
            {
                for(int j = 0; j < 2; j++)
                {
                    practiceButtons[i][j].render(batch);
                }
            }
            backMenu.render(batch);
        }
    }

    @Override
    public void update(float elpasedTime) {
        if(!worldController.inPractice)
        {
            menuButtons[0].update(elpasedTime);
            menuButtons[1].update(elpasedTime);
        }
        else {
            for(int i = 0; i < 2; i++)
            {
                for(int j = 0; j < 2; j++)
                {
                    practiceButtons[i][j].update(elpasedTime);
                }
            }
            backMenu.update(elpasedTime);
        }
    }

    public void arcadeButtonControl(int button)
    {
        if(worldController.inPractice)
        {
            if(button == 7)
            {
                practiceButtons[practiceButtonsSelectedX][practiceButtonsSelectedY].buttonFuction();
            }
            if(button == 6)
            {
                backMenu.buttonFuction();
            }
        }
        else
        {
            System.out.print(button);
            if(button == 7)
            {
                System.out.print("GO!");
                menuButtons[menuButtonSelected].buttonFuction();
            }
        }
    }

    public void arcadeMove(char axis, int value)
    {
        if(worldController.inPractice)
        {
            if(axis == 'x')
            {
                practiceButtonsSelectedX += value;
                practiceButtonsSelectedX = Math.abs(practiceButtonsSelectedX) % practiceButtons.length;

            }
            else
            {
                practiceButtonsSelectedY += value;
                practiceButtonsSelectedY = Math.abs(practiceButtonsSelectedY) % practiceButtons[0].length;
            }
        }
        else
        {
            if(axis == 'y')
            {
                menuButtonSelected = menuButtonSelected + value;
                menuButtonSelected = Math.abs(menuButtonSelected) % menuButtons.length;
            }
        }
    }
}
