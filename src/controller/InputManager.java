package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Manages the keyboard inputs from the user (player)
 */
public class InputManager implements KeyListener {

    /**
     * Controller to use to transfer the command
     */
    private Controller controller;

    /**
     * current command input from the user
     */
    private Command currentCommand;

    /**
     * key representing the up movement
     */
    private final char UP_KEY = 'z';

    /**
     * key representing the down movement
     */
    private final char DOWN_KEY = 's';

    /**
     * key representing the left movement
     */
    private final char LEFT_KEY = 'q';

    /**
     * key representing the right movement
     */
    private final char RIGHT_KEY = 'd';

    /**
     * key representing the idle movement
     */
    private final char IDLE_KEY = 'r';

    /**
     * Constructor linking the controller
     * @param controller controller to link
     */
    public InputManager (Controller controller) {
        this.controller = controller;
        this.currentCommand = Command.IDLE;
    }

    /**
     * Launched whenever a key is pressed then released by the user on the keyboard, scanning the input and sending the corresponding command to the controller
     * @param e keyboard input event
     */
    @Override
    public void keyTyped(KeyEvent e) {

        switch (e.getKeyChar()) {
            case UP_KEY:
                currentCommand = Command.UP;
                break;

            case DOWN_KEY:
                currentCommand = Command.DOWN;
                break;

            case LEFT_KEY:
                currentCommand = Command.LEFT;
                break;

            case RIGHT_KEY:
                currentCommand = Command.RIGHT;
                break;

            default:
            case IDLE_KEY:
                currentCommand = Command.IDLE;
                break;
        }

        controller.move(currentCommand);

        currentCommand = Command.IDLE;
    }

    /**
     * Launched when pressing a key, not used
     * @param e keyboard input event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        return;
    }

    /**
     * Launched when releasing a key, not used
     * @param e keyboard input event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        return;
    }

    /**
     * @return the current keyboard input from the user
     */
    public Command getCurrentCommand () {
        return currentCommand;
    }

}
