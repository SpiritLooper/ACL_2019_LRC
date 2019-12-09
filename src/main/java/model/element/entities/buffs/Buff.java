package model.element.entities.buffs;

import model.element.entities.Entity;

/**
 * A buff is applied on an entity to strengthen or weaken it
 */
public abstract class Buff {

    /**
     * entity to apply the buff to
     */
    protected Entity entity;

    /**
     * duration of the buff (in turns)
     */
    protected int duration;

    /**
     * Constructor of a buff with its duration
     * @param duration duration
     */
    public Buff (int duration) {
        this.duration = duration;
    }

    /**
     * Sets the entity
     * @param entity entity
     */
    public void setEntity (Entity entity) {
        this.entity = entity;
    }

    /**
     * @return duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Applies the buff
     */
    public abstract void apply ();

}
