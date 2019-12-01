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
    private final int UP_KEY = 'z';

    /**
     * key representing the down movement
     */
    private final int DOWN_KEY = 's';

    /**
     * key representing the left movement
     */
    private final int LEFT_KEY = 'q';

    /**
     * key representing the right movement
     */
    private final int RIGHT_KEY = 'd';

    /**
     * key representing the idle movement
     */
    private final int IDLE_KEY = 'x';

    /**
     * key representing the menu opening
     */
    private final int ESCAPE_KEY = 'a';

    /**
     * key representing the select in menu
     */
    private final int SPACE_KEY = ' ';

    /**
     * used to determine the amount of time the key is pressed
     * in order to rotate or move the hero
     */
    private long previous_time;

    private long delta;

    private boolean pressed;

    /**
     * Constructor linking the controller
     * @param controller controller to link
     */
    public InputManager (Controller controller) {
        this.controller = controller;
        this.currentCommand = Command.IDLE;
        previous_time = 0;
        delta = 0;
        pressed = false;

    }

    /**
     * Launched whenever a key is pressed then released by the user on the keyboard, scanning the input and sending the corresponding command to the controller
     * @param e keyboard input event
     */
    @Override
    public void keyTyped(KeyEvent e) {


    }

    /**
     * Launched when pressing a key start the timer
     * @param e keyboard input event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("KEYRELEASED TIME : " + System.currentTimeMillis());
       if(!pressed) {
           previous_time = System.currentTimeMillis();
           pressed = true;
       }
    }

    /**
     * Launched when releasing a key, stop the timer
     * @param e keyboard input event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        delta = System.currentTimeMillis() - previous_time;
        pressed = false;
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

            case ESCAPE_KEY:
                currentCommand = Command.ESCAPE;
                break;

            case SPACE_KEY:
                currentCommand = Command.SPACE;
                break;

            case IDLE_KEY:
                currentCommand = Command.IDLE;
                break;

            default:
                return;

        }

        controller.setDurationKey(delta > 150);
        controller.setCommand(currentCommand);


        currentCommand = Command.IDLE;
    }

}
