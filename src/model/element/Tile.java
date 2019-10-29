package model.element;

public abstract class Tile implements Entity{
    private boolean walkable;
    private Position position;

    public Tile(Position position, boolean walkable){
        this.position = position;
        this.walkable = walkable;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void move(Position p) {
        this.position = p;
    }

    public boolean isWalkable() {
        return walkable;
    }

}
