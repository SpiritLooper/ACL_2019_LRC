package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Classe gerant la mise a jour des images du jeu
 * @author Horatiu Cirstea, Vincent Thomas, Julien Claisse
 */
public class GamePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Le dessineur du jeu
     */
    private Painter painter;

    /**
     * image suivante est l'image cachee sur laquelle dessiner
     */
    private BufferedImage nextImage;

    /**
     * image en cours est l'image entrain d'etre affichee
     */
    private BufferedImage currentImage;

    /**
     * la taille des images
     */
    private int width, height;

    /**
     * constructeur Il construit les images pour doublebuffering ainsi que le
     * Panel associe. Les images stockent le painter et on demande au panel la
     * mise a jour quand le painter est fini
     *
     *  width largeur de l'image
     *  height hauteur de l'image
     */
    public GamePanel(Painter painter) {
        super();
        this.width = painter.getWidth();
        this.height = painter.getHeight();
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.painter=painter;

        // cree l'image buffer et son graphics
        this.nextImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        this.currentImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
    }

    /**
     * demande de mettre a jour le rendu de l'image sur le Panel. Creer une
     * nouvelle image vide sur laquelle dessiner
     */
    public void drawGame(int iFrame) {
        // generer la nouvelle image
        this.painter.draw(this.nextImage, iFrame);

        // inverses les images doublebuffereing
        BufferedImage temp = this.currentImage;
        // l'image a dessiner est celle qu'on a construite
        this.currentImage = this.nextImage;
        // l'ancienne image est videe
        this.nextImage = temp;
        this.nextImage.getGraphics()
                .fillRect(0, 0, this.width, this.height);
        // met a jour l'image a afficher sur le panel

        this.repaint();
    }

    /**
     * redefinit la methode paint consiste a dessiner l'image en cours
     *
     * @param g
     *            graphics pour dessiner
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.currentImage, 0, 0, getWidth(), getHeight(), 0, 0,
                getWidth(), getHeight(), null);
    }
}
