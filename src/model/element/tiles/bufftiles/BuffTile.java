package model.element.tiles.bufftiles;

import model.element.entities.Entity;
import model.element.tiles.Tile;

public abstract class BuffTile extends Tile {

    /**
     * Constructor creating a walkable, without event buff
     */
    public BuffTile() {
        super(true, false, true);
    }

    public abstract void buff (Entity entity);

}
