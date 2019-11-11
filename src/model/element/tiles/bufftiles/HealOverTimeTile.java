package model.element.tiles.bufftiles;

import model.element.entities.Entity;
import model.element.entities.buffs.HealOverTime;
import model.game.EventManager;

public class HealOverTimeTile extends BuffTile {

    @Override
    public void buff(Entity entity) {
        entity.buff(new HealOverTime(4));
        EventManager.getINSTANCE().destroyTile(this);
    }

}
