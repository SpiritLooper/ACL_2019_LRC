package model.element;

import model.game.Game;

public class Stairs extends EventTile {

    public Stairs(Position position, Game game) {
        super(position, game);
    }

    @Override
    public void fireEvent() {
        game.nextLevel();
    }
}
