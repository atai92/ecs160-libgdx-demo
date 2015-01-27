package com.fortnitta.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fortnitta.game.framework.DrawableGameComponent;
import com.fortnitta.game.framework.GameComponent;
import com.fortnitta.game.utils.Constants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Map extends DrawableGameComponent {

    protected String mMapFilePath;
    protected int mWidth, mHeight;
    protected char[][] mMap;

    public Map(String mapFilePath) {
        mMapFilePath = mapFilePath;
    }

    @Override
    public void initialize() {
        loadMap();
    }

    private void loadMap() {
        try {
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

            for (int y = 0; y < mHeight; y++) {
                String scanLine = br.readLine();

                for (int x = 0; x < scanLine.length(); x++) {
                    char mapTileChar = scanLine.charAt(x);
                    mMap[y][x] = mapTileChar;
                }
            }

            int numCastles = Integer.parseInt(br.readLine());

            for (int i = 0; i < numCastles; i++) {
                String castleDimensions = br.readLine();
                String[] castleDimensionsSplit = castleDimensions.split(" ", 2);
                int x = Integer.parseInt(castleDimensionsSplit[0]);
                int y = Integer.parseInt(castleDimensionsSplit[1]);
                mMap[y][x] = 'C';
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void debugPrintMap() {
        for (int row = 0; row < mHeight; row++) {
            for (int col = 0; col < mWidth; col++) {
                System.out.print(mMap[row][col]);
            }
            System.out.println();
        }
    }

    @Override
    public int compareTo(GameComponent o) {
        return 0;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        try {
            for (int y = 0; y < mHeight; y++) {
                for (int x = 0; x < mWidth; x++) {
                    char mapTileChar = mMap[y][x];

                    switch (mapTileChar) {
                        case 'C':
                            spriteBatch.draw(Constants.TEXTURE_2D_CASTLE_CANNON,
                                    x * Constants.TEXTURE_SIZE * Constants.SCALE_FACTOR,
                                    y * Constants.TEXTURE_SIZE * Constants.SCALE_FACTOR,
                                    Constants.TEXTURE_SIZE * Constants.SCALE_FACTOR,
                                    Constants.TEXTURE_SIZE * Constants.SCALE_FACTOR,
                                    (int)Constants.TEXTURE_2D_CASTLE_CANNON_CASTLE_BROWN.x,
                                    (int)Constants.TEXTURE_2D_CASTLE_CANNON_CASTLE_BROWN.y,
                                    Constants.TEXTURE_SIZE,
                                    Constants.TEXTURE_SIZE,
                                    false, false
                            );
                            break;
                        case 'B':
                            spriteBatch.draw(Constants.TEXTURE_2D_TERRAIN,
                                    x * Constants.TEXTURE_SIZE * Constants.SCALE_FACTOR,
                                    y * Constants.TEXTURE_SIZE * Constants.SCALE_FACTOR,
                                    Constants.TEXTURE_SIZE * Constants.SCALE_FACTOR,
                                    Constants.TEXTURE_SIZE * Constants.SCALE_FACTOR,
                                    (int)Constants.TEXTURE_2D_TERRAIN_GRASS_DARK.x,
                                    (int)Constants.TEXTURE_2D_TERRAIN_GRASS_DARK.y,
                                    Constants.TEXTURE_SIZE,
                                    Constants.TEXTURE_SIZE,
                                    false, false
                            );
                            break;
                        case 'R':
                            spriteBatch.draw(Constants.TEXTURE_2D_TERRAIN,
                                    x * Constants.TEXTURE_SIZE * Constants.SCALE_FACTOR,
                                    y * Constants.TEXTURE_SIZE * Constants.SCALE_FACTOR,
                                    Constants.TEXTURE_SIZE * Constants.SCALE_FACTOR,
                                    Constants.TEXTURE_SIZE * Constants.SCALE_FACTOR,
                                    (int)Constants.TEXTURE_2D_TERRAIN_GRASS_LIGHT.x,
                                    (int)Constants.TEXTURE_2D_TERRAIN_GRASS_LIGHT.y,
                                    Constants.TEXTURE_SIZE,
                                    Constants.TEXTURE_SIZE,
                                    false, false
                            );
                            break;
                        case ' ':
                            spriteBatch.draw(Constants.TEXTURE_2D_TERRAIN,
                                    x * Constants.TEXTURE_SIZE * Constants.SCALE_FACTOR,
                                    y * Constants.TEXTURE_SIZE * Constants.SCALE_FACTOR,
                                    Constants.TEXTURE_SIZE * Constants.SCALE_FACTOR,
                                    Constants.TEXTURE_SIZE * Constants.SCALE_FACTOR,
                                    (int)Constants.TEXTURE_2D_TERRAIN_WATER.x,
                                    (int)Constants.TEXTURE_2D_TERRAIN_WATER.y,
                                    Constants.TEXTURE_SIZE,
                                    Constants.TEXTURE_SIZE,
                                    false, false
                            );
                            break;
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }
}
