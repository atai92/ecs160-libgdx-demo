package com.fortnitta.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fortnitta.game.framework.GameScene;
import com.fortnitta.game.scenes.MainScene;

/** Our "main" class.
 *
 * The render() method is the main game loop.
 *
 * The game's render() calls GameScene's update() and draw().
 * In turn, each GameScene's GameComponent and DrawableGameComponent's update() and draw() is called.
 * Each component updates and draws itself, in an organized hierarchy (which, at the moment, is just Map).
 */
public class FortNitta extends ApplicationAdapter {

    /** The SpriteBatch that makes our OpenGL calls so much easier */
	SpriteBatch batch;

    /** Our (only) game state that contains drawable components like our Map */
    MainScene mMainScene;


    /** See @ https://github.com/libgdx/libgdx/wiki/The-life-cycle
     *
     * Method called once when the application is created.
     */
	@Override
	public void create () {
        batch = new SpriteBatch();
        mMainScene = new MainScene(batch);
        mMainScene.initialize();
        Gdx.input.setInputProcessor(mMainScene);
	}


    /** See @ https://github.com/libgdx/libgdx/wiki/The-life-cycle
     *
     * Method called by the game loop from the application every time rendering should be performed.
     * Game logic updates are usually also performed in this method.
     */
	@Override
	public void render () {

        /* Our future game will have something like:
         *
         * GameState activeGameState = StateMachine.getActiveState();
         * activeGameState.update();
         * activeGameState.draw();
         */

        /* Call update() on all the child components of our active game state */
        mMainScene.update();

        /* Clear the screen before drawing anything new */
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /* Tell LibGDX we're about to make a bunch of drawing calls */

        /* Enable alpha transparency */
        batch.enableBlending();
		batch.begin();

        /* Call draw() on all the child components of our active game state */
        mMainScene.draw();

        /* Stop drawing */
		batch.end();
	}
}
