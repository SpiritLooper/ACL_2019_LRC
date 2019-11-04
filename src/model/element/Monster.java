package model.element;

import controller.Command;

/**
 * Abstract class regrouping the monsters having each their own behavior
 * @author gouth
 */
public abstract class Monster implements Entity {

    /**
     * Returns the command (movement) that the monster wants to do
     * @return command representing its behavior
     */
    public abstract Command behave();

    @Override
    public String toString() {
        return "Monster : " + super.toString();
    }
}
