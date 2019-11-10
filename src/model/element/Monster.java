package model.element;

import controller.Command;

/**
 * Abstract class regrouping the monsters having each their own behavior
 * @author gouth
 */
public abstract class Monster implements Entity {

    /**
     * Health points of the hero
     */
    protected int hp;

    /**
     * Attack of the hero
     */
    protected int atk;

    /**
     * Returns the command (movement) that the monster wants to do
     * @return command representing its behavior
     */
    public abstract Command behave();

}
