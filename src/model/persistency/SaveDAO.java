package model.persistency;

import model.PositionPool;
import model.element.*;
import model.element.entities.Hero;
import model.element.entities.Monster;
import model.element.entities.ImmovableMonster;
import model.element.entities.BasicMonster;

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
     * pointer to the current level
     */
    private int currentLevel;

    /**
     * Constructor to initialize the attributes
     */
    public SaveDAO () {
        timer = 0;
        monsters = new HashMap<>();
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
     */
    public void setHero (int x, int y, int hp, int atk) {
        hero = new Hero(PositionPool.getInstance().getPosition(x, y));
        //hero.setHP(hp);
        //hero.setATK(atk);
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
     */
    public void addMonster (String name, int x, int y, int hp, int atk) {
        Monster m;

        switch (name) {
            case "ZOMBIE":
                m = new BasicMonster();
                break;

            case "WILDROSE":
                m = new ImmovableMonster();
                break;

            default:
                return;
        }

        //m.setHP(hp);
        //m.setATK(atk);
        monsters.put(PositionPool.getInstance().getPosition(x, y), m);
    }

    /**
     * @return the map of the monsters referenced by their keys
     */
    public HashMap<Position, Monster> getMonsters () {
        return monsters;
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
