package engine;

import model.element.Hero;
import model.element.Position;
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
    protected static final int WIDTH = 800;
    protected static final int HEIGHT = 600;

    private static final int WORLD_UNIT = 50;

    /**
     * Jeu à dessiner
     */
    private Game game;

    public Painter(Game game) {
        this.game = game;
    }

    /**
     * Dessine le jeu
     * @param im image sur laquelle dessinee
     */
    public void draw(BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();

        drawLabyrinth(crayon,im);

        drawHero(crayon,im);

        drawMonsters(crayon, im);
    }

    private void drawLabyrinth(Graphics2D g, BufferedImage img){
        //Création fond blanc
        g.setColor(Color.WHITE);
        g.fillRect(0,0, img.getWidth(), img.getHeight());

        //Dessin du cadrillage
        g.setColor(Color.BLACK);
        Position positionExtreme = game.getMaxDimLevel();
            //Dessin des lignes
        for (int l = 0 ; l <= (positionExtreme.getX() + 1) * WORLD_UNIT ; l += WORLD_UNIT ){
            g.drawLine(0,  l, (positionExtreme.getX() + 1) * WORLD_UNIT, l );
        }
            //Dessin des colonnes
        for (int c = 0 ; c <= (positionExtreme.getY() + 1) * WORLD_UNIT ; c += WORLD_UNIT ){
            g.drawLine(c, 0, c , (positionExtreme.getY() + 1) * WORLD_UNIT );
        }
        //Dessin des murs
    }

    private void drawMonsters(Graphics2D g, BufferedImage img) {
        //Couleur des monstres
        g.setColor(Color.RED);

        //Parcours de chaque position de monstre
        for(Position p : game.getMonstersPosition())  {
            g.fillOval(p.getX() * WORLD_UNIT, p.getY() * WORLD_UNIT, WORLD_UNIT, WORLD_UNIT);
        }
    }

    private void drawHero(Graphics2D g, BufferedImage img) {
        //Récupération de sa position
        Position heroPosition = game.getHero().getPosition();

        //Dessin du hero
        g.setColor(Color.BLUE);
        g.fillOval(heroPosition.getX() * WORLD_UNIT,heroPosition.getY() * WORLD_UNIT,WORLD_UNIT,WORLD_UNIT);
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}
