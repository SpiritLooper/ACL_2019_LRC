package model.element.entities.buffs;

/**
 * A heal over time highers the entity health points every turn for a set duration
 */
public class HealOverTime extends Buff {

    /**
     * Constructor to super with a given duration
     * @param duration duration of the heal
     */
    public HealOverTime(Integer duration) {
        super(duration);
    }

    /**
     * Heals the entity for 2 health points every turn for its duration
     */
    @Override
    public void apply() {
        entity.heal(2);
        duration--;
    }

}
