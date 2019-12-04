package model.element;

import controller.Command;
import model.element.Position;
import model.game.Game;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {

    @BeforeAll
    static void init(){
    }

    @BeforeEach
    void setUp(){
    }

    @AfterAll
    static void end(){
    }

    @AfterEach
    void tearDown(){
    }

    /* - applyCommand - */
    @Test
    void applyCommandUp() {
        Command c = Command.UP;
        Position p = new Position(0,1);
        assertEquals(new Position(0,0),p.applyCommand(c));
    }

    @Test
    void applyCommandUpToWall(){
        Command c = Command.UP;
        Position p = new Position(0,0);
        assertEquals(new Position(0,0),p.applyCommand(c));
    }

    /*Down*/
    @Test
    void applyCommandDown() {
        Command c = Command.DOWN;
        Position p = new Position(0,0);
        assertEquals(new Position(0,1),p.applyCommand(c));
    }

    @Test
    void applyCommandDownToWall(){
        Command c = Command.DOWN;
        Position p = new Position(0,Game.HEIGHT);
        assertEquals(new Position(0,Game.HEIGHT),p.applyCommand(c));
    }

    /*Right*/
    @Test
    void applyCommandRight() {
        Command c = Command.RIGHT;
        Position p = new Position(0,0);
        assertEquals(new Position(1,0),p.applyCommand(c));
    }

    @Test
    void applyCommandRightToWall(){
        Command c = Command.RIGHT;
        Position p = new Position(0,Game.WIDTH);
        assertEquals(new Position(0,Game.WIDTH),p.applyCommand(c));
    }

    /*Left*/
    @Test
    void applyCommandLeft() {
        Command c = Command.LEFT;
        Position p = new Position(1,0);
        assertEquals(new Position(0,0),p.applyCommand(c));
    }

    @Test
    void applyCommandLeftToWall(){
        Command c = Command.LEFT;
        Position p = new Position(0,0);
        assertEquals(new Position(0,0),p.applyCommand(c));
    }

    @Test
    void applyCommandIdle(){
        Position p = new Position(0,0);
        assertEquals(new Position(0,0),p.applyCommand(Command.IDLE));
    }

    @Test
    void applyCommandDefault(){
        Position p = new Position(0,0);
        assertEquals(new Position(0,0),p.applyCommand(Command.ESCAPE));
        assertEquals(new Position(0,0),p.applyCommand(Command.SPACE));
    }


}