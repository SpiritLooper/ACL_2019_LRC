package controller;

import model.game.Game;

public class Controller {

    private Game game;

    public Controller (Game game) {
        this.game = game;
    }

    private void moveHero (Command command) {
        game.moveHero(command);
    }

}
