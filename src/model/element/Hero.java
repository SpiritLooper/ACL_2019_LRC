package model.element;


/**
 * classe du Hero du jeu
 * @author gouth
 */
public class Hero implements Entity{
    private Position position;

    public Hero(Position p){
        position = p;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean move(Position p) {
        this.getPosition().setPosition(p);
        return true;
    }
}
