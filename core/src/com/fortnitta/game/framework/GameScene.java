package com.fortnitta.game.framework;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;


public abstract class GameScene {

    protected List<GameComponent> mUpdateables;
    protected List<DrawableGameComponent> mDrawables;
    protected SpriteBatch mSpriteBatch;

    protected GameScene(SpriteBatch batch) {
        mSpriteBatch = batch;
        mUpdateables = new ArrayList<GameComponent>();
        mDrawables = new ArrayList<DrawableGameComponent>();
    }

    public void AddComponent(GameComponent component) {
        mUpdateables.add(component);
    }

    public void AddDrawableComponent(DrawableGameComponent component) {
        mDrawables.add(component);
    }

    public void initialize() {
        for (GameComponent component : mUpdateables)
            component.initialize();
        for (DrawableGameComponent component : mDrawables)
            component.initialize();
    }

    public void update() {
        for (GameComponent component : mUpdateables)
            component.update();
        for (DrawableGameComponent component : mDrawables)
            component.update();

    }
    public void draw() {
        for (DrawableGameComponent component : mDrawables)
            component.draw(mSpriteBatch);
    }
}
