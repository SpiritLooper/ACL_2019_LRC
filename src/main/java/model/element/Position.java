package model.element;

import controller.Command;
import model.PositionPool;

import java.util.Objects;

/**
 * Represents a position in a 2-dimensional integer world
 * Immutable objects being managed by the model.PositionPool
 */
public class Position {

    /**
     * x coordinate of the position (final)
     */
    private final int x;

    /**
     * y coordinate of the position (final)
     */
    private final int y;

    /**
     * Constructor with the x and y coordinates
     * @param x x coordinate of the position
     * @param y y coordinate of the position
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * @return x coordinate of the position
     */
    public final int getX(){
        return x;
    }

    /**
     * @return y coordinate of the position
     */
    public final int getY(){
        return y;
    }

    /**
     * Calculates a position relative to this one compared to a command or this one if the new position is off the board
     * @param c command to determinate the new position
     * @return position relative to this one compared to a command or this one
     */
    public Position applyCommand (Command c) {
        //returns the position if in reach or break the switch statement if not (or if IDLE), then returns this position in this case

        switch (c) {
            case UP:
                if (PositionPool.getInstance().isInBounds(x, y - 1)) {
                    return PositionPool.getInstance().getPosition(x, y - 1);
                }
                break;

            case DOWN:
                if (PositionPool.getInstance().isInBounds(x, y + 1)) {
                    return PositionPool.getInstance().getPosition(x, y + 1);
                }
                break;

            case LEFT:
                if (PositionPool.getInstance().isInBounds(x - 1, y )) {
                    return PositionPool.getInstance().getPosition(x - 1, y);
                }
                break;

            case RIGHT:
                if (PositionPool.getInstance().isInBounds(x + 1, y)) {
                    return PositionPool.getInstance().getPosition(x + 1, y);
                }
                break;

            case IDLE:
            default:
                break;
        }

        //the wanted position is off reach, returning this one
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) { //peut mieux faire ?
        Position p = (Position) obj;
        return this.getX() == p.getX() && this.getY() == p.getY();
    }

    @Override
    public String toString(){
        return "<" + x + "," + y + ">";
    }

}
