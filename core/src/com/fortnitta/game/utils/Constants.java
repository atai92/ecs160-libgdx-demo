package com.fortnitta.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/** Stores unchanging app-wide assets and constants. */
public class Constants {

    /* The range, in pixels, at which two clicks locations varying by this amount are considered
       to be clicking the same location. Used for clicking castles, because my fingers are fat.
     */
    public static final float NEAR_DISTANCE = 50f;

    /* The size of each tile.
     *
     * I later learned tile sizes differ across tilesets. But w/e for proof of concept app.
     */
    public static final int TILE_SIZE = 12;

    /* Rendered textures can be scaled to any decimal */
    public static final float SCALE_FACTOR = 4.0f;

    /* Map texture objects */
    public static final Texture TEXTURE_TERRAIN = new Texture(Gdx.files.internal("2DTerrain.png"));
    public static final Texture TEXTURE_CASTLE_CANNON = new Texture(Gdx.files.internal("2DCastleCannon.png"));

    /* Locations of tiles within tilesets */
    public static final Vector2 TILE_LOCATION_TERRAIN_WATER = new Vector2(0, 24 * Constants.TILE_SIZE);
    public static final Vector2 TILE_LOCATION_TERRAIN_GRASS_DARK = new Vector2(0, 0 * Constants.TILE_SIZE);
    public static final Vector2 TILE_LOCATION_TERRAIN_GRASS_LIGHT = new Vector2(0, 1 * Constants.TILE_SIZE);
    public static final Vector2 TILE_LOCATION_CASTLE_CANNON_CASTLE_BROWN = new Vector2(0, 0 * Constants.TILE_SIZE);

    /* Sound objects */
    public static final Sound SOUND_TRIUMPH = Gdx.audio.newSound(Gdx.files.internal("triumph.wav"));
}
