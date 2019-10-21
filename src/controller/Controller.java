package controller;

public class Controller {

    private Game game;

    public Controller (Game game) {
        this.game = game;
    }

    public void moveHero (Command command) {
        game.moveHero(command);
    }

}
