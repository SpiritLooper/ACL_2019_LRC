package model.menu;

import controller.Command;

/**
 * Is used to select between 4 items : CONTINUE, SAVE, LOAD or EXIT
 * The selected item always returns to CONTINUE on close.
 * Opened with the ESCAPE key, navigate with UP and DOWN keys, close it with CONTINUE or ESCAPE key and select an item with SPACE key
 */
public class Menu {

    /**
     * currently selected item of the menu
     */
    private MenuItem selected;

    /**
     * is the menu open?
     */
    private boolean open;

    /**
     * Constructor initializing the selected item to CONTINUE
     */
    public Menu () {
        selected = MenuItem.CONTINUE;
    }

    /**
     * Controls the menu with the help of the received command and returns whether a item has been selected or not
     * @param command user input
     * @return the selected item if SPACE is received, CONTINUE if ESCAPE is received or IDLE else
     */
    public MenuItem control (Command command) {
        switch (command) {
            case UP:
                if (selected.ordinal() - 1 == -1) {
                    selected = MenuItem.values()[MenuItem.values().length - 2];
                } else {
                    selected = MenuItem.values()[(selected.ordinal() - 1) % (MenuItem.values().length - 1)];
                }
                break;

            case DOWN:
                selected = MenuItem.values()[(selected.ordinal() + 1) % (MenuItem.values().length - 1)];
                break;

            case SPACE:
                return selected;

            case ESCAPE:
                return MenuItem.CONTINUE;

            default:
                break;
        }

        return MenuItem.IDLE;
    }

    /**
     * Opens the menu
     */
    public void open () {
        open = true;
        //System.out.println("Menu est ouvert");
    }

    /**
     * Closes the menu and puts the selected item to CONTINUE
     */
    public void close () {
        selected = MenuItem.CONTINUE;
        open = false;
        //System.out.println("Menu est fermé");
    }

    /**
     * @return true if the menu is open, false else
     */
    public boolean isOpen () {
        return open;
    }

    /**
     * @return selected item of the menu
     */
    public MenuItem getSelected () {
        return selected;
    }

}
