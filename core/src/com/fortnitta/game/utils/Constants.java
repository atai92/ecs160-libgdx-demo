package com.fortnitta.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;


public class Constants {
    // 12 x 12 pixels
    public static final int TEXTURE_SIZE = 12;
    public static final float SCALE_FACTOR = 4.0f;

    public static final Texture TEXTURE_2D_TERRAIN = new Texture(Gdx.files.internal("2DTerrain.png"));
    public static final Texture TEXTURE_2D_CASTLE_CANNON = new Texture(Gdx.files.internal("2DCastleCannon.png"));

    public static final Vector2 TEXTURE_2D_TERRAIN_BLUE = new Vector2(0, 26 * 12);
    public static final Vector2 TEXTURE_2D_TERRAIN_RED = new Vector2(0, 27 * 12);
    public static final Vector2 TEXTURE_2D_TERRAIN_WATER = new Vector2(0, 24 * 12);
    public static final Vector2 TEXTURE_2D_TERRAIN_GRASS_DARK = new Vector2(0, 0 * 12);
    public static final Vector2 TEXTURE_2D_TERRAIN_GRASS_LIGHT = new Vector2(0, 1 * 12);
    public static final Vector2 TEXTURE_2D_CASTLE_CANNON_CASTLE_BROWN = new Vector2(0, 0 * 12);
}
