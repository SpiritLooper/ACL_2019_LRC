package model.element.tiles;

import model.game.EventManager;
import model.game.Game;

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
