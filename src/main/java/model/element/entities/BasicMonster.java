package model.element.entities;

import controller.Command;

import java.util.Random;

/**
 * A BasicMonster is a monster that moves randomly around
 */
public class BasicMonster extends Monster {

    /**
     * Base health point of the monster
     */
    public static final int BASE_HP = 2;

    /**
     * BAse attack value of the monster
     */
    public static final int BASE_ATK = 1;

    /**
     * Constructor of a basic monster
     */
    public BasicMonster(){
        super(BASE_HP, BASE_ATK);
    }

    /**
     * Behaves randomly
     * @return random command
     */
    @Override
    public Command behave() {
        Random r = new Random();
        int rand = r.nextInt(4);

        return Command.values()[rand];
    }

}
