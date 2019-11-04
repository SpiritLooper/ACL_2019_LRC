package model;

import model.element.Position;
import model.game.Game;

public class PositionPool {

    private static PositionPool INSTANCE = new PositionPool();

    private Position[][] pool;

    private static final Position lower = new Position(-1,-1); //astuce me demander (Pierre)

    public static PositionPool getInstance() {
        return INSTANCE;
    }

    private PositionPool() {
        pool = new Position[Game.WIDTH][Game.HEIGHT];
        for(int i = 0; i < Game.WIDTH; i++){
            for(int j = 0; j < Game.HEIGHT; j++){
                pool[i][j] = new Position(i,j);
            }
        }
    }

    public Position getPosition(int x, int y){
        return pool[x][y];
    }

    public boolean isInBounds (int x, int y) {
        return x >= 0 && y >= 0 && x <= Game.WIDTH && y <= Game.WIDTH;
    }

}
