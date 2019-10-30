package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {

    private Controller controller;
    private Command currentCommand;

    private final char upKey = 'z';
    private final char downKey = 's';
    private final char leftKey = 'q';
    private final char rightKey = 'd';
    private final char idleKey = 'r';

    public InputManager (Controller controller) {
        this.controller = controller;
        this.currentCommand = Command.IDLE;
    }

    // launch whenever a key input is followed by a key release
    @Override
    public void keyTyped(KeyEvent e) {

        switch (e.getKeyChar()) {
            case upKey:
                currentCommand = Command.UP;
                break;

            case downKey:
                currentCommand = Command.DOWN;
                break;

            case leftKey:
                currentCommand = Command.LEFT;
                break;

            case rightKey:
                currentCommand = Command.RIGHT;
                break;

            default:
            case idleKey:
                currentCommand = Command.IDLE;
                break;
        }

        controller.move(currentCommand);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public Command getCurrentCommand () {
        return currentCommand;
    }

}
