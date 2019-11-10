package model.element;

import controller.Command;

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
     * constructeur par defaut d'un monstre qui aura des attributs par defaut
     * hp = 4
     * atk = 1
     */
    public Monster(){
        this.hp = 4;
        this.atk = 1;
    }

    public Monster(int hp, int atk){
        this.hp = hp;
        this.atk = atk;
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

}
