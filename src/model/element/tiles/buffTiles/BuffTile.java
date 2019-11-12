package model.element.tiles.buffTiles;

import model.element.entities.Entity;
import model.element.tiles.Tile;

/**
 * Tiles containing a buff for the entity walking onto it
 */
public abstract class BuffTile extends Tile {

    /**
     * Constructor creating a walkable, without event buff
     */
    public BuffTile() {
        super(true, false, true);
    }

    /**
     * buffs the entity
     * @param entity entity to buff
     */
    public abstract void buff (Entity entity);

}
