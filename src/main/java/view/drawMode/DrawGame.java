package view.drawMode;

import controller.Orientation;
import model.PositionPool;
import model.element.Position;
import model.element.entities.BasicMonster;
import model.element.entities.ImmovableMonster;
import model.element.entities.Status;
import model.game.Game;
import model.game.GameStatement;
import view.Engine;
import view.Painter;
import view.spriteManager.SpriteTileParser;
import view.spriteManager.sprite.oriented.Hero;
import view.spriteManager.sprite.oriented.OrientedSprite;
import view.spriteManager.sprite.oriented.Zombie;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static view.Painter.*;

/**
 * Dessine le jeu vu dans le level
 */
public class DrawGame implements DrawMode {

    private static final int TIMER_WARNING_START = 5;

    private Game game;

    private OrientedSprite heroSprite;
    private BufferedImage treasureSprite;
    private BufferedImage stairsSprite;
    private OrientedSprite zombieSprite;
    private BufferedImage wildRoseSprite;
    private BufferedImage healTile;
    private BufferedImage healOverTimeTile;

    private BufferedImage levelBackground ; // Contient le sol et les murs dessinee

    public DrawGame(Game g) {
        this.game = g;

        try {
            SpriteTileParser.loadSprites();
            SpriteTileParser.loadLevels( game );

            heroSprite = new Hero();
            treasureSprite = SpriteTileParser.getTreasureSprite();
            stairsSprite = SpriteTileParser.getStairsSprite();
            zombieSprite = new Zombie();
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
    public void draw(Graphics2D g, int iFrame) {
        GameStatement gameStat = game.getGameStatement();

        drawLabyrinth(g, gameStat);

        drawEventTiles(g,gameStat);

        drawHero(g, gameStat, iFrame);

        drawMonsters(g,  gameStat, iFrame);

        drawTimer(g);

        if(game.isFinished()) {
            drawWin(g);
        }

    }

    /**
     * Dessine les pv d'une entité
     * @param g Image sur laquelle dessinnee
     */
    private void drawHp(Graphics2D g, Position p, double pv , double pvMax ) {

        g.setColor(Color.RED);
        g.fillRect((p.getX() + 1) * WORLD_UNIT ,  (p.getY() + 1) * WORLD_UNIT - 10, WORLD_UNIT, 5);
        g.setColor(Color.GREEN);
        g.fillRect((p.getX() + 1) * WORLD_UNIT ,  (p.getY() + 1) * WORLD_UNIT - 10, ( (int)pv  * WORLD_UNIT) / (int)pvMax, 5);


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
    private void drawMonsters(Graphics2D g, GameStatement gameStat, int iFrame) {

        //Parcours de chaque position de Zombie
        for(Position p : gameStat.getAllPosition(GameStatement.ZOMBIE))  {
            drawEntityOrientedSprite(g,p, iFrame,gameStat.getMonster(p).getOrientation(), zombieSprite,gameStat.getMonster(p).getStatus(), gameStat);
            drawHp(g, p, gameStat.getMonster(p).getHp(), BasicMonster.PV_BASE);
        }

        //Parcours de chaque position de Wild Rose
        for(Position p : gameStat.getAllPosition(GameStatement.WILD_ROSE))  {
            g.drawImage(wildRoseSprite,( p.getX() + 1 ) * WORLD_UNIT, ( p.getY() + 1 ) * WORLD_UNIT , null);
            drawHp(g, p, gameStat.getMonster(p).getHp(), ImmovableMonster.PV_BASE);
        }
    }

    /**
     * Dessine le hero dans son level
     * @param g objet de dessin de l'image
     * @param gameStat
     */
    private void drawHero(Graphics2D g, GameStatement gameStat, int iFrame) {
        //Récupération de sa position
        Position heroPosition = gameStat.getFirstPosition(GameStatement.HERO);

        //Dessin du hero
        drawEntityOrientedSprite(g,heroPosition, iFrame,gameStat.getHeroStatement().getOrientation(), heroSprite,gameStat.getHeroStatement().getStatus(), gameStat);
        drawHp(g, heroPosition, gameStat.getHeroStatement().getHp(), model.element.entities.Hero.PV_BASE);
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

    private void drawEntityOrientedSprite(Graphics2D g, Position p , int frame, Orientation o , OrientedSprite os, Status s, GameStatement gs){
        heroSprite.setOrientation(o);
        heroSprite.setStatus(s);

        boolean haveWallInFace = isAWallInFace(p,o,gs);
        boolean haveMonsterInFace = isAMonsterInFace(p,o,gs) ;

        if(frame >= 0 && !haveWallInFace && ! haveMonsterInFace ){

            switch (o) {
                case UP:
                    g.drawImage(os.getSprite(frame), ( p.getX() + 1 ) * WORLD_UNIT , (( p.getY() + 1 ) * WORLD_UNIT + (int)(((double)(Engine.NB_FRAME_MOVE - frame) / (double)Engine.NB_FRAME_MOVE) * WORLD_UNIT)), null );
                    break;
                case LEFT:
                    g.drawImage(os.getSprite(frame), (( p.getX() + 1 ) * WORLD_UNIT ) + (int)(((double)(Engine.NB_FRAME_MOVE - frame) / (double)Engine.NB_FRAME_MOVE) * WORLD_UNIT), ( p.getY() + 1) * WORLD_UNIT, null );
                    break;
                case RIGHT:
                    g.drawImage(os.getSprite(frame), (( p.getX() + 1 ) * WORLD_UNIT ) - (int)(((double)(Engine.NB_FRAME_MOVE - frame) / (double)Engine.NB_FRAME_MOVE) * WORLD_UNIT), ( p.getY() + 1) * WORLD_UNIT, null );
                    break;
                case DOWN:
                    g.drawImage(os.getSprite(frame), ( p.getX() + 1 ) * WORLD_UNIT , ( (p.getY() + 1 ) * WORLD_UNIT) - (int)(((double)(Engine.NB_FRAME_MOVE - frame) / (double)Engine.NB_FRAME_MOVE) * WORLD_UNIT), null );
                    break;
            }
        } else {
            if(frame >= 0  && !haveWallInFace &&  haveMonsterInFace){
                switch (o) {
                    case UP:
                        g.drawImage(os.getSprite(frame), ( p.getX() + 1 ) * WORLD_UNIT , (( p.getY() + 1 ) * WORLD_UNIT - (int)(((double)(Engine.NB_FRAME_MOVE - frame) / (double)Engine.NB_FRAME_MOVE) * WORLD_UNIT)), null );
                        break;
                    case LEFT:
                        g.drawImage(os.getSprite(frame), (( p.getX() + 1 ) * WORLD_UNIT ) - (int)(((double)(Engine.NB_FRAME_MOVE - frame) / (double)Engine.NB_FRAME_MOVE) * WORLD_UNIT), ( p.getY() + 1) * WORLD_UNIT, null );
                        break;
                    case RIGHT:
                        g.drawImage(os.getSprite(frame), (( p.getX() + 1 ) * WORLD_UNIT ) + (int)(((double)(Engine.NB_FRAME_MOVE - frame) / (double)Engine.NB_FRAME_MOVE) * WORLD_UNIT), ( p.getY() + 1) * WORLD_UNIT, null );
                        break;
                    case DOWN:
                        g.drawImage(os.getSprite(frame), ( p.getX() + 1 ) * WORLD_UNIT , ( (p.getY() + 1 ) * WORLD_UNIT) + (int)(((double)(Engine.NB_FRAME_MOVE - frame) / (double)Engine.NB_FRAME_MOVE) * WORLD_UNIT), null );
                        break;
                }
            } else {
                g.drawImage(os.getSprite(frame), ( p.getX() + 1 ) * WORLD_UNIT , ( (p.getY() + 1 ) * WORLD_UNIT), null );
            }
        }

    }

    private boolean isAWallInFace(Position p, Orientation o, GameStatement gs) {

        if (p.getY() + 1 < Game.HEIGHT && (gs.getAllPosition(GameStatement.WALL).contains(PositionPool.getInstance().getPosition(p.getX(), p.getY() + 1)) && o == Orientation.DOWN) ) {
            return true;
        }

        if  ( p.getY() - 1 >= 0 &&  (  gs.getAllPosition(GameStatement.WALL).contains(PositionPool.getInstance().getPosition(p.getX(), p.getY() - 1)) && o == Orientation.UP )) {
            return true;
        }

        if ( p.getX() - 1 >= 0 ) {
            if(  gs.getAllPosition(GameStatement.WALL).contains(PositionPool.getInstance().getPosition(p.getX() - 1, p.getY())) && o == Orientation.LEFT ) {
               return true;
            }
        }

        if ( p.getX() + 1 < Game.WIDTH && (  gs.getAllPosition(GameStatement.WALL).contains(PositionPool.getInstance().getPosition(p.getX() + 1, p.getY())) && o == Orientation.RIGHT )){
            return true;
        }

        boolean res =  ( p.getX() == 0 && Orientation.LEFT == o )||
                ( p.getX() + 1 == Game.WIDTH && Orientation.RIGHT == o )||
                ( p.getY() == 0 && Orientation.UP == o ) ||
                ( p.getY() + 1 == Game.HEIGHT && Orientation.DOWN == o );

        return res;
    }

    private boolean isAMonsterInFace(Position p, Orientation o, GameStatement gs) {

        if(p.getX() == 0 && o == Orientation.LEFT)
            return false;
        if(p.getY() == 0 && o == Orientation.UP)
            return false;
        if(p.getX() == Game.WIDTH - 1 && Orientation.RIGHT == o)
            return false;
        if(p.getY() == Game.HEIGHT - 1 && Orientation.DOWN == o)
            return false;

        switch (o) {
            case UP:
                return gs.getAllPosition(GameStatement.ZOMBIE).contains(PositionPool.getInstance().getPosition(p.getX(), p.getY() - 1)) ||
                       gs.getAllPosition(GameStatement.WILD_ROSE).contains(PositionPool.getInstance().getPosition(p.getX(), p.getY() - 1)) ||
                        ( gs.getFirstPosition(GameStatement.HERO).getX() == p.getX() && gs.getFirstPosition(GameStatement.HERO).getY() == p.getY() - 1) ;
            case DOWN:
                return gs.getAllPosition(GameStatement.ZOMBIE).contains(PositionPool.getInstance().getPosition(p.getX(), p.getY() + 1)) ||
                        gs.getAllPosition(GameStatement.WILD_ROSE).contains(PositionPool.getInstance().getPosition(p.getX(), p.getY() + 1)) ||
                        ( gs.getFirstPosition(GameStatement.HERO).getX() == p.getX() && gs.getFirstPosition(GameStatement.HERO).getY() == p.getY() + 1) ;
            case LEFT:
                return gs.getAllPosition(GameStatement.ZOMBIE).contains(PositionPool.getInstance().getPosition(p.getX() - 1, p.getY())) ||
                        gs.getAllPosition(GameStatement.WILD_ROSE).contains(PositionPool.getInstance().getPosition(p.getX()- 1, p.getY())) ||
                        ( gs.getFirstPosition(GameStatement.HERO).getX() == p.getX() - 1 && gs.getFirstPosition(GameStatement.HERO).getY() == p.getY()) ;
            case RIGHT:
                return gs.getAllPosition(GameStatement.ZOMBIE).contains(PositionPool.getInstance().getPosition(p.getX() + 1, p.getY())) ||
                        gs.getAllPosition(GameStatement.WILD_ROSE).contains(PositionPool.getInstance().getPosition(p.getX() + 1, p.getY() )) ||
                        ( gs.getFirstPosition(GameStatement.HERO).getX() == p.getX() + 1 && gs.getFirstPosition(GameStatement.HERO).getY() == p.getY()) ;
        }
        return false;
    }

}
