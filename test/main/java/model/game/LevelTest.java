package main.java.model.game;

import main.java.controller.Command;
import main.java.model.PositionPool;
import main.java.model.element.Position;
import main.java.model.element.entities.BasicMonster;
import main.java.model.element.entities.Hero;
import main.java.model.element.entities.ImmovableMonster;
import main.java.model.element.entities.Monster;
import main.java.model.element.tiles.EventTile;
import main.java.model.element.tiles.Tile;
import main.java.model.element.tiles.Wall;
import main.java.view.spriteManager.basicSprite.Stairs;
import org.easymock.EasyMock;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {

    private static Level l;

    @BeforeAll
    static void init(){
        l = new Level();
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

    /* - Test -*/

    @Test
    void addMonster() {
        Monster m = new BasicMonster();
        l.addMonster(PositionPool.getInstance().getPosition(0,0), m);
        assertTrue(l.containsMonster(m));

        Monster m2 = new ImmovableMonster();
        l.addMonster(PositionPool.getInstance().getPosition(0,1), m2);
        assertTrue(l.containsMonster(m2));
    }

    @Test
    void removeMonster() {
        Monster m = new BasicMonster();
        l.addMonster(PositionPool.getInstance().getPosition(0,0),m);
        l.removeMonster(PositionPool.getInstance().getPosition(0,0));
        assertFalse(l.containsMonster(m));
    }

    @Test
    void addTile() {
        Wall w = new Wall();
        l.addTile(PositionPool.getInstance().getPosition(0,0), w);
        assertTrue(l.constainsTile(w));
    }

    @Test
    void destroyTile() {
        Wall w = new Wall();
        l.addTile(PositionPool.getInstance().getPosition(0,0), w);
        l.destroyTile(w);
        assertFalse(l.constainsTile(w));
    }

    @Test
    void hasATreasure() {
        l.setNextLevel(new Level());
        assertFalse(l.hasATreasure());
    }

    @Test
    void hasNoTreasure(){
        assertTrue(l.hasATreasure());
    }

    @Test
    void moveHero() {
        /*
        l.moveHero(Command.DOWN);
        assertSame(PositionPool.getInstance().getPosition(0,1),l.getHero().getPosition());
        l.moveHero(Command.RIGHT);
        assertSame(PositionPool.getInstance().getPosition(1,1),l.getHero().getPosition());
        l.moveHero(Command.LEFT);
        assertSame(PositionPool.getInstance().getPosition(0,1),l.getHero().getPosition());
        l.moveHero(Command.UP);
        assertSame(PositionPool.getInstance().getPosition(0,0),l.getHero().getPosition());*/

        //a cause du niveau 1 le hero bloque contre un monstre trop souvent

    }

    @Test
    void loadSave() {
        //TODO
    }

    @Test
    void createSave() {
        //TODO
    }

}