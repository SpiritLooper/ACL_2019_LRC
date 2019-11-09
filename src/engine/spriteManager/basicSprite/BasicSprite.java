package engine.spriteManager.basicSprite;

import engine.Painter;
import engine.spriteManager.SpriteTileParser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Gestion d un sprite movible
 */
 public abstract class BasicSprite {

    /**
     * Image stockee
     */
    private BufferedImage sprite;

    public BasicSprite(String path) throws IOException {
        this.sprite = SpriteTileParser.resizeBufferedImageAsWorldUnit( ImageIO.read(new File(path)) ) ;
    }

    /**
     *
     * @return largeur de l'image
     */
    public int getWidht() {
        return sprite.getWidth();
    }

    /**
     *
     * @return hauteur de l'image
     */
    public int getHeight() {
        return sprite.getHeight();
    }

    /**
     * Donne l'image et son orientation d'apres sa derniere position
     * @return sprite de la bonne orientation
     */
    public BufferedImage getSprite() {
        return sprite;
    }


}
