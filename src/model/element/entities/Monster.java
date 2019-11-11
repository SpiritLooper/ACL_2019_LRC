package model.element.entities;

import controller.Command;
import model.element.entities.buffs.Buff;

import java.util.ArrayList;

/**
 * Abstract class regrouping the monsters having each their own behavior
 * @author gouth
 */
public abstract class Monster implements Entity {

    /**
     * Health points of the monster
     */
    protected int hp;
    /**
     * Attack of the monster
     */
    protected int atk;

    /**
     * status of the hero
     */
    private Status status;

    /**
     * duration of the status
     */
    private int statusDuration;

    /**
     * current buffs of the monster
     */
    private ArrayList<Buff> buffs;

    /**
     * constructeur par defaut d'un monstre qui aura des attributs par defaut
     * hp = 4
     * atk = 1
     */
    public Monster(){
        this.hp = 4;
        this.atk = 1;
        status = Status.ABLE;
        statusDuration = 0;
        buffs = new ArrayList<>();
    }

    public Monster(int hp, int atk){
        this.hp = hp;
        this.atk = atk;
        status = Status.ABLE;
        statusDuration = 0;
        buffs = new ArrayList<>();
    }

    /**
     * Returns the command (movement) that the monster wants to do
     * @return command representing its behavior
     */
    public abstract Command behave();

    @Override
    public void attack(Entity e) {
        hp = hp - e.getAtk();
        e.hit(atk);
    }

    @Override
    public void hit(int atk) {
        hp = hp - atk;
    }

    @Override
    public int getAtk() {
        return atk;
    }

    @Override
    public int getHp() {
        return hp;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public void applyStatus(Status status, int duration) {
        this.status = status;
        statusDuration = duration;
    }

    @Override
    public void buff(Buff buff) {
        buff.setEntity(this);
        buffs.add(buff);
    }

    @Override
    public void heal(int amount) {
        hp += amount;
    }

    @Override
    public void update() {
        if (statusDuration <= 0) {
            status = Status.ABLE;
            statusDuration = 0;
        }

        if (status == Status.FROZEN) {
            statusDuration--;
        }

        //copy of the list to avoid a concurrent modification
        ArrayList<Buff> buffsCopy = new ArrayList<>(buffs);

        for (Buff b : buffsCopy) {
            if (b.getDuration() <= 0) {
                buffs.remove(b);
            } else {
                b.apply();
            }
        }

    }

}
