package view;

import controller.Controller;
import controller.InputManager;

import javax.swing.*;

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
    public GUI(Painter gamePainter, Controller gameController){
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // attacher le panel contenant l'afficheur du game
        this.panel=new GamePanel(gamePainter);
        f.setContentPane(this.panel);

        //Creation du keyListener

        // attacher controller au panel du game
        this.panel.addKeyListener(new InputManager(gameController));

        f.pack();
        f.setVisible(true);
        f.getContentPane().setFocusable(true);
        f.getContentPane().requestFocus();
    }

    /**
     * mise a jour du dessin
     */
    public void paint() {
        this.panel.drawGame();
    }

}
