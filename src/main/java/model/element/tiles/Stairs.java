package main.java.model.element.tiles;

import main.java.model.game.EventManager;

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
