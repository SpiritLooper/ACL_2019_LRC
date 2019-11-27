package model.persistency;

import model.PositionPool;
import model.element.*;
import model.element.entities.Hero;
import model.element.entities.Monster;
import model.element.entities.buffs.Buff;
import model.element.tiles.buffTiles.BuffTile;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Used as game definition object for parsing a game file
 */
public class SaveDAO {

    /**
     * saved timer
     */
    private int timer;

    /**
     * saved hero
     */
    private Hero hero;

    /**
     * hashmap of the monsters referenced by their positions
     */
    private HashMap<Position, Monster> monsters;

    /**
     * hashmap of the buff tiles referenced by their positions
     */
    private HashMap<Position, BuffTile> buffTiles;

    /**
     * pointer to the current level
     */
    private int currentLevel;

    /**
     * Constructor to initialize the attributes
     */
    public SaveDAO () {
        timer = 0;
        monsters = new HashMap<>();
        buffTiles = new HashMap<>();
        currentLevel = 0;
    }

    /**
     * Sets the timer
     * @param timer timer to save
     */
    public void setTimer (int timer) {
        this.timer = timer;
    }

    /**
     * @return the saved timer
     */
    public int getTimer () {
        return timer;
    }

    /**
     * Sets the hero to a position, with its hp and atk
     * @param x x coordinate
     * @param y y coordinate
     * @param hp health points
     * @param atk attack value
     * @param buffs buffs to apply to the monster
     */
    public void setHero (int x, int y, int hp, int atk, String... buffs) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        hero = new Hero(PositionPool.getInstance().getPosition(x, y));
        hero.setHp(hp);
        hero.setAtk(atk);

        // ajout des buffs au héro
        ArrayList<Buff> heroBuffs = new ArrayList<>();
        if (buffs.length != 0) {
            for (int i = 0; i < buffs.length; i += 2) {
                Buff b = (Buff) Class.forName(GameParser.BUFF_PATH + buffs[i]).getDeclaredConstructor(Integer.class).newInstance(Integer.parseInt(buffs[i+1]));
                b.setEntity(hero);
                heroBuffs.add(b);
            }
        }
        hero.setBuffs(heroBuffs);
    }

    /**
     * @param buffs to apply to the hero
     */
    public void setHeroBuffs (ArrayList<Buff> buffs) {
        hero.setBuffs(buffs);
    }

    /**
     * @return the saved hero
     */
    public Hero getHero () {
        return hero;
    }

    /**
     * Adds a monster to the save
     * @param name name of the monster
     * @param x x coordinate
     * @param y y coordinate
     * @param hp health points
     * @param atk attack value
     * @param buffs buffs to apply to the monster
     */
    public void addMonster (String name, int x, int y, int hp, int atk, String... buffs) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Monster m = (Monster) Class.forName(GameParser.ENTITY_PATH + name).getDeclaredConstructor().newInstance();

        m.setHp(hp);
        m.setAtk(atk);
        monsters.put(PositionPool.getInstance().getPosition(x, y), m);

        // ajout des buffs au monstre
        ArrayList<Buff> monsterBuffs = new ArrayList<>();
        if (buffs.length != 0) {
            for (int i = 0; i <= buffs.length; i += 2) {
                Buff b = (Buff) Class.forName(GameParser.BUFF_PATH + buffs[i]).getDeclaredConstructor(Integer.class).newInstance(Integer.parseInt(buffs[i + 1]));
                b.setEntity(m);
                monsterBuffs.add(b);
            }
        }
        m.setBuffs(monsterBuffs);
    }

    /**
     * @param p position referencing the monster
     * @param buffs buffs to apply to the monster
     */
    public void setMonsterBuffs (Position p, ArrayList<Buff> buffs) {
        monsters.get(p).setBuffs(buffs);
    }

    /**
     * Adds a buff tile to the save
     * @param name name of the buff tile
     * @param x x coordinate
     * @param y y coordinate
     */
    public void addBuffTile (String name, int x, int y) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        BuffTile t = (BuffTile) Class.forName(GameParser.BUFF_TILE_PATH + name).getDeclaredConstructor().newInstance();

        buffTiles.put(PositionPool.getInstance().getPosition(x, y), t);
    }

    /**
     * @return the map of the monsters referenced by their keys
     */
    public HashMap<Position, Monster> getMonsters () {
        return monsters;
    }

    /**
     * @return the map of the buff tiles referenced by their keys
     */
    public HashMap<Position, BuffTile> getBuffTiles() {
        return buffTiles;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(hero);

        for (Position p : monsters.keySet()) {
            sb.append("\n");
            sb.append(p + "->" + monsters.get(p));
        }

        return sb.toString();
    }

}
