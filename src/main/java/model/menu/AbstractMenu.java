package model.menu;

import controller.Command;

public abstract class AbstractMenu {

    /**
     * currently selected item of the menu
     */
    protected int selected;

    /**
     * is the menu open?
     */
    protected boolean open;

    /**
     * items of this menu
     */
    protected MenuItem[] items;

    /**
     * Constructor initializing the selected item to CONTINUE
     */
    protected AbstractMenu() {
        selected = 0;
    }

    /**
     * Opens the menu
     */
    public void open () {
        open = true;
    }

    /**
     * Closes the menu and puts the selected item to CONTINUE
     */
    public void close () {
        selected = 0;
        open = false;
    }

    /**
     * Controls the menu with the help of the received command and returns whether a item has been selected or not
     * @param command user input
     * @return the selected item if SPACE is received, CONTINUE if ESCAPE is received or IDLE else
     */
    public MenuItem control (Command command) {
        switch (command) {
            case UP:
                if (selected - 1 == -1) {
                    selected = items.length - 1;
                } else {
                    selected = selected - 1;
                }
                break;

            case DOWN:
                if (selected + 1 == items.length) {
                    selected = 0;
                } else {
                    selected = selected + 1;
                }
                break;

            case SPACE:
                return items[selected];

            case ESCAPE:
                return items[0];

            default:
                break;
        }

        return MenuItem.IDLE;
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
    public int getSelected () {
        return selected;
    }

    /**
     * @return array of the menu items of this menu
     */
    public MenuItem[] getMenuItems () {
        return items;
    }

    /**
     * Get the name of menu to display
     * @return string name
     */
    public abstract String getText();
}
