package model.element;

import model.game.Game;

public class Treasure extends EventTile {

    public Treasure(Position position, Game game) {
        super(position, game);
    }

    @Override
    public void fireEvent() {
        game.finish(true);
    }
}
