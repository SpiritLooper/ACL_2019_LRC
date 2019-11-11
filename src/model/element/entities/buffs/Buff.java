package model.element.entities.buffs;

import model.element.entities.Entity;

public abstract class Buff {

    protected Entity entity;
    protected int duration;

    public Buff (int duration) {
        this.duration = duration;
    }

    public void setEntity (Entity entity) {
        this.entity = entity;
    }

    public int getDuration() {
        return duration;
    }

    public abstract void apply ();

}
