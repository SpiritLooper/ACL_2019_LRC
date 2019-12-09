package model.element.entities;

import controller.Command;

/**
 * A breakable wall is a wall you can destroy in one hit
 */
public class BreakableWall extends Monster {

    /**
     * Base health points of the wall
     */
    public static final int BASE_HP = 1;

    /**
     * Base attack value of the wall
     */
    public static final int BASE_ATK = 0;

    /**
     * Constructor of a monster with its hp and atk
     */
    public BreakableWall() {
        super(BASE_HP, BASE_ATK);
    }

    @Override
    public Command behave() {
        return Command.IDLE;
    }

}
