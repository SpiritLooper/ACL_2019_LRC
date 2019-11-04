package model.element;

import model.game.Game;

public abstract class EventTile extends Tile{

    protected Game game;

    public EventTile(Position position, Game game) {
        super(true, true, position);
        this.game = game;
    }

    public abstract void fireEvent();
}
