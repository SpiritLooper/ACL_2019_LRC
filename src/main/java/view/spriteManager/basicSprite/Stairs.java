package main.java.view.spriteManager.basicSprite;

import main.java.view.spriteManager.SpriteTileParser;

import java.io.IOException;

/**
 * Charge le sprite de l'escalier
 */
public class Stairs extends BasicSprite {

    private static final String path = SpriteTileParser.TEXTURE_PATH + "stairs2.png";

    public Stairs() throws IOException {
        super(path);
    }
}
