package com.fortnitta.game.framework;

import com.badlogic.gdx.utils.Disposable;


/** A game component provides a modular way of adding functionality to a game.
 *
 * Represents anything that could benefit from an Update() being called
 * on every frame (e.g. an InputManager checking if a key is down).
 */
public abstract class GameComponent implements Updateable, Comparable<GameComponent>, Disposable {

    protected abstract void initialize();
}
