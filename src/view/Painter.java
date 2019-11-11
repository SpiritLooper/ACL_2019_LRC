package view;

import view.drawMode.DrawGame;
import view.drawMode.DrawMenu;
import view.drawMode.DrawMode;
import model.game.Game;


import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * Classe permettant de dessiner le jeu à partir de l'image donnée
 * @author Julien Claisse
 */
public class Painter {
    /**
     * la taille des cases
     */
    public static final int WORLD_UNIT = 50;
    public static final int FONT_SIZE = 36;
    public static final Font STANDARD_FONT = new Font("TimesRoman", Font.PLAIN, FONT_SIZE);


    private DrawMode displayMode;

    private final DrawMenu MENU ;
    private final DrawGame GAME ;

    /**
     * Jeu à dessiner
     */
    private Game game;

    public Painter(Game game) {
        this.game = game;

        GAME = new DrawGame(game);
        MENU = new DrawMenu(game);

        displayMode = GAME;
    }

    /**
     * Dessine le jeu
     * @param im image sur laquelle dessinee
     */
    public void draw(BufferedImage im) {

        Graphics2D crayon = (Graphics2D) im.getGraphics();

        displayMode = (game.isMenuOpen()) ? MENU : GAME;

        displayMode.draw(crayon);
    }

    public static int getWidth() {
        return WORLD_UNIT * (Game.WIDTH + 2);
    }

    public static int getHeight() {
        return WORLD_UNIT * (Game.HEIGHT + 2);
    }

    public void updateNextLevel() {
        GAME.updateNextLevel();
    }
}
