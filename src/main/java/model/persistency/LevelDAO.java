package model.persistency;

import model.PositionPool;
import model.element.*;
import model.element.entities.Hero;
import model.element.entities.Monster;
import model.element.tiles.Tile;
import model.element.tiles.Wall;
import model.game.Biom;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Used as level definition object for parsing a level file
 */
public class LevelDAO {

    /**
     * hero
     */
    private Hero hero;

    /**
     * list of monsters
     */
    private HashMap<Position, Monster> monsters;

    /**
     * list of tiles
     */
    private HashMap<Position, Tile> tiles;

    /**
     * biom of the level
     */
    private Biom biom;

    /**
     * Constructor initializing the lists
     */
    public LevelDAO(){
        monsters = new HashMap<>();
        tiles = new HashMap<>();
    }

    /**
     * Sets the position of the hero
     * @param x x coordinate
     * @param y y coordinate
     */
    public void setHeroPosition(int x, int y) {
        Position pos = PositionPool.getInstance().getPosition(x,y);
        hero = new Hero(pos);
    }

    /**
     * Generic method to add a monster to the level
     * @param name name of the type of the monster
     * @param x x coordinate
     * @param y y coordinate
     */
    public void addMonster (String name, int x, int y) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Monster m = (Monster) Class.forName(GameParser.ENTITY_PATH + name).getDeclaredConstructor().newInstance();
        Position pos = PositionPool.getInstance().getPosition(x, y);
        monsters.put(pos, m);
    }

    /**
     * Generic method to add a tile to the level
     * @param name name of the type of the tile
     * @param x x coordinate
     * @param y y coordinate
     */
    public void addTile (String name, int x, int y) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Tile t = (Tile) Class.forName(GameParser.TILE_PATH + name).getDeclaredConstructor().newInstance();
        Position pos = PositionPool.getInstance().getPosition(x, y);
        tiles.put(pos, t);
    }

    /**
     * Generic method to add a buff tile to the level
     * @param name name of the type of the buff tile
     * @param x x coordinate
     * @param y y coordinate
     */
    public void addBuffTile (String name, int x, int y) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Tile t = (Tile) Class.forName(GameParser.BUFF_TILE_PATH + name).getDeclaredConstructor().newInstance();
        Position pos = PositionPool.getInstance().getPosition(x, y);
        tiles.put(pos, t);
    }

    /**
     * Adds a wall to the list of tiles
     * @param x x coordinate
     * @param y y coordinate
     */
    public void addWall(int x, int y) {
        Position pos = PositionPool.getInstance().getPosition(x,y);
        tiles.put(pos, new Wall());
    }


    /**
     * @return the map of the monsters referenced by their keys
     */
    public Map<Position, Monster> getMonsters() {
        return monsters;
    }

    /**
     * @return the map of the tiles referenced by their keys
     */
    public Map<Position, Tile> getTiles (){
        return tiles;
    }

    /**
     * @return the hero
     */
    public Hero getHero(){
        return hero;
    }

    /**
     * @return : the biom
     */
    public Biom getBiom() {
        return biom;
    }

    /**
     * setter
     * @param biom ; the biom
     */
    public void setBiom(Biom biom){
        this.biom = biom;
    }
}
