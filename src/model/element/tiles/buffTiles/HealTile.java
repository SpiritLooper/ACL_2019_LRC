package model.element.tiles.buffTiles;

import model.element.entities.Entity;
import model.element.entities.buffs.Heal;
import model.game.EventManager;

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
