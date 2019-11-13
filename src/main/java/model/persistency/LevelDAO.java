package main.java.model.persistency;

import main.java.model.PositionPool;
import main.java.model.element.*;
import main.java.model.element.entities.Hero;
import main.java.model.element.entities.Monster;
import main.java.model.element.entities.ImmovableMonster;
import main.java.model.element.entities.BasicMonster;
import main.java.model.element.tiles.Stairs;
import main.java.model.element.tiles.Tile;
import main.java.model.element.tiles.Treasure;
import main.java.model.element.tiles.Wall;
import main.java.model.element.tiles.buffTiles.HealOverTimeTile;
import main.java.model.element.tiles.buffTiles.HealTile;

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
     * width of the level
     */
    private int width;

    /**
     * height of the level
     */
    private int height;

    /**
     * Constructor initializing the lists
     */
    public LevelDAO(){
        monsters = new HashMap<>();
        tiles = new HashMap<>();
    }

    /**
     * Sets the dimensions of the level
     * @param w width
     * @param h height
     */
    public void setDimension(int w, int h) {
        width = w;
        height = h;
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
     * Adds a zombie to the list of monsters
     * @param x x coordiante
     * @param y y coordinate
     */
    public void addBasicMonster(int x, int y) {
        Position pos = PositionPool.getInstance().getPosition(x,y);
        monsters.put(pos, new BasicMonster());
    }

    /**
     * Adds an immovable monster to the list of monsters
     * @param x x coordinate
     * @param y y coordinate
     */
    public void addImmovableMonster(int x, int y) {
        Position pos = PositionPool.getInstance().getPosition(x,y);
        monsters.put(pos, new ImmovableMonster());
    }

    /**
     * Adds a treasure to the list of tiles
     * @param x x coordinate
     * @param y y coordinate
     */
    public void addTreasure(int x, int y) {
        Position pos = PositionPool.getInstance().getPosition(x,y);
        tiles.put(pos, new Treasure());
    }

    /**
     * Adds stairs to the list of tiles
     * @param x x coordinate
     * @param y y coordinate
     */
    public void addStairs(int x, int y) {
        Position pos = PositionPool.getInstance().getPosition(x,y);
        tiles.put(pos, new Stairs());
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
     * Adds a healing tile to the list of tile
     * @param x x coordinate
     * @param y y coordinate
     */
    public void addHeal (int x, int y) {
        Position pos = PositionPool.getInstance().getPosition(x, y);
        tiles.put(pos, new HealTile());
    }

    /**
     * Adds a heal over time tile to the list of tile
     * @param x x coordinate
     * @param y y coordinate
     */
    public void addHealOverTime (int x, int y) {
        Position pos = PositionPool.getInstance().getPosition(x, y);
        tiles.put(pos, new HealOverTimeTile());
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

}
