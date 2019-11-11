package model.element.entities.buffs;

public class Heal extends Buff {

    public Heal () {
        super(1);
    }

    @Override
    public void apply() {
        entity.heal(5);
        duration--;
    }

}
