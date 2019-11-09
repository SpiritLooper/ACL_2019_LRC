package engine.spriteManager.basicSprite;

import engine.spriteManager.SpriteTileParser;

import java.io.IOException;

/**
 * Charge le sprite du tresor
 */
public class Treasure extends BasicSprite {

    private static final String path = SpriteTileParser.TEXTURE_PATH + "treasure.png";

    public Treasure() throws IOException {
        super(path);
    }
}
