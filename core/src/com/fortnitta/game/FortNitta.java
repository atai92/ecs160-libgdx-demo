package com.fortnitta.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fortnitta.game.framework.GameScene;
import com.fortnitta.game.scenes.MainScene;

public class FortNitta extends ApplicationAdapter {
	SpriteBatch batch;
    MainScene mMainScene;
	
	@Override
	public void create () {
        batch = new SpriteBatch();
        mMainScene = new MainScene(batch);
        mMainScene.initialize();
	}

	@Override
	public void render () {
        mMainScene.update();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        mMainScene.draw();
		batch.end();
	}
}
