package main.java.view.drawMode;

import main.java.view.Painter;
import main.java.view.spriteManager.SpriteTileParser;
import main.java.model.element.Position;
import main.java.model.game.Game;
import main.java.model.game.GameStatement;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static main.java.view.Painter.*;

/**
 * Dessine le jeu vu dans le level
 */
public class DrawGame implements DrawMode {

    private static final int TIMER_WARNING_START = 5;

    private Game game;

    private BufferedImage heroSprite;
    private BufferedImage treasureSprite;
    private BufferedImage stairsSprite;
    private BufferedImage zombieSprite;
    private BufferedImage wildRoseSprite;
    private BufferedImage healTile;
    private BufferedImage healOverTimeTile;

    private BufferedImage levelBackground ; // Contient le sol et les murs dessinee

    public DrawGame(Game g) {
        this.game = g;

        try {
            SpriteTileParser.loadSprites();
            SpriteTileParser.loadLevels( game );

            heroSprite = SpriteTileParser.getHeroSprite();
            treasureSprite = SpriteTileParser.getTreasureSprite();
            stairsSprite = SpriteTileParser.getStairsSprite();
            zombieSprite = SpriteTileParser.getZombieSprite();
            wildRoseSprite = SpriteTileParser.getWildRoseSprite();
            healTile = SpriteTileParser.getHealTileSprite();
            healOverTimeTile = SpriteTileParser.getOverTimeTileSprite();

            levelBackground = SpriteTileParser.nextLevel();

        } catch (IOException e) {
            System.err.println("Load sprite failed");
            e.printStackTrace();
            System.exit(1);
        }
    }  
    
    @Override
    public void draw(Graphics2D g) {
        GameStatement gameStat = game.getGameStatement();

        drawLabyrinth(g, gameStat);

        drawEventTiles(g,gameStat);

        drawHero(g, gameStat);

        drawMonsters(g,  gameStat);

        drawTimer(g);

        drawHp(g);

        if(game.isFinished()) {
            drawWin(g);
        }
    }

    /**
     * Dessine les pv du hero
     * @param g Image sur laquelle dessinnee
     */
    private void drawHp(Graphics2D g) {
        int hp = game.getLevel().getHeroHp();
        int timeLeft = game.getTimeLeft();

        if (hp > 0 && timeLeft > 0 && !game.isGameWon()) {
            g.setFont(STANDARD_FONT);
            g.setColor(Color.GREEN);

            g.drawString("HP: "+game.getLevel().getHeroHp(),0, Painter.getHeight() - (FONT_SIZE / 4));
        }
    }

    /**
     * Dessine la structure du level
     * @param g objet de dessin
     * @param gameStat
     */
    private void drawLabyrinth(Graphics2D g, GameStatement gameStat){
        //Création fond blanc
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0, Painter.getWidth(), Painter.getHeight());

        //Dessin du sol
        g.drawImage(levelBackground, 0,0, null);
    }

    /**
     * Dessine l'ensemble des monstres du level
     * @param g objet de dessin de l'image
     * @param gameStat
     */
    private void drawMonsters(Graphics2D g, GameStatement gameStat) {

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
     * @param g objet de dessin de l'image
     * @param gameStat
     */
    private void drawHero(Graphics2D g, GameStatement gameStat) {
        //Récupération de sa position
        Position heroPosition = gameStat.getFirstPosition(GameStatement.HERO);

        //Dessin du hero
        g.drawImage(heroSprite, ( heroPosition.getX() + 1 )* WORLD_UNIT , ( heroPosition.getY() + 1) * WORLD_UNIT, null );
    }

    private void drawTimer (Graphics2D g) {
        g.setFont(STANDARD_FONT);
        int timeLeft = game.getTimeLeft();

        g.setColor( ( timeLeft <= TIMER_WARNING_START) ?
                Color.RED :
                Color.WHITE );

        g.drawString("Moves Left : "+timeLeft , 0, Painter.getHeight() - ( FONT_SIZE / 4 * 6));
    }

    private void drawEventTiles(Graphics2D g, GameStatement gameStat) {
        //Dessin escalier ou trésor
        Position p;
        if(gameStat.hasATresure()){
            p = gameStat.getFirstPosition(GameStatement.TREASURE);
            g.drawImage(treasureSprite, ( p.getX() + 1 ) * WORLD_UNIT, ( p.getY() + 1 ) * WORLD_UNIT , null);
        } else {
            p = gameStat.getFirstPosition(GameStatement.STAIRS);
            g.drawImage(stairsSprite, ( p.getX() + 1 ) * WORLD_UNIT, ( p.getY() + 1 ) * WORLD_UNIT , null);
        }

        //Dessin tiles buffer
        for( Position pos : gameStat.getAllPosition(GameStatement.HEAL)) {
            g.drawImage(healTile, ( pos.getX() + 1 ) * WORLD_UNIT, ( pos.getY() + 1 ) * WORLD_UNIT , null);
        }

        for( Position pos : gameStat.getAllPosition(GameStatement.HEALOVERTIME)) {
            g.drawImage(healOverTimeTile, ( pos.getX() + 1 ) * WORLD_UNIT, ( pos.getY() + 1 ) * WORLD_UNIT , null);
        }

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

        g.drawString(endMessage,0 ,Painter.getHeight() - ( FONT_SIZE / 4 ));
    }

    public void updateNextLevel() {
        this.levelBackground = SpriteTileParser.nextLevel();
    }
}
