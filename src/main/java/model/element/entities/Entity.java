package model.element.entities;

import controller.Command;
import controller.Orientation;
import model.element.entities.buffs.Buff;

import java.util.ArrayList;

/**
 * Interface regrouping the different entities of the game
 */
public interface Entity {

    /**
     * This Entity attack the Entity e
     * @param e entity attacked
     */
    void attack(Entity e);

    /**
     * This Entity takes a hit
     * @param atk attack's force
     */
    void hit(int atk);

    /**
     * Sets the health points
     */
    void setHp (int hp);

    /**
     * @return health points
     */
    int getHp();

    /**
     * Sets the attack value
     */
    void setAtk (int atk);

    /**
     * @return attack oh the Entity
     */
    int getAtk();

    /**
     * Applies a status to the entity
     * @param status status to apply
     * @param duration duration of the status
     */
    void applyStatus (Status status, int duration);

    /**
     * Buffs the entity
     * @param buff buff used on the entity
     */
    void buff (Buff buff);

    /**
     * @return buffs applied on the entity
     */
    ArrayList<Buff> getBuffs ();

    /**
     * Sets the current buffs of the entity
     * @param buffs buffs to add
     */
    void setBuffs (ArrayList<Buff> buffs);

    /**
     * Heals the entity
     * @param amount amount to heal
     */
    void heal (int amount);

    /**
     * Updates the entity (updates its status and buffs)
     */
    void update ();

    /**
     * Rotate the entity
     * @param command command to where to rotate to
     */
    void rotate(Command command);

    /**
     * @return current orientation of the entity
     */
    Orientation getOrientation ();

    /**
     * Sets the orientation of the entity
     * @param orientation orientation to set the entity to
     */
    void setOrientation (Orientation orientation);
}
