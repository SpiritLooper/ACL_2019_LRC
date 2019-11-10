package model.element;


/**
 * The hero is the character you are controlling in the game
 * @author gouth
 */
public class Hero implements Entity{

    /**
     * Position of the hero
     */
    private Position position;

    /**
     * Health points of the hero
     */
    private int hp;

    /**
     * Attack of the hero
     */
    private int atk;

    /**
     * Constructor with the spawn position
     */
    public Hero(Position position) {
        this.position = position;
        hp = 10;
        atk = 1;
    }

    /**
     * @return position of the hero
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the hero position
     * @param position position to set the hero to
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Hero<" + position.getX() + "," + position.getY() + ">";
    }

    @Override
    public void attack(Entity e) {
        hp = hp - e.getAtk();
        e.hit(atk);
    }

    @Override
    public void hit(int atk) {
        hp = hp - atk;
    }

    @Override
    public int getAtk() {
        return atk;
    }

    @Override
    public int getHp() {
        return hp;
    }
}
