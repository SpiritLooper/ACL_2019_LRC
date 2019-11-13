package main.java.model.element.tiles;

import main.java.model.game.Game;

/**
 * An EventTile is a tile with a special event attached to it, fired when the hero walks onto it
 */
public abstract class EventTile extends Tile{

    /**
     * game to fire the event to
     */
    protected Game game;

    /**
     * Constructor for a walkable event tile
     */
    public EventTile() {
        super(true, true, false); //means that this tile is walkable and has an event
    }

    /**
     * Fires an event to the game
     */
    public abstract void fireEvent();

}
