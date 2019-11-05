package engine;

import model.PositionPool;
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
    public static final int FONT_SIZE = 36;
    public static final Font STANDARD_FONT = new Font("TimesRoman", Font.PLAIN, FONT_SIZE);
    public static final int TIMER_WARNING_START = 5;

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

        drawTimer(crayon, im);

        if(game.isFinished()) {
            drawWin(crayon);
        }
    }

    /**
     * Dessine la structure du level
     * @param g objet de dessin
     * @param img image sur laquelle dessiner
     */
    private void drawLabyrinth(Graphics2D g, BufferedImage img){
        //Création fond blanc
        g.setColor(Color.WHITE);
        g.fillRect(0,0, img.getWidth(), img.getHeight());

        //Dessin du cadrillage
        g.setColor(Color.BLACK);
        Position positionExtreme = PositionPool.getInstance().getPosition(Game.WIDTH - 1, Game.HEIGHT - 1);
        //Dessin des lignes
        for (int l = 0 ; l <= (positionExtreme.getY() + 1) * WORLD_UNIT ; l += WORLD_UNIT ){
            g.drawLine(0,  l, (positionExtreme.getX() + 1) * WORLD_UNIT, l );
        }
        //Dessin des colonnes
        for (int c = 0 ; c <= (positionExtreme.getX() + 1) * WORLD_UNIT ; c += WORLD_UNIT ){
            g.drawLine(c, 0, c , (positionExtreme.getY() + 1) * WORLD_UNIT );
        }

        //Dessin des murs
        g.setColor(Color.BLACK);
        for(Position p : game.getWallsPosition())  {
            g.fillRect(p.getX() * WORLD_UNIT, p.getY() * WORLD_UNIT, WORLD_UNIT, WORLD_UNIT);
        }

        //Dessin escalier ou trésor
        Position p;
        if(game.hasATreasureInLevel()){
            g.setColor(Color.ORANGE);
            p = game.getTreasurePosition();
        } else {
            g.setColor(Color.GREEN);
            p = game.getStairsPosition();
        }

        if(p != null)
            g.fillRect(p.getX() * WORLD_UNIT, p.getY() * WORLD_UNIT , WORLD_UNIT, WORLD_UNIT);

    }

    /**
     * Dessine l'ensemble des monstres du level
     * @param g objet de dessin
     * @param img image sur laquelle dessiner
     */
    private void drawMonsters(Graphics2D g, BufferedImage img) {
        //Couleur des monstres
        g.setColor(Color.RED);

        //Parcours de chaque position de monstre
        for(Position p : game.getMonstersPosition())  {
            g.fillOval(p.getX() * WORLD_UNIT, p.getY() * WORLD_UNIT, WORLD_UNIT, WORLD_UNIT);
        }
    }

    /**
     * Dessine le hero dans son level
     * @param g objet de dessin
     * @param img image sur laquelle dessiner
     */
    private void drawHero(Graphics2D g, BufferedImage img) {
        //Récupération de sa position
        Position heroPosition = game.getHeroPosition();

        //Dessin du hero
        g.setColor(Color.BLUE);
        g.fillOval(heroPosition.getX() * WORLD_UNIT,heroPosition.getY() * WORLD_UNIT,WORLD_UNIT,WORLD_UNIT);
    }

    private void drawTimer (Graphics2D g, BufferedImage img) {
        g.setFont(STANDARD_FONT);
        int timeLeft = game.getTimeLeft();

        g.setColor( ( timeLeft <= TIMER_WARNING_START) ?
                        Color.RED :
                        Color.BLACK );

        g.drawString("Move Left : "+timeLeft , 0, HEIGHT - ( FONT_SIZE / 4 * 6));
    }

    private void drawWin(Graphics2D g) {
        g.setFont(STANDARD_FONT);
        g.setColor(Color.DARK_GRAY);

        String endMessage;
        if (game.isGameWon()) {
            endMessage = "You win !";
        } else {
            endMessage = "You Loose !";
        }

        g.drawString(endMessage,0 ,HEIGHT - ( FONT_SIZE / 4 ));
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}
