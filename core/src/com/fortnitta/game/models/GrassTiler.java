package com.fortnitta.game.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fortnitta.game.utils.Constants;

/**
 * Created by Alan on 1/31/2015.
 */
public class GrassTiler {
    public static void draw(SpriteBatch spriteBatch, int x, int y) {
        if ((x%2 == 0 && y%2 == 0) || (x%2 == 1 && y%2 == 1)) {
            spriteBatch.draw(Constants.TEXTURE_TERRAIN,
                    x * Constants.TILE_SIZE * Constants.X_SCALE_FACTOR,
                    y * Constants.TILE_SIZE * Constants.Y_SCALE_FACTOR,
                    Constants.TILE_SIZE * Constants.X_SCALE_FACTOR,
                    Constants.TILE_SIZE * Constants.Y_SCALE_FACTOR,
                    (int) Constants.TILE_LOCATION_TERRAIN_GRASS_DARK.x,
                    (int) Constants.TILE_LOCATION_TERRAIN_GRASS_DARK.y,
                    Constants.TILE_SIZE,
                    Constants.TILE_SIZE,
                    false, false
            );
        } else if ((x%2 == 1 && y%2 == 0) || (x%2 == 0 && y%2 == 1)) {
            spriteBatch.draw(Constants.TEXTURE_TERRAIN,
                    x * Constants.TILE_SIZE * Constants.X_SCALE_FACTOR,
                    y * Constants.TILE_SIZE * Constants.Y_SCALE_FACTOR,
                    Constants.TILE_SIZE * Constants.X_SCALE_FACTOR,
                    Constants.TILE_SIZE * Constants.Y_SCALE_FACTOR,
                    (int) Constants.TILE_LOCATION_TERRAIN_GRASS_LIGHT.x,
                    (int) Constants.TILE_LOCATION_TERRAIN_GRASS_LIGHT.y,
                    Constants.TILE_SIZE,
                    Constants.TILE_SIZE,
                    false, false
            );
        }
    }
}
