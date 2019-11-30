package view.spriteManager.sprite;

import view.spriteManager.SpriteTileParser;

import java.io.IOException;

public class HealTile extends BasicSprite {

    private static final String path = SpriteTileParser.TEXTURE_PATH + "heal.png";

    public HealTile() throws IOException {
        super(path);
    }
}
