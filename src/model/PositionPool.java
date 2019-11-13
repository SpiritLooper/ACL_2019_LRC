package model;

import model.element.Position;
import model.game.Game;

/**
 * Pool of the positions of the game grid
 */
public class PositionPool {

    /**
     * static instance of the singleton
     */
    private static PositionPool INSTANCE = new PositionPool();

    /**
     * actual pool of position
     */
    private Position[][] pool;

    /**
     * @return static instance of the singleton
     */
    public static PositionPool getInstance() {
        return INSTANCE;
    }

    /**
     * Constructor initializing the pool of positions
     */
    private PositionPool() {
        pool = new Position[Game.WIDTH][Game.HEIGHT];
        for(int i = 0; i < Game.WIDTH; i++){
            for(int j = 0; j < Game.HEIGHT; j++){
                pool[i][j] = new Position(i,j);
            }
        }
    }

    /**
     * Returns the position identified by the x and y coordinates
     * @param x x coordinate
     * @param y y coordinate
     * @return position in the pool
     */
    public Position getPosition(int x, int y){
        return pool[x][y];
    }

    /**
     * Returns whether the x and y coordinates are in bounds of the game or not
     * @param x x coordinate
     * @param y y coordinate
     * @return true if the x and y coordinates are in bounds of the game, false else
     */
    public boolean isInBounds (int x, int y) {
        return x >= 0 && y >= 0 && x < Game.WIDTH && y < Game.WIDTH;
    }

}
