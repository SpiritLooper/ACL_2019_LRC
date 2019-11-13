package main.java.view.spriteManager.basicSprite;

import main.java.view.spriteManager.SpriteTileParser;

import java.io.IOException;

/**
 * Charge le sprite de l'hero
 */
public class Hero extends BasicSprite {

    private static final String path = SpriteTileParser.TEXTURE_PATH + "hero2.png";

    public Hero() throws IOException {
        super(path);
    }
}
