package model.menu;

public class GameOverMenu extends AbstractMenu {

    /**
     * Constructor for the menu when dying
     */
    public GameOverMenu(){
        super();
        items = new MenuItem[3];
        items[0] = MenuItem.RESTART;
        items[1] = MenuItem.LOAD;
        items[2] = MenuItem.EXIT;
    }

    public String getText(){
        return "GAME OVER";
    }
}
