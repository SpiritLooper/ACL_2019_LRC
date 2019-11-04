package model.element;

import model.game.Game;

/**
 * A Treasure tile is designed to win the game
 */
public class Treasure extends EventTile {

    /**
     * Constructor with the game to update
     * @param game game to update
     */
    public Treasure(Game game) {
        super(game);
    }

    /**
     * Telling the game to finish and that the player won
     */
    @Override
    public void fireEvent() {
        game.finish(true);
    }

}
