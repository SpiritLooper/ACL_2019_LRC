package model.element.entities;

import controller.Command;

/**
 * A wild rose never moves, it only acts as a living obstacle
 */
public class ImmovableMonster extends Monster {

    public ImmovableMonster(){
        super(2,2);
    }

    @Override
    public Command behave() {
        return Command.IDLE;
    }

    @Override
    public String toString() {
        return "WildRose<>";
    }

    @Override
    public void attack(Entity e) {
        super.attack(e);
    }

    @Override
    public void hit(int atk) {
        super.hit(atk);
    }

}
