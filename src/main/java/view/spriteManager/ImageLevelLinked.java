package view.spriteManager;

import java.awt.image.BufferedImage;

/**
 * Classe permettant de lier des iamges de chaque level
 */
public class ImageLevelLinked {
    private BufferedImage current;

    private ImageLevelLinked next;

    public ImageLevelLinked(BufferedImage current) {
        this.current = current;
    }

    /**
     * Donne le prochois objet lier
     * @return lobjet contenant la prochaine image
     */
    public ImageLevelLinked next() {
        return next;
    }

    /**
     * Donne limage contenu
     * @return l'image
     */
    public BufferedImage getImage() {
        return current;
    }

    public void add(BufferedImage bi) {
        if ( next == null ) {
            next = new ImageLevelLinked(bi);
        } else {
            next.add(bi);
        }
    }

    public ImageLevelLinked clone() {
        ImageLevelLinked res = new ImageLevelLinked(this.current);
        if(next != null)
            res.next = this.next.clone();
        return res;
    }
}
