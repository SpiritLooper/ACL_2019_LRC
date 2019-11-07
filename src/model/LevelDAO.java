package model;

import model.element.*;
import model.game.Game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * WOK
 *
 * FACTORISER LE CODE
 */
public class LevelDAO {

    private Hero hero;
    private HashMap<Position, Monster> monsters;
    private HashMap<Position, Tile> tiles;
    private int width;
    private int height;
    private Game game;

    public LevelDAO(Game game){
        this.game = game;
        monsters = new HashMap<>();
        tiles = new HashMap<>();
        hero = new Hero(PositionPool.getInstance().getPosition(0,0));
    }

    public void setDimension(int w, int h) {
        width = w;
        height = h;
    }

    public void setHeroPosition(int x, int y) {
        Position pos = PositionPool.getInstance().getPosition(x,y);
        hero.setPosition(pos);
    }

    public void addZombie(int x, int y) {
        Position pos = PositionPool.getInstance().getPosition(x,y);
        monsters.put(pos, new Zombie());
    }

    public void addWildrose(int x, int y) {
        Position pos = PositionPool.getInstance().getPosition(x,y);
        monsters.put(pos, new WildRose());
    }

    public void addTreasure(int x, int y) {
        Position pos = PositionPool.getInstance().getPosition(x,y);
        tiles.put(pos, new Treasure(game));
    }

    public void addStairs(int x, int y) {
        Position pos = PositionPool.getInstance().getPosition(x,y);
        tiles.put(pos, new Stairs(game));
    }

    public void addWall(int x, int y) {
        Position pos = PositionPool.getInstance().getPosition(x,y);
        tiles.put(pos, new Wall());
    }

    public Map<Position, Monster> getMonsters() {
        return monsters;
    }

    public Map<Position, Tile> getTiles (){
        return tiles;
    }

    public Hero getHero(){
        return hero;
    }
}
