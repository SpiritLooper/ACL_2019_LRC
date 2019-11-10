package model.element;

import controller.Command;

import java.util.Random;

/**
 * A Zombie is a monster that moves randomly around
 */
public class Zombie extends Monster {


    public Zombie(){
        atk = 2;
        hp = 1;
    }
    /**
     * Behaves randomly
     * @return random command
     */
    @Override
    public Command behave() {
        Random r = new Random();
        int rand = r.nextInt(5);

        return Command.values()[rand];
    }

    @Override
    public String toString() {
        return "Zombie<>";
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
