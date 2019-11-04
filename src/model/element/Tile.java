package model.element;

public abstract class Tile{

    private boolean walkable;
    private boolean hasEvent;
    private Position position;

    public Tile(boolean walkable, boolean hasEvent, Position position){
        this.walkable = walkable;
        this.hasEvent = hasEvent;
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void move(Position p) {
        this.position = p;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public boolean hasEvent () {
        return hasEvent;
    }

}
