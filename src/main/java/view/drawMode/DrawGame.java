package view.drawMode;

import controller.Orientation;
import model.PositionPool;
import model.element.Position;
import model.element.entities.BasicMonster;
import model.element.entities.ImmovableMonster;
import model.element.entities.Monster;
import model.element.entities.Status;
import model.game.Game;
import model.game.GameStatement;
import view.Engine;
import view.Painter;
import view.spriteManager.SpriteTileParser;
import view.spriteManager.sprite.BreakableWall;
import view.spriteManager.sprite.oriented.Hero;
import view.spriteManager.sprite.oriented.OrientedSprite;
import view.spriteManager.sprite.oriented.Zombie;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static view.Painter.*;

/**
 * Dessine le jeu vu dans le level
 */
public class DrawGame implements DrawMode {

    private static final int TIMER_WARNING_START = 5;

    private Game game;

    /**
     * Sprite handlers
     */
    private OrientedSprite heroSprite;
    private BufferedImage treasureSprite;
    private BufferedImage stairsSprite;
    private OrientedSprite zombieSprite;
    private BufferedImage wildRoseSprite;
    private BufferedImage healTile;
    private BufferedImage healOverTimeTile;
    private BreakableWall breakableWallSprite;

    /**
     * Level picture background
     */
    private BufferedImage levelBackground ; // Contient le sol et les murs dessinee

    /**
     * entity last position remind
     */
    private Position lastHeroPosition = null;
    private Map<Monster, Position> lastMonsterPositionMap = new HashMap<>();

    public DrawGame(Game g) {
        this.game = g;

        try {

            //Load sprite and level
            SpriteTileParser.loadSprites();
            SpriteTileParser.loadLevels( game );

            heroSprite = new Hero();
            treasureSprite = SpriteTileParser.getTreasureSprite();
            stairsSprite = SpriteTileParser.getStairsSprite();
            zombieSprite = new Zombie();
            wildRoseSprite = SpriteTileParser.getWildRoseSprite();
            healTile = SpriteTileParser.getHealTileSprite();
            healOverTimeTile = SpriteTileParser.getOverTimeTileSprite();
            breakableWallSprite = new BreakableWall(SpriteTileParser.getBiomFromEnum(g.getLevel().getBiomLevel()));

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

        //Création d'un fond
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0, Painter.getWidth(), Painter.getHeight());

        drawATH(g, gameStat);

        drawLabyrinth(g, gameStat);

        drawEventTiles(g,gameStat);

        drawHero(g, gameStat, iFrame);

        drawMonsters(g,  gameStat, iFrame);

        if(game.isFinished()) {
            drawWin(g);
        }

    }

    private void drawATH(Graphics2D g, GameStatement gameStat) {
        drawTimer(g);
    }

    /**
     * Draw hp entity
     * @param g draw tool
     * @param pv actual hp entity
     * @param pvMax max hp entity
     */
    private void drawHp(Graphics2D g, Position p, double pv , double pvMax ) {

        g.setColor(Color.RED);
        g.fillRect((p.getX() + 1) * WORLD_UNIT + 1,  HEIGHT_ATH + (p.getY() + 1) * WORLD_UNIT - 10, WORLD_UNIT - 2, 5);
        if(pv <= pvMax) {
            g.setColor(Color.GREEN);
            g.fillRect((p.getX() + 1) * WORLD_UNIT + 1 , HEIGHT_ATH + (p.getY() + 1) * WORLD_UNIT - 10, ( (int)pv  * WORLD_UNIT) / (int)pvMax - 2, 5);
        } else {
            g.setColor(Color.CYAN);
            g.fillRect((p.getX() + 1) * WORLD_UNIT  + 1,  HEIGHT_ATH +(p.getY() + 1) * WORLD_UNIT - 10, ( WORLD_UNIT ) - 2 , 5);

        }

    }

    /**
     * draw level background
     * @param g draw tool
     * @param gameStat
     */
    private void drawLabyrinth(Graphics2D g, GameStatement gameStat){
        //Dessin du sol
        g.drawImage(levelBackground, 0,HEIGHT_ATH , null);
    }

