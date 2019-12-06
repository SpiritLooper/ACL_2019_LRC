package model.menu;

import controller.Command;

/**
 * Is used to select between 4 items : CONTINUE, SAVE, LOAD or EXIT
 * The selected item always returns to CONTINUE on close.
 * Opened with the ESCAPE key, navigate with UP and DOWN keys, close it with CONTINUE or ESCAPE key and select an item with SPACE key
 */
public class Menu extends AbstractMenu{

    public Menu(){
        super();
    }

    @Override
    public MenuItem[] getMenuItems(){
        return MenuItem.values();
    }

}
