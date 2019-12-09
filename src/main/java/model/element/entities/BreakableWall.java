package model.element.entities;

import controller.Command;

public class BreakableWall extends Monster {

    /**
     * Constructor of a monster with its hp and atk
     *
     * @param hp  health points
     * @param atk attack values
     */
    public BreakableWall(int hp, int atk) {
        super(3, 0);
    }

    @Override
    public Command behave() {
        return Command.IDLE;
    }

}
