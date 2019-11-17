package main.java.model;

import main.java.model.game.Game;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionPoolTest {

    @Test
    void isInBounds() {
        assertTrue(PositionPool.getInstance().isInBounds(0,0));
        assertTrue(PositionPool.getInstance().isInBounds(Game.WIDTH-1,Game.HEIGHT-1));
        assertTrue(PositionPool.getInstance().isInBounds(0,Game.HEIGHT-1));
        assertTrue(PositionPool.getInstance().isInBounds(Game.WIDTH-1,0));
    }

    @Test
    void isNotInBounds(){
        assertFalse(PositionPool.getInstance().isInBounds(-1,0));
        assertFalse(PositionPool.getInstance().isInBounds(0,-1));
        assertFalse(PositionPool.getInstance().isInBounds(Game.WIDTH,Game.HEIGHT));
        assertFalse(PositionPool.getInstance().isInBounds(Game.WIDTH,0));
        assertFalse(PositionPool.getInstance().isInBounds(0,Game.HEIGHT));
    }
}