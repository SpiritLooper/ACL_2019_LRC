package model.element;

import controller.Command;

import java.util.Random;

/**
 * A Zombie is a monster that moves randomly around
 */
public class Zombie extends Monster {

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

}
