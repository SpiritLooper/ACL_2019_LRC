package model.element.entities.buffs;

/**
 * A heal highers an entoty health points
 */
public class Heal extends Buff {

    /**
     * Constructor to super with a duration of 1
     */
    public Heal () {
        super(1);
    }

    /**
     * Heals the entity for 5 health points and lower the buff's duration
     */
    @Override
    public void apply() {
        entity.heal(5);
        duration--;
    }

}
