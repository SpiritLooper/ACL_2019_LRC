package engine;

import engine.spriteManager.SpriteTileParser;
import engine.spriteManager.biomManager.BiomLevel;
import model.element.Position;
import model.game.Game;
import model.game.GameStatement;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
    public static final int TIMER_WARNING_START = 5;

    private BufferedImage heroSprite;
    private BufferedImage treasureSprite;
    private BufferedImage stairsSprite;
    private BufferedImage zombieSprite;
    private BufferedImage wildRoseSprite;

    private BiomLevel levelDesign;
    
    /**
     * Jeu à dessiner
     */
    private Game game;

    public Painter(Game game) {
        this.game = game;

        try {
            SpriteTileParser.loadSprites();
            heroSprite = SpriteTileParser.getHeroSprite();
            treasureSprite = SpriteTileParser.getTreasureSprite();
            stairsSprite = SpriteTileParser.getStairsSprite();
            zombieSprite = SpriteTileParser.getZombieSprite();
            wildRoseSprite = SpriteTileParser.getWildRoseSprite();
            levelDesign = SpriteTileParser.getLevelDesign();
        } catch (IOException e) {
            System.err.println("Load sprite failed");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Dessine le jeu
     * @param im image sur laquelle dessinee
     */
    public void draw(BufferedImage im) {

        Graphics2D crayon = (Graphics2D) im.getGraphics();

        GameStatement gameStat = game.getGameStatement();

        drawLabyrinth(crayon,im, gameStat);

        drawHero(crayon,im, gameStat);

        drawMonsters(crayon, im, gameStat);

        drawTimer(crayon, im);

        drawHp(crayon);

        if(game.isFinished()) {
            drawWin(crayon);
        }
    }

    /**
     * Dessine la structure du level
     * @param g objet de dessin
     * @param img image sur laquelle dessiner
     * @param gameStat
     */
    private void drawLabyrinth(Graphics2D g, BufferedImage img, GameStatement gameStat){
        //Création fond blanc
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0, img.getWidth(), img.getHeight());

        //Dessin du sol
        BufferedImage level = levelDesign.buildImageLevel(gameStat.getAllPosition(GameStatement.WALL));
        g.drawImage(level, 0,0, null);

        //Dessin escalier ou trésor
        Position p;
        if(gameStat.hasATresure()){
            p = gameStat.getFirstPosition(GameStatement.TREASURE);
            g.drawImage(treasureSprite, ( p.getX() + 1 ) * WORLD_UNIT, ( p.getY() + 1 ) * WORLD_UNIT , null);
        } else {
            p = gameStat.getFirstPosition(GameStatement.STAIRS);
            g.drawImage(stairsSprite, ( p.getX() + 1 ) * WORLD_UNIT, ( p.getY() + 1 ) * WORLD_UNIT , null);
        }

    }

    /**
     * Dessine l'ensemble des monstres du level
     * @param g objet de dessin
     * @param img image sur laquelle dessiner
     * @param gameStat
     */
    private void drawMonsters(Graphics2D g, BufferedImage img, GameStatement gameStat) {

        //Parcours de chaque position de Zombie
        for(Position p : gameStat.getAllPosition(GameStatement.ZOMBIE))  {
            g.drawImage(zombieSprite, ( p.getX() + 1 ) * WORLD_UNIT, ( p.getY() + 1 ) * WORLD_UNIT , null);
        }

        //Parcours de chaque position de Wild Rose
        for(Position p : gameStat.getAllPosition(GameStatement.WILD_ROSE))  {
            g.drawImage(wildRoseSprite,( p.getX() + 1 ) * WORLD_UNIT, ( p.getY() + 1 ) * WORLD_UNIT , null);
        }
    }

    /**
     * Dessine le hero dans son level
     * @param g objet de dessin
     * @param img image sur laquelle dessiner
     * @param gameStat
     */
    private void drawHero(Graphics2D g, BufferedImage img, GameStatement gameStat) {
        //Récupération de sa position
        Position heroPosition = gameStat.getFirstPosition(GameStatement.HERO);

        //Dessin du hero
        g.drawImage(heroSprite, ( heroPosition.getX() + 1 )* WORLD_UNIT , ( heroPosition.getY() + 1) * WORLD_UNIT, null );
    }

    private void drawTimer (Graphics2D g, BufferedImage img) {
        g.setFont(STANDARD_FONT);
        int timeLeft = game.getTimeLeft();

        g.setColor( ( timeLeft <= TIMER_WARNING_START) ?
                Color.RED :
                Color.WHITE );

        g.drawString("Moves Left : "+timeLeft , 0, getHeight() - ( FONT_SIZE / 4 * 6));
    }

    private void drawWin(Graphics2D g) {
        g.setFont(STANDARD_FONT);
        g.setColor(Color.LIGHT_GRAY);

        String endMessage;
        if (game.isGameWon()) {
            endMessage = "You win !";
        } else {
            endMessage = "You Loose !";
        }

        g.drawString(endMessage,0 ,getHeight() - ( FONT_SIZE / 4 ));
    }

    /**
     * Draw the hero's life
     * @param g
     */
    private void drawHp(Graphics2D g){
        int hp = game.getLevel().heroLife();
        int timeLeft = game.getTimeLeft();

        if (hp > 0 && timeLeft > 0 && !game.isGameWon()) {
            g.setFont(STANDARD_FONT);
            g.setColor(Color.GREEN);

            g.drawString("HP: "+game.getLevel().heroLife(),0, getHeight() - (FONT_SIZE / 4));
        }

    }

    public int getWidth() {
        return WORLD_UNIT * (Game.WIDTH + 2);
    }

    public int getHeight() {
        return WORLD_UNIT * (Game.HEIGHT + 2);
    }
}
