package model.element;

public abstract class Monster implements Entity {

    private Position position;

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
