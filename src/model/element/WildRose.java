package model.element;

import controller.Command;

/**
 * A wild rose never moves, it only acts as a living obstacle
 */
public class WildRose extends Monster {

    public WildRose(){
        atk = 2;
        hp = 2;
    }

    @Override
    public Command behave() {
        return Command.IDLE;
    }

    @Override
    public String toString() {
        return "WildRose<>";
    }

    @Override
    public void attack(Entity e) {
        hp = hp - e.getAtk();
        e.hit(atk);
    }

    @Override
    public void hit(int atk) {
        hp = hp - atk;
    }

    @Override
    public int getAtk() {
        return atk;
    }

    @Override
    public int getHp() {
        return hp;
    }
}
