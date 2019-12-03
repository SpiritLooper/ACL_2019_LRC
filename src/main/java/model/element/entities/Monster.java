package model.element.entities;

import controller.Command;
import controller.Orientation;
import model.element.entities.buffs.Buff;
import model.game.EventManager;
import view.AudioPlayer;

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
     * current buffs of the monster
     */
    private ArrayList<Buff> buffs;

    /**
     * current orientation of the monster
     */
    private Orientation orientation;

    /**
     * Constructor of a monster with its hp and atk
     * @param hp health points
     * @param atk attack values
     */
    public Monster(int hp, int atk){
        this.hp = hp;
        this.atk = atk;
        status = Status.STANDING;
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
     * @return x coordinate of the monster
     */
    @Override
    public int getX() {
        return EventManager.getINSTANCE().getXofMonster(this);
    }

    /**
     * @return y coordinate of the monster
     */
    @Override
    public int getY() {
        return EventManager.getINSTANCE().getYofMonster(this);
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
    @Override
    public Status getStatus() {
        return status;
    }

    /**
     * Updates the status of the monster
     * @param status status to apply
     */
    @Override
    public void updateStatus(Status status) {
        this.status = status;
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
     * @return a list of the buffs currently applied to the hero
     */
    @Override
    public ArrayList<Buff> getBuffs () {
        return buffs;
    }

    /**
     * Sets the buffs of the monster
     * @param buffs buffs to add
     */
    @Override
    public void setBuffs (ArrayList<Buff> buffs) {
        this.buffs = buffs;
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
        updateBuffs();
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

    /**
     * Rotates the monster depending of the received command
     * @param command command to where to rotate to
     */
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

    /**
     * @return current orientation of the monster
     */
    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Sets the orientation of the monster
     * @param orientation orientation to set the entity to
     */
    @Override
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
