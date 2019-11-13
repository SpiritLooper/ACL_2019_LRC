package main.java.model.game;

import main.java.model.element.tiles.Tile;

/**
 * The event manager manages the event launched by the event tiles and gives the hero position to the monsters for their behaviors
 */
public class EventManager {

    /**
     * static instance of the singleton
     */
    private static EventManager INSTANCE = new EventManager();

    /**
     * game to manipulate
     */
    private Game game;

    /**
     * @return static instance of the singleton
     */
    public static EventManager getINSTANCE () {
        return INSTANCE;
    }

    /**
     * Sets the game to manipulate
     * @param game game to manipulate
     */
    public void setGame (Game game) {
        this.game = game;
    }

    /**
     * Switches the level to the next one
     */
    public void nextLevel () {
        game.nextLevel();
        game.notifyNextLevelEngine();
    }

    /**
     * Wins the game
     */
    public void winGame () {
        game.finish(true);
    }

    /**
     * Looses the game
     */
    public void looseGame () {
        game.finish(false);
    }

    /**
     * Destroys a tile
     * @param tile tile to destroy
     */
    public void destroyTile (Tile tile) {
        game.destroyTile(tile);
    }

}
