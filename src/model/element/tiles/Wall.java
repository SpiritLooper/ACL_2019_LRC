package model.element.tiles;

/**
 * A wall is a non walkable tile
 */
public class Wall extends Tile {

    /**
     * Constructor for a non walkable tile, without event and without buff
     */
    public Wall () {
        super(false, false, false);
    }

}
