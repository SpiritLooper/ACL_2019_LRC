package model.element.entities;


import controller.Command;
import controller.Orientation;
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
     * current orientation of the hero
     */
    private Orientation orientation;

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
        orientation = Orientation.UP;
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

    /**
     * Attacks the given entity
     * @param e entity attacked
     */
    @Override
    public void attack(Entity e) {
        //si modif des points d'attaque ICI
        e.hit(atk);
    }

    /**
     * Get hit for a given attack value
     * @param atk attack's force
     */
    @Override
    public void hit(int atk) {
        //si modif de la défence ICI
        hp = hp - atk;
    }

    /**
     * @return attack value of the hero
     */
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
     * @return a list of the buffs currently applied to the hero
     */
    @Override
    public ArrayList<Buff> getBuffs () {
        return buffs;
    }

    /**
     * Sets the buffs of the hero
     * @param buffs buffs to add
     */
    @Override
    public void setBuffs (ArrayList<Buff> buffs) {
        this.buffs = buffs;
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
     * Rotates the hero depending on the command received
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
        System.out.println("I'm hero" + this.getClass() + "and i'm rotating to the " + getOrientation().name());
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

    /**
     * @return current orientation of the hero
     */
    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Sets the orientation of the hero
     * @param orientation orientation to set the hero to
     */
    @Override
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

}
