package model.element.entities;

import controller.Command;

/**
 * An immovable monster never moves, it only acts as a living obstacle
 */
public class ImmovableMonster extends Monster {

    /**
     * Constructor initializing the immovable monster to 2 hp and 2 atk
     */
    public ImmovableMonster(){
        super(2,2);
    }

    /**
     * Behaves accrdingly to its nature
     * @return a command representing the movement
     */
    @Override
    public Command behave() {
        return Command.IDLE;
    }

}
