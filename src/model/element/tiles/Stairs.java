package model.element.tiles;

import model.game.EventManager;
import model.game.Game;

/**
 * A Stair tile is used to move the hero to the next level
 */
public class Stairs extends EventTile {

    /**
     * Telling the game to go to the next level
     */
    @Override
    public void fireEvent() {
        EventManager.getINSTANCE().nextLevel();
    }
}
