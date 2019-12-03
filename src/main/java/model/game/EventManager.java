package model.game;

import model.element.entities.Entity;
import model.element.entities.Monster;
import model.element.tiles.Tile;

import java.lang.reflect.InvocationTargetException;

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
    public void nextLevel () throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
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

    /**
     * Returns the x coordinate of an entity
     * @param monster entity to look for
     * @return x coordinate
     */
    public int getXofMonster (Monster monster) {
        return game.getXofMonster(monster);
    }

    /**
     * Returns the y coordinate of an entity
     * @param monster entity to look for
     * @return y coordinate
     */
    public int getYofMonster (Monster monster) {
        return game.getYofEntity(monster);
    }

}
