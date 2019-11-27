package model.element.entities;

import controller.Command;
import controller.Orientation;
import model.element.entities.buffs.Buff;

import java.util.ArrayList;

/**
 * Abstract class regrouping the monsters having each their own behavior
 * @author gouth
 */
public abstract class Monster implements Entity {

    /**
     * health points
     */
    protected int hp;
    /**
     * attack value
     */
    protected int atk;

    /**
     * status
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

    private Orientation orientation;

    /**
     * Constructor of a monster with its hp and atk
     * @param hp health points
     * @param atk attack values
     */
    public Monster(int hp, int atk){
        this.hp = hp;
        this.atk = atk;
        status = Status.ABLE;
        statusDuration = 0;
        buffs = new ArrayList<>();
        orientation = Orientation.DOWN;
    }

    /**
     * Returns the command (movement) that the monster wants to do
     * @return command representing its behavior
     */
    public abstract Command behave();

    /**
     * This monster attacks the given entity
     * @param e entity attacked
     */
    @Override
    public void attack(Entity e) {
        //hp = hp - e.getAtk();
        e.hit(atk);
    }

    /**
     * This monster takes a hit
     * @param atk attack's force
     */
    @Override
    public void hit(int atk) {
        hp = hp - atk;
    }

    /**
     * @return health points
     */
    @Override
    public int getHp() {
        return hp;
    }

    /**
     * @return attack value
     */
    @Override
    public int getAtk() {
        return atk;
    }

    /**
     * Sets the health points of the monster
     * @param hp health points
     */
    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Sets the attack value of the monster
     * @param atk attack value
     */
    @Override
    public void setAtk(int atk) {
        this.atk = atk;
    }

    /**
     * @return status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Applies a status to the monster
     * @param status status to apply
     * @param duration duration of the status
     */
    @Override
    public void applyStatus(Status status, int duration) {
        this.status = status;
        statusDuration = duration;
    }

    /**
     * Buffs the monster
     * @param buff buff used on the entity
     */
    @Override
    public void buff(Buff buff) {
        buff.setEntity(this);
        buffs.add(buff);
    }

    /**
     * Heals the monster
     * @param amount amount to heal
     */
    @Override
    public void heal(int amount) {
        hp += amount;
    }

    /**
     * Updates the status and buffs of the monster
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

    @Override
    public void rotate(Command command) {
        switch (command){
            case UP:
                setOrientation(Orientation.UP);
                break;

            case DOWN:
                setOrientation(Orientation.DOWN);
                break;

            case LEFT:
                setOrientation(Orientation.LEFT);
                break;

            case RIGHT:
                setOrientation(Orientation.RIGHT);
                break;

            default:
        }
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}
