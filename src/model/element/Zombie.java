package model.element;

import controller.Command;

import java.util.Random;

public class Zombie extends Monster {

    public Zombie(Position position) {
        super(position);
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }

    @Override
    public void move(Position p) {
        super.move(p);
    }

    @Override
    public Position behave() {
        Random r = new Random();
        int rand = r.nextInt(5);

        return getNewPosition(Command.values()[rand]);
    }
}
