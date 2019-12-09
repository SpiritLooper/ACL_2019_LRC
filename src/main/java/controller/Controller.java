package controller;

/**
 * Controller of the game acting as a bridge between the game facade and the input manager
 */
public class Controller {

    /**
     * currently pushed command
     */
    private Command currentCommand = Command.IDLE;

    public Command getCommand() {
        Command res = this.currentCommand;
        this.currentCommand = Command.IDLE;
        return res;
    }

    /**
     * Sets the current command
     * @param currentCommand current command to affect
     */
    public void setCommand(Command currentCommand) {
        this.currentCommand = currentCommand;
    }

}