    /**
     * Dessine l'ensemble des monstres du level
     * @param g objet de dessin de l'image
     * @param gameStat
     * @param iFrame frame number, -1 if out of animation
     */
    private void drawMonsters(Graphics2D g, GameStatement gameStat, int iFrame) {

        //Parcours de chaque position de Zombie
        for(Position p : gameStat.getAllPosition(GameStatement.ZOMBIE))  {
            //On regarde si on connait l'ancienne position du monstre
           if(lastMonsterPositionMap.containsKey(gameStat.getMonster(p))){
               boolean haveAMonsterInFace = false;
               for(Position monsterPosition : lastMonsterPositionMap.values()){ // On regarde s'il y a un monstre en face de lui
                   haveAMonsterInFace = haveAMonsterInFace || isAMonsterInFace(lastMonsterPositionMap.get(gameStat.getMonster(p)),gameStat.getMonster(p).getOrientation(),monsterPosition, gameStat);
               }

               if(haveAMonsterInFace) {
                   drawEntityOrientedSprite(g,p, -1,gameStat.getMonster(p).getOrientation(), zombieSprite,gameStat.getMonster(p).getStatus(), gameStat);
               } else {
                   //On regarde s'il a un mur en face
                   boolean haveWallInFace = isAWallInFace(lastMonsterPositionMap.get(gameStat.getMonster(p)),gameStat.getMonster(p).getOrientation(),gameStat);
                   if( !haveWallInFace ) { // On dessine l'animation
                       drawMoveEntityOrientedSprite(g,p, iFrame,gameStat.getMonster(p).getOrientation(), zombieSprite,gameStat.getMonster(p).getStatus());
                   } else { // On dessine le sprite initial sinon
                       drawEntityOrientedSprite(g,p, iFrame,gameStat.getMonster(p).getOrientation(), zombieSprite,gameStat.getMonster(p).getStatus(), gameStat);
                   }
               }
           } else { // On dessine l'etat initial peu importe
               drawEntityOrientedSprite(g,p, iFrame,gameStat.getMonster(p).getOrientation(), zombieSprite,gameStat.getMonster(p).getStatus(), gameStat);
           }
            drawHp(g, p, gameStat.getMonster(p).getHp(), BasicMonster.PV_BASE);

            if(iFrame == -1) // Enregistre la position du monstre
                lastMonsterPositionMap.put( gameStat.getMonster(p), p);
        }

        //Parcours de chaque position de Wild Rose
        for(Position p : gameStat.getAllPosition(GameStatement.WILD_ROSE))  {
            g.drawImage(wildRoseSprite,( p.getX() + 1 ) * WORLD_UNIT, HEIGHT_ATH + ( p.getY() + 1 ) * WORLD_UNIT , null);
            drawHp(g, p, gameStat.getMonster(p).getHp(), ImmovableMonster.PV_BASE);
            if(iFrame == -1){
                lastMonsterPositionMap.put( gameStat.getMonster(p), p);
            }
        }

        //Parcours des murs cassable
        for (Position p : gameStat.getAllPosition(GameStatement.BREAKABLE_WALL)) {
            g.drawImage(breakableWallSprite.getSprite(),( p.getX() + 1 ) * WORLD_UNIT, HEIGHT_ATH + ( p.getY() + 1 ) * WORLD_UNIT , null);
            //drawHp(g, p, gameStat.getMonster(p).getHp(), ImmovableMonster.PV_BASE);
            if(iFrame == -1){
                lastMonsterPositionMap.put( gameStat.getMonster(p), p);
            }
        }

    }

    /**
     * Dessine le hero dans son level
     * @param g objet de dessin de l'image
     * @param gameStat
     */
    private void drawHero(Graphics2D g, GameStatement gameStat, int iFrame) {
        //Récupération de sa position
        Position heroPosition;

        heroPosition = gameStat.getFirstPosition(GameStatement.HERO);

        //Dessin du hero
        if(lastHeroPosition != null) { // On regarde si son ancienne position est stockée
            boolean haveWallInFace = isAWallInFace(lastHeroPosition,gameStat.getHeroStatement().getOrientation(),gameStat);
            boolean haveAMonsterInFace = false;
            for(Position monsterPosition : lastMonsterPositionMap.values()){ // On regarde s'il y a un monstre en face de lui
                haveAMonsterInFace = haveAMonsterInFace || isAMonsterInFace(lastHeroPosition,gameStat.getHeroStatement().getOrientation(),monsterPosition, gameStat);
            }
            if(haveAMonsterInFace) { // Si oui on applique l'animation d'attack
                drawAttackMoveEntityOrientedSprite(g,heroPosition, iFrame,gameStat.getHeroStatement().getOrientation(), heroSprite,gameStat.getHeroStatement().getStatus());
            } else if(!haveWallInFace ) { //Sinon s'il y a rien devant lui on applique l'animation de déplacement
                drawMoveEntityOrientedSprite(g,heroPosition, iFrame,gameStat.getHeroStatement().getOrientation(), heroSprite,gameStat.getHeroStatement().getStatus());
            } else { // Sinon on dessine le sprite de base
                drawEntityOrientedSprite(g,heroPosition, iFrame,gameStat.getHeroStatement().getOrientation(), heroSprite,gameStat.getHeroStatement().getStatus(), gameStat);
            }
            drawHp(g, heroPosition, gameStat.getHeroStatement().getHp(), model.element.entities.Hero.PV_BASE);
        }

        if(iFrame == -1) // On stock l'ancienne position du hero
            lastHeroPosition = heroPosition;
    }

