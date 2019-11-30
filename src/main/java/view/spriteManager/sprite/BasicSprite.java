package view.spriteManager.sprite;

import view.spriteManager.SpriteTileParser;

import javax.imageio.ImageIO;
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
    protected BufferedImage sprite;

    public BasicSprite(String path, boolean autoResize) throws IOException {
        this.sprite = ImageIO.read(new File(path)) ;
        if (autoResize)
            this.sprite = SpriteTileParser.resizeBufferedImageAsWorldUnit(this.sprite);
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
