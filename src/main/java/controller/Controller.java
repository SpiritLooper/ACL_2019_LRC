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
     * states if the controller can throw in input in the game or not
     */
    private boolean listening;

    /**
     * Constructor with the game
     * @param game game to send the received command to
     */
    public Controller (Game game) {
        this.game = game;
        this.listening = true;
    }

    /**
     * Telling the game to move the hero following the received command
     * @param command received command
     */
    public void execute (Command command) {
        if(!game.isFinished() && listening) {
            listening = false;
            game.execute(command);
        }
    }

    /**
     * Enables the controller to listen for inputs
     */
    public void listen () {
        listening = true;
    }

}
