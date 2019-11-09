package engine.spriteManager.basicSprite;

import engine.spriteManager.SpriteTileParser;

import java.io.IOException;

/**
 * Charge le sprite de la rose
 */
public class WildRose extends BasicSprite {

    private static final String path = SpriteTileParser.TEXTURE_PATH + "wild_rose.png";

    public WildRose() throws IOException {
        super(path);
    }
}
