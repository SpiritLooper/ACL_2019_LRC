package model.game;

import model.element.tiles.Tile;

public class EventManager {

    /**
     * static instance of the singleton
     */
    private static EventManager INSTANCE = new EventManager();

    /**
     * game to manipulate
     */
    private Game game;

    public static EventManager getINSTANCE () {
        return INSTANCE;
    }

    public void setGame (Game game) {
        this.game = game;
    }

    public void nextLevel () {
        game.nextLevel();
        game.notifyNextLevelEngine();
    }

    public void winGame () {
        game.finish(true);
    }

    public void looseGame () {
        game.finish(false);
    }

    public void destroyTile (Tile tile) {
        game.destroyTile(tile);
    }

}
