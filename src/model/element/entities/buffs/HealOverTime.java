package model.element.entities.buffs;

import model.element.entities.Entity;

public class HealOverTime extends Buff {

    public HealOverTime(int duration) {
        super(duration);
    }

    @Override
    public void apply() {
        entity.heal(2);
        duration--;
    }

}
