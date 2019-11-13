package main.java.model.element.tiles.buffTiles;

import main.java.model.element.entities.Entity;
import main.java.model.element.entities.buffs.HealOverTime;
import main.java.model.game.EventManager;

/**
 * Tile applying a heal over 4 turns to the entity walking onto it
 */
public class HealOverTimeTile extends BuffTile {

    /**
     * Applies a heal over time to the entity
     * @param entity entity to buff
     */
    @Override
    public void buff(Entity entity) {
        entity.buff(new HealOverTime(4));
        EventManager.getINSTANCE().destroyTile(this);
    }

}
