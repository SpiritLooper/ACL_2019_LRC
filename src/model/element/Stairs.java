package model.element;

import model.game.Game;

/**
 * A Stair tile is used to move the hero to the next level
 */
public class Stairs extends EventTile {

    /**
     * Constructor with the game to update
     * @param game game to update
     */
    public Stairs(Game game) {
        super(game);
    }

    /**
     * Telling the game to go to the next level
     */
    @Override
    public void fireEvent() {
        game.nextLevel();
        game.notifyNextLevelEngine();
    }
}
