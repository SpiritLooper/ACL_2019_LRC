package main.java.model.element.tiles;

import main.java.model.game.EventManager;

/**
 * A Treasure tile is designed to win the game
 */
public class Treasure extends EventTile {

    /**
     * Telling the game to finish and that the player won
     */
    @Override
    public void fireEvent() {
        EventManager.getINSTANCE().winGame();
    }

}
