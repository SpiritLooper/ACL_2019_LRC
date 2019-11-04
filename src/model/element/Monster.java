package model.element;

import controller.Command;
import model.PoolPosition;
import model.game.Game;
import model.game.Level;

/**
 * Classe qui modélise un montre
 * @author gouth
 */
public abstract class Monster implements Entity {

    public abstract Command behave();

    @Override
    public String toString() {
        return "Monster : " + super.toString();
    }
}
