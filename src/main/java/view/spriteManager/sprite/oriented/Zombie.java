package view.spriteManager.sprite.oriented;

import controller.Orientation;
import view.spriteManager.SpriteTileParser;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Charge le sprite du Zombie
 */
public class Zombie extends OrientedSprite{

    private static final String path = SpriteTileParser.TEXTURE_PATH + "zombie_tileset.png";

    public Zombie() throws IOException {
        super(path);
    }

    @Override
    protected void setSpritesMove(BufferedImage[][] spritesTab) {
        // HAUT
        spritesTab[Orientation.UP.ordinal()][0] = this.sprite.getSubimage(95,87,26,32);
        spritesTab[Orientation.UP.ordinal()][1] = this.sprite.getSubimage(122,87,26,32);
        spritesTab[Orientation.UP.ordinal()][2] = this.sprite.getSubimage(150,87,26,32);

        // BAS
        spritesTab[Orientation.DOWN.ordinal()][0] = this.sprite.getSubimage(95,55,26,32);
        spritesTab[Orientation.DOWN.ordinal()][1] = this.sprite.getSubimage(122,55,26,32);
        spritesTab[Orientation.DOWN.ordinal()][2] = this.sprite.getSubimage(150,55,26,32);

        // GAUCHE
        spritesTab[Orientation.LEFT.ordinal()][0] = this.sprite.getSubimage(88,120,30,32);
        spritesTab[Orientation.LEFT.ordinal()][1] = this.sprite.getSubimage(118,120,30,32);
        spritesTab[Orientation.LEFT.ordinal()][2] = this.sprite.getSubimage(152,120,30,32);

        // DROITE
        spritesTab[Orientation.RIGHT.ordinal()][0] = createFlipped( spritesTab[Orientation.LEFT.ordinal()][0] );
        spritesTab[Orientation.RIGHT.ordinal()][1] = createFlipped( spritesTab[Orientation.LEFT.ordinal()][1] );
        spritesTab[Orientation.RIGHT.ordinal()][2] = createFlipped( spritesTab[Orientation.LEFT.ordinal()][2] );
    }

    @Override
    protected void setSpriteIdle(BufferedImage[] idleSprite) {
        // HAUT
        idleSprite[Orientation.UP.ordinal()] = this.sprite.getSubimage(95,87,26,32);

        // BAS
        idleSprite[Orientation.DOWN.ordinal()] = this.sprite.getSubimage(95,55,26,32);

        // GAUCHE
        idleSprite[Orientation.LEFT.ordinal()] = this.sprite.getSubimage(88,120,30,32);

        // DROITE
        idleSprite[Orientation.RIGHT.ordinal()] = createFlipped( idleSprite[Orientation.LEFT.ordinal()] );
    }

    @Override
    protected void setSpritesAttack(BufferedImage[][] spritesTab) {
        // HAUT
        spritesTab[Orientation.UP.ordinal()][0] = this.sprite.getSubimage(95,87,26,32);
        spritesTab[Orientation.UP.ordinal()][1] = this.sprite.getSubimage(122,87,26,32);
        spritesTab[Orientation.UP.ordinal()][2] = this.sprite.getSubimage(150,87,26,32);

        // BAS
        spritesTab[Orientation.DOWN.ordinal()][0] = this.sprite.getSubimage(95,55,26,32);
        spritesTab[Orientation.DOWN.ordinal()][1] = this.sprite.getSubimage(122,55,26,32);
        spritesTab[Orientation.DOWN.ordinal()][2] = this.sprite.getSubimage(150,55,26,32);

        // GAUCHE
        spritesTab[Orientation.LEFT.ordinal()][0] = this.sprite.getSubimage(88,120,30,32);
        spritesTab[Orientation.LEFT.ordinal()][1] = this.sprite.getSubimage(118,120,30,32);
        spritesTab[Orientation.LEFT.ordinal()][2] = this.sprite.getSubimage(152,120,30,32);

        // DROITE
        spritesTab[Orientation.RIGHT.ordinal()][0] = createFlipped( spritesTab[Orientation.LEFT.ordinal()][0] );
        spritesTab[Orientation.RIGHT.ordinal()][1] = createFlipped( spritesTab[Orientation.LEFT.ordinal()][1] );
        spritesTab[Orientation.RIGHT.ordinal()][2] = createFlipped( spritesTab[Orientation.LEFT.ordinal()][2] );
    }

    @Override
    protected void setSufferingSprite(BufferedImage[] spritesTab) {
        // HAUT
        spritesTab[Orientation.UP.ordinal()] = this.sprite.getSubimage(39,123,32,32);

        // BAS
        spritesTab[Orientation.DOWN.ordinal()] = this.sprite.getSubimage(7,123,32,32);

        // GAUCHE
        spritesTab[Orientation.LEFT.ordinal()] = this.sprite.getSubimage(22,154,32,32);

        // DROITE
        spritesTab[Orientation.RIGHT.ordinal()] = createFlipped( spritesTab[Orientation.LEFT.ordinal()] );
    }
}
