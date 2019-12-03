package model.element.entities;

import controller.Command;

import java.util.Random;

/**
 * A BasicMonster is a monster that moves randomly around
 */
public class BasicMonster extends Monster {

    public static int PV_BASE = 2;

    public BasicMonster(){
        super(PV_BASE,1);
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
