package model.element;

import model.game.Game;

/**
 * An EventTile is a tile with a special event attached to it, fired when the hero walks onto it
 */
public abstract class EventTile extends Tile{

    /**
     * Game to fire the event to
     */
    protected Game game;

    /**
     * Constructor with the position of the tile and it's controlled game
     * @param game game to fire the event to
     */
    public EventTile(Game game) {
        super(true, true); //means that this tile is walkable and has an event
        this.game = game;
    }

    /**
     * Fires an event to the game
     */
    public abstract void fireEvent();

}
