package com.fortnitta.game.framework;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

/** An autonomous game state.
 *
 * A GameScene is the implementation of a game state.
 * InputProcessor is a LibGDX interface for handling touch input.
 */
public abstract class GameScene implements InputProcessor {

    /** The list of game components. Each component's update() method is automatically called. */
    protected List<GameComponent> mUpdateables;

    /** The list of drawable components. Each component's draw() method is automatically called. */
    protected List<DrawableGameComponent> mDrawables;

    /** The SpriteBatch we received from ApplicationAdapter, passed to each child component draw() call. */
    protected SpriteBatch mSpriteBatch;

    private OrthographicCamera camera;

    protected GameScene(SpriteBatch batch) {
        mSpriteBatch = batch;
        mUpdateables = new ArrayList<GameComponent>();
        mDrawables = new ArrayList<DrawableGameComponent>();
    }

    /** Add a new component to our state/scene.
       These just spin with the game loop and update game logic.
    */
    public void AddComponent(GameComponent component) {
        mUpdateables.add(component);
    }

    /** Add a new drawable component to our state/scene.
       These spin with the game loop, update game logic, and draw themselves.
    */
    public void AddDrawableComponent(DrawableGameComponent component) {
        mDrawables.add(component);
    }

    /** Initialize all child components */
    public void initialize() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1920,1080);
        for (GameComponent component : mUpdateables)
            component.initialize();
        for (DrawableGameComponent component : mDrawables)
            component.initialize();
    }

    /** Update all child components */
    public void update() {
        for (GameComponent component : mUpdateables)
            component.update();
        for (DrawableGameComponent component : mDrawables)
            component.update();

    }

    /** Draw all child components */
    public void draw() {
        camera.update();
        for (DrawableGameComponent component : mDrawables)
            component.draw(mSpriteBatch);
    }
}
