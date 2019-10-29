package model.element;

public abstract class EventTile extends Tile{

    public EventTile(Position position, boolean walkable) {
        super(position, walkable);
    }

    public abstract void fireEnvent();
}
