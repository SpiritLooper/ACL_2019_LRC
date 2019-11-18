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

    /**
     * @return health points of the hero
     */
    @Override
    public int getHp() {
        return hp;
    }

    /**
     * Sets the health points of the hero
     * @param hp health points
     */
    @Override
    public void setHp(int hp){
        this.hp = hp;
    }

    /**
     * Sets the attack value of the hero
     * @param atk attack value
     */
    @Override
    public void setAtk(int atk) {
        this.atk = atk;
    }

    /**
     * @return current status of the hero
     */
    public Status getStatus () {
        return status;
    }

    /**
     * Applies a status to the hero
     * @param status status to apply
     * @param duration duration of the status
     */
    @Override
    public void applyStatus(Status status, int duration) {
        this.status = status;
        statusDuration = duration;
    }

    /**
     * Buffs the hero
     * @param buff buff used on the entity
     */
    @Override
    public void buff(Buff buff) {
        buff.setEntity(this);
        buffs.add(buff);
    }

    /**
     * Heals the hero
     * @param amount amount to heal
     */
    @Override
    public void heal (int amount) {
        hp += amount;
    }

    /**
     * Updates the status and buffs
     */
    @Override
    public void update() {
        updateStatus();
        updateBuffs();
    }

    /**
     * Updates the status
     */
    private void updateStatus() {
        if (statusDuration <= 0) {
            status = Status.ABLE;
            statusDuration = 0;
        }

        if (status == Status.FROZEN) {
            statusDuration--;
        }
    }

    /**
     * Updates the buffs
     */
    private void updateBuffs () {
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
