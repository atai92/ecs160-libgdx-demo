package com.fortnitta.game.framework;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/** Defines the interface for a drawable game component. */
public interface Drawable {

    /** Draws the IDrawable, optionally using a SpriteBatch passed from the central ApplicationAdapter. */
    void draw(SpriteBatch spriteBatch);
}
