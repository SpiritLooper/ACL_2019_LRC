package model.element;

/**
 * Classe qui modélise un montre
 * @author gouth
 */
public abstract class Monster implements Entity {

    private Position position;

    /**Constructeur
     *
     * @param position position par défaut du monstre
     */
    public Monster(Position position){
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void move(Position p) {
        this.position = p;
    }
}
