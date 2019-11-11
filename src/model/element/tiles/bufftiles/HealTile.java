package model.element.tiles.bufftiles;

import model.element.entities.Entity;
import model.element.entities.buffs.Heal;
import model.game.EventManager;

public class HealTile extends BuffTile {

    @Override
    public void buff(Entity entity) {
        System.out.println("WALKED ON HEAL TILE");
        entity.buff(new Heal());
        EventManager.getINSTANCE().destroyTile(this);
    }

}
