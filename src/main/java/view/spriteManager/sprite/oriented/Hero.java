package view.spriteManager.sprite.oriented;

import controller.Orientation;
import view.spriteManager.SpriteTileParser;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Charge le sprite de l'hero
 */
public class Hero extends OrientedSprite {

    private static final String path = SpriteTileParser.TEXTURE_PATH + "hero_tileset.png";

    public Hero() throws IOException {
        super(path);
    }

    @Override
    protected void setSpritesMove(BufferedImage[][] spritesTab) {
        // HAUT
        spritesTab[Orientation.UP.ordinal()][0] = this.sprite.getSubimage(143,68,25,25);
        spritesTab[Orientation.UP.ordinal()][1] = this.sprite.getSubimage(174,68,25,25);
        spritesTab[Orientation.UP.ordinal()][2] = this.sprite.getSubimage(205,68,25,25);

        // BAS
        spritesTab[Orientation.DOWN.ordinal()][0] = this.sprite.getSubimage(143,39,25,25);
        spritesTab[Orientation.DOWN.ordinal()][1] = this.sprite.getSubimage(174,39,25,25);
        spritesTab[Orientation.DOWN.ordinal()][2] = this.sprite.getSubimage(205,38,25,25);

        // GAUCHE
        spritesTab[Orientation.LEFT.ordinal()][0] = this.sprite.getSubimage(140,96,25,25);
        spritesTab[Orientation.LEFT.ordinal()][1] = this.sprite.getSubimage(172,96,25,25);
        spritesTab[Orientation.LEFT.ordinal()][2] = this.sprite.getSubimage(205,96,25,25);

        // DROITE
        spritesTab[Orientation.RIGHT.ordinal()][0] = createFlipped( spritesTab[Orientation.LEFT.ordinal()][0] );
        spritesTab[Orientation.RIGHT.ordinal()][1] = createFlipped( spritesTab[Orientation.LEFT.ordinal()][1] );
        spritesTab[Orientation.RIGHT.ordinal()][2] = createFlipped( spritesTab[Orientation.LEFT.ordinal()][2] );
    }

    @Override
    protected void setSpriteIdle(BufferedImage[] idleSprite) {
        //HAUT
        idleSprite[Orientation.UP.ordinal()] = this.sprite.getSubimage(73,56,23,23);
        //BAS
        idleSprite[Orientation.DOWN.ordinal()] = this.sprite.getSubimage(72,28,23,23);
        //GAUCHE
        idleSprite[Orientation.LEFT.ordinal()] = this.sprite.getSubimage(73,80,24,24);
        //DROITE
        idleSprite[Orientation.RIGHT.ordinal()] = this.sprite.getSubimage(75,105,24,24);
    }

    @Override
    protected void setSpritesAttack(BufferedImage[][] spritesTab) {
        // HAUT
        spritesTab[Orientation.UP.ordinal()][0] = this.sprite.getSubimage(143,68,25,25);
        spritesTab[Orientation.UP.ordinal()][1] = this.sprite.getSubimage(174,68,25,25);
        spritesTab[Orientation.UP.ordinal()][2] = this.sprite.getSubimage(205,68,25,25);

        // BAS
        spritesTab[Orientation.DOWN.ordinal()][0] = this.sprite.getSubimage(143,39,25,25);
        spritesTab[Orientation.DOWN.ordinal()][1] = this.sprite.getSubimage(174,39,25,25);
        spritesTab[Orientation.DOWN.ordinal()][2] = this.sprite.getSubimage(205,38,25,25);

        // GAUCHE
        spritesTab[Orientation.LEFT.ordinal()][0] = this.sprite.getSubimage(140,96,25,25);
        spritesTab[Orientation.LEFT.ordinal()][1] = this.sprite.getSubimage(172,96,25,25);
        spritesTab[Orientation.LEFT.ordinal()][2] = this.sprite.getSubimage(205,96,25,25);

        // DROITE
        spritesTab[Orientation.RIGHT.ordinal()][0] = createFlipped( spritesTab[Orientation.LEFT.ordinal()][0] );
        spritesTab[Orientation.RIGHT.ordinal()][1] = createFlipped( spritesTab[Orientation.LEFT.ordinal()][1] );
        spritesTab[Orientation.RIGHT.ordinal()][2] = createFlipped( spritesTab[Orientation.LEFT.ordinal()][2] );

    }

    @Override
    protected void setSufferingSprite(BufferedImage[] spritesTab) {
        // HAUT
        spritesTab[Orientation.UP.ordinal()] = this.sprite.getSubimage(36,122,25,25);

        // BAS
        spritesTab[Orientation.DOWN.ordinal()] = this.sprite.getSubimage(14,122,25,25);

        // GAUCHE
        spritesTab[Orientation.LEFT.ordinal()] = this.sprite.getSubimage(9,149,25,25);

        // DROITE
        spritesTab[Orientation.RIGHT.ordinal()] = createFlipped( spritesTab[Orientation.LEFT.ordinal()] );
    }
}
