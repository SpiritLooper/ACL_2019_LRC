package model.element;

import model.game.Game;

public class Tresure extends EventTile {

    private Game game;

    public Tresure(Position position, Game g) {
        super(position);
        this.game = g;
    }

    @Override
    public void fireEnvent() {
        game.finished();
    }
}
