package model.element.entities;

import controller.Command;

public class BreakableWall extends Monster {

    /**
     * Constructor of a monster with its hp and atk
     */
    public BreakableWall() {
        super(1, 0);
    }

    @Override
    public Command behave() {
        return Command.IDLE;
    }

}
