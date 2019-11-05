package model.game;

import controller.Command;
import model.PositionPool;
import model.element.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The Level contains all of its tiles and monsters, its next level (if any) as well as the hero
 * @author gouth
 */
public class Level {

    /**
     * next level (using stairs)
     */
    private Level nextLevel;

    /**
     * Map of the monsters identified by their positions
     */
    private Map<Position, Monster> monsters;

    /**
     * Map of the tiles identified by their positions
     */
    private Map<Position, Tile> tiles;

    /**
     * hero contained in the level
     */
    private Hero hero;

    /**
     * timer of the level
     */
    private Timer timer;

    /**
     * Constructor instantiating the maps and the hero to the default position
     */
    public Level(){
        monsters = new HashMap<>();
        tiles = new HashMap<>();

        hero = new Hero(PositionPool.getInstance().getPosition(0, 0));
        timer = new Timer();
    }

    /**
     * @return hero
     */
    public Hero getHero () {
        return hero;
    }

    /**
     * @return timer of the level
     */
    public Timer getTimer () {
        return timer;
    }

    /**
     * Adds a monster to the level at a given position
     * @param p position to spawn the monster at
     * @param m monster to spawn
     */
    protected void addMonster(Position p, Monster m){
        monsters.put(p, m);
    }

    /**
     * Adds a tile to the level at a given position
     * @param p position to put the tile at
     * @param t tile to put
     */
    protected void addTile(Position p, Tile t) { tiles.put(p, t); }

    /**
     * Removes the monster identified by its position
     * @param p position identifying the monster
     */
    protected void removeMonster(Position p){ monsters.remove(p);}

    /**
     * Removes the tile indentified by its position
     * @param p position identifying the tile
     */
    protected void removeTile(Position p){ tiles.remove(p);}

    /**
     * Generates a default level
     * @return the generated level
     */
    protected Level generateDefaultLevel(){
        /*
          0   1   2
         _____________
      0  |_H_|___|___|
      1  |___|___|_W_|
      2  |___|___|_M_|

         */

        Position p = PositionPool.getInstance().getPosition(2,2);
        addMonster(p, new Zombie());

        //Ajout mur
        p = PositionPool.getInstance().getPosition(2,1);
        addTile(p, new Wall());

        return this;
    }

    /**
     * @return a collection of the monsters' positions
     */
    public Collection<Position> getMonstersPosition() {
        return monsters.keySet();
    }

    /**
     * @return a collection of the tiles' positions
     */
    public Collection<Position> getTilesEventPosition() { //A corriger plus tard, il faudra filtrer les murs et les autres tiles a partir de la hashmap Tiles
        return tiles.keySet();                // c'est juste pour test la vue
    }

    /**
     * @return true if the level contains a treasure, false else
     */
    public boolean hasATreasure() {
        return nextLevel == null;
    }

    /**
     * @return next level
     */
    public Level nextLevel() {
        return nextLevel;
    }

    /**
     * Returns the position of the treasure
     * TODO retirer le instanceof!
     * @return position of the treasure
     */
    public Position getPositionTresor() {
        for (Position p : tiles.keySet()) {
            Tile t = tiles.get(p);
            if(t instanceof Treasure){
                return p;
            }
        }
        return null;
    }

    /**
     * Returns the position of the stairs
     * TODO retirer le instanceof
     * @return
     */
    public Position getPositionStairs() {
        for (Position p : tiles.keySet()) {
            Tile t = tiles.get(p);
            if(t instanceof Stairs){
                return p;
            }
        }
        return null;
    }

    /**
     * Sets the next level
     * @param level next level
     */
    public void setNextLevel(Level level){
        this.nextLevel = level;
    }

    /**
     * Updates the level, firing the event of the hero position if any and updates each monsters using their behaviors
     */
    public void update () {
        //test d'un évènement à déclencher
        if (tiles.containsKey(hero.getPosition()) && tiles.get(hero.getPosition()).hasEvent()) {
            ((EventTile)tiles.get(hero.getPosition())).fireEvent();
        }

        //mise à jour des monstres
        Set<Position> monstersPositions = monsters.keySet(); //on récupère les positions avant changement de la hashmap

        for (Position p : monstersPositions) { //parcours des monstres
            Command monsterCommand = monsters.get(p).behave(); //sauvegarde de la position
            Monster m = monsters.get(p); //sauvegarde du monstre
            Position newPosition = p.applyCommand(monsterCommand); //nouvelle position du monstre
            if (isEmpty(newPosition) && hero.getPosition() != newPosition) {
                monsters.remove(p);
                monsters.put(newPosition, m);
            }
        }

        //mise à jour du timer
        timer.tick();
    }

    /**
     * Moves the hero given a command
     * @param command command representing the move to make
     */
    public void moveHero (Command command) {
        Position newPosition = hero.getPosition().applyCommand(command);
        if (isEmpty(newPosition)) {
            hero.setPosition(newPosition);
        }
    }

    /**
     * Checks if a position is empty and enables someone to walk onto it
     * @param p position to check
     * @return true if someone can walk onto it, false else
     */
    private boolean isEmpty (Position p) {
        return (!tiles.containsKey(p) || (tiles.containsKey(p) && tiles.get(p).isWalkable())) && !monsters.containsKey(p);
    }

}