    /**
     * draw move left timer
     * @param g draw tool
     */
    private void drawTimer (Graphics2D g) {
        g.setFont(STANDARD_FONT);
        int timeLeft = game.getTimeLeft();

        g.setColor( ( timeLeft <= TIMER_WARNING_START) ?
                Color.RED :
                Color.WHITE );

        g.drawString("Moves Left : "+timeLeft , 0, HEIGHT_ATH / 2 + ( FONT_SIZE / 2) );
    }

    /**
     * draw buff and debuff tiles
     * @param g draw tool
     * @param gameStat statment of model
     */
    private void drawEventTiles(Graphics2D g, GameStatement gameStat) {
        //Dessin escalier ou trésor
        Position p;
        if(gameStat.hasATresure()){
            p = gameStat.getFirstPosition(GameStatement.TREASURE);
            g.drawImage(treasureSprite, ( p.getX() + 1 ) * WORLD_UNIT, HEIGHT_ATH + ( p.getY() + 1 ) * WORLD_UNIT , null);
        } else {
            p = gameStat.getFirstPosition(GameStatement.STAIRS);
            g.drawImage(stairsSprite, ( p.getX() + 1 ) * WORLD_UNIT, HEIGHT_ATH + ( p.getY() + 1 ) * WORLD_UNIT , null);
        }

        //Dessin tiles buffer
        for( Position pos : gameStat.getAllPosition(GameStatement.HEAL)) {
            g.drawImage(healTile, ( pos.getX() + 1 ) * WORLD_UNIT, HEIGHT_ATH + ( pos.getY() + 1 ) * WORLD_UNIT , null);
        }

        for( Position pos : gameStat.getAllPosition(GameStatement.HEALOVERTIME)) {
            g.drawImage(healOverTimeTile, ( pos.getX() + 1 ) * WORLD_UNIT, HEIGHT_ATH + ( pos.getY() + 1 ) * WORLD_UNIT , null);
        }

    }

    /**
     * Draw the win or loose game
     * @param g draw tool
     */
    private void drawWin(Graphics2D g) {
        g.setFont(STANDARD_FONT);
        g.setColor(Color.LIGHT_GRAY);

        String endMessage;
        if (game.isGameWon()) {
            endMessage = "You win !";
        } else {
            endMessage = "You Loose !";
        }

        g.drawString(endMessage,0 ,HEIGHT_ATH + Painter.getHeight() - ( FONT_SIZE / 4 ));
    }

    /**
     * update the background image
     */
    public void updateNextLevel() {
        this.levelBackground = SpriteTileParser.nextLevel();
    }

    /**
     * Draw the great image of an oriented sprite
     * @param g draw tool
     * @param newPosition new position of entity
     * @param frame number frame (-1) if out of animation
     * @param o orientation of entity
     * @param os Sprite Design
     * @param s status of entity
     */
    private void drawMoveEntityOrientedSprite(Graphics2D g, Position newPosition , int frame, Orientation o , OrientedSprite os, Status s) {
        os.setOrientation(o);
        os.setStatus(s);
        if(frame >= 0) { // Si on est dans une animation on dessine le sprite animé
            switch (o) {
                case UP:
                    g.drawImage(os.getSprite(frame), ( newPosition.getX() + 1 ) * WORLD_UNIT ,HEIGHT_ATH + (( newPosition.getY() + 1 ) * WORLD_UNIT + (int)(((double)(Engine.NB_FRAME_MOVE - frame) / (double)Engine.NB_FRAME_MOVE) * WORLD_UNIT)), null );
                    break;
                case LEFT:
                    g.drawImage(os.getSprite(frame), (( newPosition.getX() + 1 ) * WORLD_UNIT ) + (int)(((double)(Engine.NB_FRAME_MOVE - frame) / (double)Engine.NB_FRAME_MOVE) * WORLD_UNIT), HEIGHT_ATH + ( newPosition.getY() + 1) * WORLD_UNIT, null );
                    break;
                case RIGHT:
                    g.drawImage(os.getSprite(frame), (( newPosition.getX() + 1 ) * WORLD_UNIT ) - (int)(((double)(Engine.NB_FRAME_MOVE - frame) / (double)Engine.NB_FRAME_MOVE) * WORLD_UNIT), HEIGHT_ATH + ( newPosition.getY() + 1) * WORLD_UNIT, null );
                    break;
                case DOWN:
                    g.drawImage(os.getSprite(frame), ( newPosition.getX() + 1 ) * WORLD_UNIT , HEIGHT_ATH + ( (newPosition.getY() + 1 ) * WORLD_UNIT) - (int)(((double)(Engine.NB_FRAME_MOVE - frame) / (double)Engine.NB_FRAME_MOVE) * WORLD_UNIT), null );
                    break;
            }
        } else { // Sinon on dessine le sprite par defaut
            g.drawImage(os.getSprite(frame), ( newPosition.getX() + 1 ) * WORLD_UNIT ,HEIGHT_ATH + ( (newPosition.getY() + 1 ) * WORLD_UNIT), null );
        }
    }

