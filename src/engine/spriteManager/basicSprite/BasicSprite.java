package engine.spriteManager.basicSprite;

import engine.Painter;

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
        this.sprite = resizeBufferedImageAsWorldUnit( ImageIO.read(new File(path)) ) ;
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

    /**
     * REdimmensionne l'image pour qu'elle corresponde à la taille de monde
     * @param bi image a redimmensionner
     * @return img redimensionnee
     */
    private BufferedImage resizeBufferedImageAsWorldUnit(BufferedImage bi){
        int w = bi.getWidth();
        int h = bi.getHeight();
        BufferedImage dimg = new BufferedImage(Painter.WORLD_UNIT, Painter.WORLD_UNIT, bi.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bi, 0, 0, Painter.WORLD_UNIT, Painter.WORLD_UNIT, 0, 0, w, h, null);
        g.dispose();
        return dimg;
    }
}
