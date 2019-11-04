package model.element;


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
     * Constructor with the spawn position
     */
    public Hero(Position position) {
        this.position = position;
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

}
