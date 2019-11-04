package model.element;

import controller.Command;

import java.util.Random;

public class Zombie extends Monster {

    public Zombie() {
        super();
    }

    @Override
    public Command behave() {
        Random r = new Random();
        int rand = r.nextInt(5);

        return Command.values()[rand];
    }

    @Override
    public String toString() {
        return "Zombie>" + super.toString();
    }
}
