package main.java.model.element.entities;

import main.java.model.PositionPool;
import main.java.model.element.entities.Hero;
import main.java.model.element.entities.Monster;
import main.java.model.element.entities.BasicMonster;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    private static Hero h0;

    @BeforeAll
    static void init(){

    }

    @BeforeEach
    void setUp(){
        h0 = new Hero(PositionPool.getInstance().getPosition(0,0));
    }

    @AfterAll
    static void end(){

    }

    @AfterEach
    void tearDown(){

    }

    @Test
    void getPositionRight() {
        assertEquals(PositionPool.getInstance().getPosition(0,0),h0.getPosition());
    }

    @Test
    void getPositionRight2(){
        Hero h = new Hero(PositionPool.getInstance().getPosition(2,2));
        assertEquals(PositionPool.getInstance().getPosition(2,2),h.getPosition());
    }

    @Test
    void getPositionFalse(){
        assertNotEquals(PositionPool.getInstance().getPosition(1,1), h0.getPosition());
    }

    @Test
    void setPosition() {
        h0.setPosition(PositionPool.getInstance().getPosition(1,1));
        assertEquals(PositionPool.getInstance().getPosition(1,1),h0.getPosition());
    }

    @Test
    void setSamePosition(){
        h0.setPosition(PositionPool.getInstance().getPosition(0,0));
        assertEquals(PositionPool.getInstance().getPosition(0,0),h0.getPosition());
    }

    @Test
    void testToString(){
        assertEquals("Hero<0,0>", h0.toString());
    }

    @Test
    void attack() {
        Monster m = new BasicMonster();
        int hpMonsterBeforeHit = m.getHp();
        int hpHeroBeforeHit = h0.getHp();
        h0.attack(m);
        assertSame(hpHeroBeforeHit-m.getAtk(), h0.getHp());
        assertSame(hpMonsterBeforeHit-h0.getAtk(), m.getHp());
    }

    @Test
    void hit() {
        h0.hit(1);
        assertSame(9,h0.getHp());
    }

    @Test
    void heal() {
        int hpBeforeHeal = h0.getHp();
        h0.heal(1);
        assertSame(hpBeforeHeal+1, h0.getHp());
    }
}