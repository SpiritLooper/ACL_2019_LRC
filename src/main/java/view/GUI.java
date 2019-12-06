package view;

import controller.Controller;
import controller.InputManager;
import view.spriteManager.SpriteTileParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * Classe gérant la fenetre graphique du jeu
 * @author Horatiu Cirstea, Vincent Thomas, Julien Claisse
 */
public class GUI {

    /**
     * le Panel pour l'afficheur
     */
    private GamePanel panel;


    /**
     * la construction de l'interface graphique: JFrame avec panel pour le game
     *
     * @param gamePainter l'afficheur a utiliser dans le moteur
     * @param gameController l'afficheur a utiliser dans le moteur
     *
     */
    public GUI(Painter gamePainter, Controller gameController) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // attacher le panel contenant l'afficheur du game
        this.panel=new GamePanel(gamePainter);
        f.setContentPane(this.panel);

        //Creation du keyListener

        // attacher controller au panel du game
        this.panel.addKeyListener(new InputManager(gameController));

        f.pack();
        f.setTitle("Sad eevee");
        f.setVisible(true);
        f.getContentPane().setFocusable(true);
        f.getContentPane().requestFocus();
        f.setResizable(false);

        try {
            f.setIconImage(SpriteTileParser.resizeBufferedImageAsWorldUnit(ImageIO.read(new File("res/textures/hero2.png"))));
        } catch (IOException e) {
            System.err.println("Can't get icon image");
            e.printStackTrace();
        }

    }

    /**
     * mise a jour du dessin
     */
    public void paint(int iFrame) {
        this.panel.drawGame(iFrame);
    }

}
