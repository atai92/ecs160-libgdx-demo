package com.fortnitta.game.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fortnitta.game.framework.GameScene;
import com.fortnitta.game.models.Map;

/* Scene for Week 3 goals:
    - Load and render NorthSouth map file
    - Support castle selection
        - Each castle selection plays a sound
        - When all castles have been selected quit the game
 */
public class MainScene extends GameScene {

    public MainScene(SpriteBatch batch) {
        super(batch);
        AddDrawableComponent(new Map("NorthSouth.map"));
    }
}
