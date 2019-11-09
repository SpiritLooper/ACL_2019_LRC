package engine.spriteManager.basicSprite;

import engine.spriteManager.SpriteTileParser;

import java.io.IOException;

/**
 * Charge le sprite de l'escalier
 */
public class Stairs extends BasicSprite {

    private static final String path = SpriteTileParser.TEXTURE_PATH + "stairs.png";

    public Stairs() throws IOException {
        super(path);
    }
}
