package model.element.entities;


import model.element.Position;
import model.element.entities.buffs.Buff;

import java.util.ArrayList;

/**
 * The hero is the character you are controlling in the game
 * @author gouth
 */
public class Hero implements Entity{

    /**
     * Position of the hero
     */
    private Position position;

    /**
     * Health points of the hero
     */
    private int hp;

    /**
     * Attack of the hero
     */
    private int atk;

    /**
     * status of the hero
     */
    private Status status;

    /**
     * duration of the status
     */
    private int statusDuration;

    /**
     * current buffs of the hero
     */
    private ArrayList<Buff> buffs;

    /**
     * Constructor with the spawn position
     */
    public Hero(Position position) {
        this.position = position;
        hp = 10;
        atk = 1;
        status = Status.ABLE;
        statusDuration = 0;
        buffs = new ArrayList<>();
    }

    /**
     * @return position of the hero
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the hero position
     * @param position position to set the hero to
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Hero<" + position.getX() + "," + position.getY() + ">";
    }

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

    public void setLife(int l){
        hp = l;
    }

    public Status getStatus () {
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
    public void heal (int amount) {
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
