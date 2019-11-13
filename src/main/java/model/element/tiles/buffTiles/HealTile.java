package main.java.model.element.tiles.buffTiles;

import main.java.model.element.entities.Entity;
import main.java.model.element.entities.buffs.Heal;
import main.java.model.game.EventManager;

/**
 * Tile healing the entity walking onto it
 */
public class HealTile extends BuffTile {

    /**
     * Applies a heal to the entity
     * @param entity entity to buff
     */
    @Override
    public void buff(Entity entity) {
        System.out.println("WALKED ON HEAL TILE");
        entity.buff(new Heal());
        EventManager.getINSTANCE().destroyTile(this);
    }

}
