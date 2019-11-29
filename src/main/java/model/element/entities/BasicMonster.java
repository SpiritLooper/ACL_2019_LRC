package model.element.entities;

import controller.Command;

import java.util.Random;

/**
 * A BasicMonster is a monster that moves randomly around
 */
public class BasicMonster extends Monster {
    
    public BasicMonster(){
        super(2,1);
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

}