    /**
     * Draw the great image of an attack move
     * @param g draw tool
     * @param newPosition new position of entity
     * @param frame number frame (-1) if out of animation
     * @param o orientation of entity
     * @param os Sprite Design
     * @param s status of entity
     */
    private void drawAttackMoveEntityOrientedSprite(Graphics2D g, Position newPosition , int frame, Orientation o , OrientedSprite os, Status s) {
        os.setOrientation(o);
        os.setStatus(s);
        if(frame >= 0) { // Si pon est en animation on dessine le sprite anime
            switch (o) {
                case UP:
                    g.drawImage(os.getSprite(frame), ( newPosition.getX() + 1 ) * WORLD_UNIT , HEIGHT_ATH +(( newPosition.getY() + 1 ) * WORLD_UNIT - (int)(((double)(Engine.NB_FRAME_MOVE - frame) / (double)Engine.NB_FRAME_MOVE) * WORLD_UNIT)), null );
                    break;
                case LEFT:
                    g.drawImage(os.getSprite(frame), (( newPosition.getX() + 1 ) * WORLD_UNIT ) - (int)(((double)(Engine.NB_FRAME_MOVE - frame) / (double)Engine.NB_FRAME_MOVE) * WORLD_UNIT), HEIGHT_ATH + ( newPosition.getY() + 1) * WORLD_UNIT, null );
                    break;
                case RIGHT:
                    g.drawImage(os.getSprite(frame), (( newPosition.getX() + 1 ) * WORLD_UNIT ) + (int)(((double)(Engine.NB_FRAME_MOVE - frame) / (double)Engine.NB_FRAME_MOVE) * WORLD_UNIT), HEIGHT_ATH + ( newPosition.getY() + 1) * WORLD_UNIT, null );
                    break;
                case DOWN:
                    g.drawImage(os.getSprite(frame), ( newPosition.getX() + 1 ) * WORLD_UNIT , HEIGHT_ATH + ( (newPosition.getY() + 1 ) * WORLD_UNIT) + (int)(((double)(Engine.NB_FRAME_MOVE - frame) / (double)Engine.NB_FRAME_MOVE) * WORLD_UNIT), null );
                    break;
            }
        } else { // l'image par defaut sinon
            g.drawImage(os.getSprite(frame), ( newPosition.getX() + 1 ) * WORLD_UNIT ,HEIGHT_ATH + ( (newPosition.getY() + 1 ) * WORLD_UNIT), null );
        }
    }



