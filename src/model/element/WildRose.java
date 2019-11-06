package model.element;

import controller.Command;

/**
 * A wild rose never moves, it only acts as a living obstacle
 */
public class WildRose extends Monster {

    @Override
    public Command behave() {
        return Command.IDLE;
    }

    @Override
    public String toString() {
        return "WildRose<>";
    }

}
