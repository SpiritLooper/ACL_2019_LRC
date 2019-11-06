package model;

import model.element.*;

import java.util.HashMap;

public class SaveDAO {

    private int timer;
    private Hero hero;
    private HashMap<Position, Monster> monsters;
    private int currentLevel;

    public SaveDAO () {
        timer = 0;
        monsters = new HashMap<>();
        currentLevel = 0;
    }

    public void setTimer (int timer) {
        this.timer = timer;
    }

    public int getTimer () {
        return timer;
    }

    public void setHero (int x, int y, int hp, int atk) {
        hero = new Hero(PositionPool.getInstance().getPosition(x, y));
        //hero.setHP(hp);
        //hero.setATK(atk);
    }

    public Hero getHero () {
        return hero;
    }

    public void addMonster (String name, int x, int y, int hp, int atk) {
        Monster m;

        switch (name) {
            case "ZOMBIE":
                m = new Zombie();
                break;

            case "WILDROSE":
                m = new WildRose();
                break;

            default:
                return;
        }

        //m.setHP(hp);
        //m.setATK(atk);
        monsters.put(PositionPool.getInstance().getPosition(x, y), m);
    }

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
