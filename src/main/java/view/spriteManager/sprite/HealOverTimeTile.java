package view.spriteManager.sprite;

import view.spriteManager.SpriteTileParser;

import java.io.IOException;

public class HealOverTimeTile extends BasicSprite {

    private static final String path = SpriteTileParser.TEXTURE_PATH + "heal_over_time.png";

    public HealOverTimeTile() throws IOException {
        super(path);
    }
}
