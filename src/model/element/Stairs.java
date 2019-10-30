package model.element;

import model.game.Game;

public class Stairs extends EventTile {

    private Game game;

    public Stairs(Position position, Game g) {
        super(position);
        game = g;
    }

    @Override
    public void fireEnvent() {
        game.nextLevel();
    }
}