    /**
     * Draw the great image of an oriented sprite
     * @param g draw tool
     * @param p new position of entity
     * @param frame number frame (-1) if out of animation
     * @param o orientation of entity
     * @param os Sprite Design
     * @param s status of entity
     * @param gs game statment to know all position of game entity
     */
    private void drawEntityOrientedSprite(Graphics2D g, Position p , int frame, Orientation o , OrientedSprite os, Status s, GameStatement gs){
        os.setOrientation(o);
        os.setStatus(s);

        boolean haveWallInFace = isAWallInFace(p,o,gs);

        if(frame >= 0 && !haveWallInFace ){ // Dessine l'animation

            switch (o) {
                case UP:
                    g.drawImage(os.getSprite(frame), ( p.getX() + 1 ) * WORLD_UNIT , HEIGHT_ATH + (( p.getY() + 1 ) * WORLD_UNIT + (int)(((double)(Engine.NB_FRAME_MOVE - frame) / (double)Engine.NB_FRAME_MOVE) * WORLD_UNIT)), null );
                    break;
                case LEFT:
                    g.drawImage(os.getSprite(frame), (( p.getX() + 1 ) * WORLD_UNIT ) + (int)(((double)(Engine.NB_FRAME_MOVE - frame) / (double)Engine.NB_FRAME_MOVE) * WORLD_UNIT), HEIGHT_ATH + ( p.getY() + 1) * WORLD_UNIT, null );
                    break;
                case RIGHT:
                    g.drawImage(os.getSprite(frame), (( p.getX() + 1 ) * WORLD_UNIT ) - (int)(((double)(Engine.NB_FRAME_MOVE - frame) / (double)Engine.NB_FRAME_MOVE) * WORLD_UNIT), HEIGHT_ATH + ( p.getY() + 1) * WORLD_UNIT, null );
                    break;
                case DOWN:
                    g.drawImage(os.getSprite(frame), ( p.getX() + 1 ) * WORLD_UNIT , HEIGHT_ATH + ( (p.getY() + 1 ) * WORLD_UNIT) - (int)(((double)(Engine.NB_FRAME_MOVE - frame) / (double)Engine.NB_FRAME_MOVE) * WORLD_UNIT), null );
                    break;
            }
        } else { // Image de bvase sinon
          g.drawImage(os.getSprite(frame), ( p.getX() + 1 ) * WORLD_UNIT ,HEIGHT_ATH + ( (p.getY() + 1 ) * WORLD_UNIT), null );
        }

    }

    /**
     * Know if a wall is behind an entity
     * @param p position of entity
     * @param o orientation of entity
     * @param gs game statment to know all position of game entity
     * @return true if a wall is behind
     */
    private boolean isAWallInFace(Position p, Orientation o, GameStatement gs) {

        //Test Bas Hero
        if (p.getY() + 1 < Game.HEIGHT && (gs.getAllPosition(GameStatement.WALL).contains(PositionPool.getInstance().getPosition(p.getX(), p.getY() + 1)) && o == Orientation.DOWN) ) {
            return true;
        }

        //Test Haut Hero
        if  ( p.getY() - 1 >= 0 &&  (  gs.getAllPosition(GameStatement.WALL).contains(PositionPool.getInstance().getPosition(p.getX(), p.getY() - 1)) && o == Orientation.UP )) {
            return true;
        }

        //Test Gauche Hero
        if ( p.getX() - 1 >= 0 ) {
            if(  gs.getAllPosition(GameStatement.WALL).contains(PositionPool.getInstance().getPosition(p.getX() - 1, p.getY())) && o == Orientation.LEFT ) {
               return true;
            }
        }

        //Test Droite Hero
        if ( p.getX() + 1 < Game.WIDTH && (  gs.getAllPosition(GameStatement.WALL).contains(PositionPool.getInstance().getPosition(p.getX() + 1, p.getY())) && o == Orientation.RIGHT )){
            return true;
        }

        // Test si on est en bordure
        boolean res =  ( p.getX() == 0 && Orientation.LEFT == o )||
                ( p.getX() + 1 == Game.WIDTH && Orientation.RIGHT == o )||
                ( p.getY() == 0 && Orientation.UP == o ) ||
                ( p.getY() + 1 == Game.HEIGHT && Orientation.DOWN == o );

        return res;
    }

    /**
     * Know if a monster is behind an entity
     * @param p position of entity
     * @param o orientation of entity
     * @param gs game statment to know all position of game entity
     * @return true if a monster is behind
     */
    private boolean isAMonsterInFace(Position p, Orientation o, Position positionMonster, GameStatement gs) {

        //Test si on regarde une bordure
        if(p.getX() == 0 && o == Orientation.LEFT)
            return false;
        if(p.getY() == 0 && o == Orientation.UP)
            return false;
        if(p.getX() == Game.WIDTH - 1 && Orientation.RIGHT == o)
            return false;
        if(p.getY() == Game.HEIGHT - 1 && Orientation.DOWN == o)
            return false;

        // Test monstre a cote du hero
        switch (o) {
            case UP:
                return positionMonster.getX() == p.getX() && positionMonster.getY() == p.getY() - 1;
            case DOWN:
                return positionMonster.getX() == p.getX() && positionMonster.getY() == p.getY() + 1;
            case LEFT:
                return positionMonster.getX() == p.getX() - 1 && positionMonster.getY() == p.getY();
            case RIGHT:
                return positionMonster.getX() == p.getX() + 1 && positionMonster.getY() == p.getY();
        }
        return false;
    }

    /**
     * clean monster position
     */
    public void cleanCachePositionStock() {
        lastMonsterPositionMap.clear();
    }

    public void updateBreakableWall() {
        this.breakableWallSprite.setBiom(SpriteTileParser.getBiomFromEnum(this.game.getLevel().getBiomLevel()));
    }
}
