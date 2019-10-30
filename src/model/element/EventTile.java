package model.element;

public abstract class EventTile extends Tile{

    public EventTile(Position position) {
        super(position, true);
    }

    public abstract void fireEnvent();
}
