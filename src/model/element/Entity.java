package model.element;

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
     * @return attack oh the Entity
     */
    int getAtk();

    /**
     * @return health point of the Entity
     */
    int getHp();
}
