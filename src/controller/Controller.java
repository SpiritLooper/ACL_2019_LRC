package controller;

import model.game.Game;

/**
 * Controller of the game acting as a bridge between the game facade and the input manager
 */
public class Controller {

    /**
     * game to send the received command to
     */
    private Game game;

    /**
     * Constructor with the game
     * @param game game to send the received command to
     */
    public Controller (Game game) {
        this.game = game;
    }

    /**
     * Telling the game to move the hero following the received command
     * @param command received command
     */
    public void execute (Command command) {
        if(!game.isFinished()) {
            game.execute(command);
        }
    }

}
