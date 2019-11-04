package model.element;


import controller.Command;
import model.PoolPosition;

/**
 * Classe du Hero du jeu
 * @author gouth
 */
public class Hero implements Entity{

    private Position position;

    /**
     * constructeur par defaut position (0,0)
     */
    public Hero(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
