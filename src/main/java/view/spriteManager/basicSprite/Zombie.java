package main.java.view.spriteManager.basicSprite;

import main.java.view.spriteManager.SpriteTileParser;

import java.io.IOException;

/**
 * Charge le sprite du Zombie
 */
public class Zombie extends BasicSprite {

    private static final String path = SpriteTileParser.TEXTURE_PATH + "zombie2.png";

    public Zombie() throws IOException {
        super(path);
    }
}