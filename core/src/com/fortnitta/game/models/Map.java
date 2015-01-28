package com.fortnitta.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.fortnitta.game.framework.DrawableGameComponent;
import com.fortnitta.game.framework.GameComponent;
import com.fortnitta.game.utils.Constants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;


/** Maintains an internal 2D array describing tiles and their locations.
 * As a DrawableGameComponent, it update() and draw() itself. */
public class Map extends DrawableGameComponent {

    /** The non-absolute file path to the .map file asset. */
    protected String mMapFilePath;

    /** The dimensions of our internal 2D array. */
    protected int mWidth, mHeight;

    /** The internal 2D array describing the tile type by char (this is bad).
       The tile location is not described in this array; it is described by the tile type itself.
           A "water" tile represented by character " " (space).
           A long if-else ladder chain sees "if water, use this tileset to draw, else .."
           The appropriate tileset is used to draw the texture.
     */
    protected char[][] mMap;

    /** This stores the locations of the map's castles.
       Screen click locations are compared to castle locations; if close enough, play sound.
    */
    private Array<Vector2> mCastleLocations;

    /**
     * This stores the locations of our clicks, only if they were "near" a castle.
     */
    private Array<Vector2> mCastleClickLocations = new Array<Vector2>();

    public Map(String mapFilePath) {
        mMapFilePath = mapFilePath;
    }

    @Override
    public void initialize() {
        loadMap();
    }

