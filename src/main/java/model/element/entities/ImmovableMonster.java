package model.element.entities;

import controller.Command;

/**
 * An immovable monster never moves, it only acts as a living obstacle
 */
public class ImmovableMonster extends Monster {

    /**
     * base health points of the monster
     */
    public static final int BASE_HP = 2;

    /**
     * base attack value of the monster
     */
    public static final int BASE_ATK = 2;

    /**
     * Constructor initializing the immovable monster to 2 hp and 2 atk
     */
    public ImmovableMonster(){
        super(BASE_HP, BASE_ATK);
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
