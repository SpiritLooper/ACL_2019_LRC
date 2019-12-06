package model.menu;

/**
 * Is used to select between 4 items : CONTINUE, SAVE, LOAD or EXIT
 * The selected item always returns to CONTINUE on close.
 * Opened with the ESCAPE key, navigate with UP and DOWN keys, close it with CONTINUE or ESCAPE key and select an item with SPACE key
 */
public class MainMenu extends AbstractMenu{

    /**
     * Constructor for the menu when pressing the escape key
     */
    public MainMenu(){
        super();
        items = new MenuItem[5];
        items[0] = MenuItem.CONTINUE;
        items[1] = MenuItem.SAVE;
        items[2] = MenuItem.LOAD;
        items[3] = MenuItem.RESTART;
        items[4] = MenuItem.EXIT;
    }

}
