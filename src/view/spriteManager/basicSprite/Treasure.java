package view.spriteManager.basicSprite;

import view.spriteManager.SpriteTileParser;

import java.io.IOException;

/**
 * Charge le sprite du tresor
 */
public class Treasure extends BasicSprite {

    private static final String path = SpriteTileParser.TEXTURE_PATH + "treasure2.png";

    public Treasure() throws IOException {
        super(path);
    }
}
