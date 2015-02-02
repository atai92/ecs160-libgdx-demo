package com.fortnitta.game.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.fortnitta.game.utils.Constants;
import com.fortnitta.game.utils.Is;

/**
 * Created by Alan on 1/31/2015.
 */
public class WaterTiler {
    public static void draw(SpriteBatch spriteBatch, int x, int y, char[][] map) {
        int WaterType = 0;
        if (y < map.length && y > 0) {
            if (map[y][x - 1] != ' ') {
                if (map[y-1][x+1] == ' ' && map[y+1][x-1] == ' ' && (map[y-1][x] != ' ' || map[y+1][x] != ' ')) {
                    WaterType = 2;
                } else {
                    WaterType = 1;
                }
            }
        }
        switch (WaterType) {
            case 1:
                if (Is.Even(x, y)) create(spriteBatch,x,y,Constants.TILE_LOCATION_TERRAIN_LEFT_SHORE_LIGHT);
                else create(spriteBatch,x,y,Constants.TILE_LOCATION_TERRAIN_LEFT_SHORE_DARK);
                break;
            case 2:
                if (Is.Even(x, y)) create(spriteBatch,x,y,Constants.TILE_LOCATION_TERRAIN_BOTTOM_LEFT_SHORE_LIGHT);
                else create(spriteBatch,x,y,Constants.TILE_LOCATION_TERRAIN_BOTTOM_LEFT_SHORE_DARK);
                break;
            default:
                break;
        }
    }

    //Actual drawing function for the water.
    private static void create(SpriteBatch sb, int x, int y, Vector2 TileLoc) {
        sb.draw(Constants.TEXTURE_TERRAIN,
                x * Constants.TILE_SIZE * Constants.X_SCALE_FACTOR,
                y * Constants.TILE_SIZE * Constants.Y_SCALE_FACTOR,
                Constants.TILE_SIZE * Constants.X_SCALE_FACTOR,
                Constants.TILE_SIZE * Constants.Y_SCALE_FACTOR,
                (int) TileLoc.x,
                (int) TileLoc.y,
                Constants.TILE_SIZE,
                Constants.TILE_SIZE,
                false, false
        );
    }
}

/*
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
 */