    /** Reads Nitta's .map file and creates an internal 2D array of chars representing the map grid and tile type. */
    private void loadMap() {
        try {
            /* Use Gdx's internal file handler to create a file handle; it knows where its assets are */
            FileHandle fileHandle = Gdx.files.internal(mMapFilePath);
            BufferedReader br = fileHandle.reader(1024);
            // Read the map file

            // Ignore the first line
            String firstLine = br.readLine();

            // Read the map dimensions
            String mapDimensions = br.readLine();
            String[] mapDimensionsSplit = mapDimensions.split(" ", 2);
            mWidth = Integer.parseInt(mapDimensionsSplit[0]) + 2;
            mHeight = Integer.parseInt(mapDimensionsSplit[1]) + 2;

            mMap = new char[mHeight][mWidth];

            // For each row
            for (int y = 0; y < mHeight; y++) {
                String scanLine = br.readLine();

                // For each character, in the row
                for (int x = 0; x < scanLine.length(); x++) {
                    // Get its value from the .map file (e.g. 'B', 'R', or ' ')
                    char mapTileChar = scanLine.charAt(x);
                    // Assign it directly to our 2D char[[]
                    mMap[y][x] = mapTileChar;
                }
            }

            // Read the number of castles on the map
            int numCastles = Integer.parseInt(br.readLine());
            mCastleLocations = new Array<Vector2>(numCastles);

            // For each next line
            for (int i = 0; i < numCastles; i++) {
                // Read the castle location
                String castleLocation = br.readLine();
                String[] castleDimensionsSplit = castleLocation.split(" ", 2);
                int x = Integer.parseInt(castleDimensionsSplit[0]);
                int y = Integer.parseInt(castleDimensionsSplit[1]);
                // Set the character at the array indices to 'C' to represent 'Castle'
                mMap[y][x] = 'C';
                mCastleLocations.add(new Vector2(x * Constants.TILE_SIZE * Constants.SCALE_FACTOR,
                                                 y * Constants.TILE_SIZE * Constants.SCALE_FACTOR));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /** A helper method to print the internal 2D array of chars. Goes to LogCat. */
    public void debugPrintMap() {
        for (int row = 0; row < mHeight; row++) {
            for (int col = 0; col < mWidth; col++) {
                System.out.print(mMap[row][col]);
            }
            System.out.println();
        }
    }

    /** Occurs when a user releases a tap
     *
     * Parameters: See @ https://github.com/libgdx/libgdx/wiki/Event-handling
     */
    public boolean touchUp(int x, int y, int pointer, int button) {

        Vector2 clickLocation = new Vector2(x, y);

        /* Is there a castle "near" where we clicked? */
        if (isCastleNear(clickLocation)) {
            /* If there exists a Castle near this location */

            /* Play a sound */
            Constants.SOUND_TRIUMPH.play(0.5f);

            /* If we've never clicked near this castle before */
            if (!mCastleClickLocations.contains(clickLocation, false)) {
                /* Remember this location so we don't count clicks near this location again */
                mCastleClickLocations.add(clickLocation);
            }
        }

        /* Let the game know this event was handled */
        return true;
    }

    /** Returns true if a castle is "near" where the specified location */
    public boolean isCastleNear(Vector2 clickLocation) {
        /* Recall x and y are in device screen coordinates, in pixel units
           My phone has a 1920 x 1080 resolution, so example value is x: 576, y: 999

           On the other hand, our castle locations are stored in tile units. So a
           castle location at (1, 1) really means (1 * TILE_SIZE, 1 * TILE_SIZE) = (12, 12).
         */

        /* For each stored castle location */
        for (Vector2 castleLocation : mCastleLocations) {
            /* If the distance between the stored castle location and my click */
            float distance_in_px = castleLocation.dst(clickLocation);
            /* Is within a tolerable range, just say "yes, you clicked near a castle" */
            if (distance_in_px <= Constants.NEAR_DISTANCE)
                return true;
        }

        return false;
    }

    /** Not used yet. */
    @Override
    public int compareTo(GameComponent o) {
        return 0;
    }

    /** Not used yet */
    @Override
    public void dispose() {
    }

    /** Called repeatedly as part of our game loop, to update game logic. */
    @Override
    public void update() {
        /* If we've clicked 10 castles, exit the game */
        if (mCastleClickLocations.size >= 10)
            Gdx.app.exit();
    }

    /** Called repeatedly as part of our game loop, to update the screen's graphics. */
    @Override
    public void draw(SpriteBatch spriteBatch) {
        try {
            for (int y = 0; y < mHeight; y++) {
                for (int x = 0; x < mWidth; x++) {
                    char mapTileChar = mMap[y][x];

                    switch (mapTileChar) {
                        case 'C':
                            /* See drawTile for a description of the params */
                            this.drawTile(spriteBatch, Constants.TEXTURE_CASTLE_CANNON,
                                    x,
                                    y,
                                    Constants.TILE_LOCATION_CASTLE_CANNON_CASTLE_BROWN);
                            break;
                        case 'B':
                            this.drawTile(spriteBatch, Constants.TEXTURE_TERRAIN,
                                    x,
                                    y,
                                    Constants.TILE_LOCATION_TERRAIN_GRASS_DARK);
                            break;
                        case 'R':
                            this.drawTile(spriteBatch, Constants.TEXTURE_TERRAIN,
                                    x,
                                    y,
                                    Constants.TILE_LOCATION_TERRAIN_GRASS_LIGHT);
                            break;
                        case ' ':
                            this.drawTile(spriteBatch, Constants.TEXTURE_TERRAIN,
                                    x,
                                    y,
                                    Constants.TILE_LOCATION_TERRAIN_WATER);
                            break;
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to draw a single tile, from our map grid.
     *
     * @param spriteBatch The SpriteBatch provided from ApplicationAdapter.
     * @param tileset Use this tileset to draw our tile.
     * @param srcTileX The on-screen X coordinate to draw to.
     * @param srcTileY  The on-screen X coordinate to draw to.
     * @param tileLocation  The pixel coordinates, in the tileset, of the top-left corner of our tile.
     * @return whether the input was processed
     */
    private void drawTile(SpriteBatch spriteBatch, Texture tileset, int srcTileX, int srcTileY, Vector2 tileLocation) {
        /* Using the SpriteBatch provided from ApplicationAdapter */
        spriteBatch.draw(
                /* Using the specified tileset */
                tileset,
                /* Draw to this on-screen X coordinate */
                srcTileX * Constants.TILE_SIZE * Constants.SCALE_FACTOR,
                /* Draw to this on-screen Y coordinate */
                srcTileY * Constants.TILE_SIZE * Constants.SCALE_FACTOR,
                /* Draw it this wide */
                Constants.TILE_SIZE * Constants.SCALE_FACTOR,
                /* And draw it just as tall */
                Constants.TILE_SIZE * Constants.SCALE_FACTOR,
                /* X coordinate, in the tileset, of the top-left corner of our tile. */
                (int)tileLocation.x,
                /* Y coordinate, in the tileset, of the top-left corner of our tile. */
                (int)tileLocation.y,
                /* The width of our tile */
                Constants.TILE_SIZE,
                /* The height of our tile */
                Constants.TILE_SIZE,
                /* Do not flip the image on either axis. */
                false, false
            );
    }
}
