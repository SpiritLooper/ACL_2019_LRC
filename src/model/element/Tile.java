package model.element;

/**
 * Abstract class representing a tile, being walkable or not and having an event to fire or not
 */
public abstract class Tile{

    /**
     * is the tile walkable?
     */
    private boolean walkable;

    /**
     * does the tile has an event?
     */
    private boolean hasEvent;

    /**
     * Constructor with the walkable and hasEvent attributes
     * @param walkable is the tile walkable?
     * @param hasEvent does the tile has an event?
     */
    public Tile(boolean walkable, boolean hasEvent){
        this.walkable = walkable;
        this.hasEvent = hasEvent;
    }

    /**
     * @return true if the tile is walkable, false else
     */
    public boolean isWalkable() {
        return walkable;
    }

    /**
     * @return true if the tile has an event, false else
     */
    public boolean hasEvent () {
        return hasEvent;
    }

}
