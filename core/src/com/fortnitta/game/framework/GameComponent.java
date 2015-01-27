package com.fortnitta.game.framework;

import com.badlogic.gdx.utils.Disposable;

public abstract class GameComponent implements Updateable, Comparable<GameComponent>, Disposable {

    protected abstract void initialize();
}
