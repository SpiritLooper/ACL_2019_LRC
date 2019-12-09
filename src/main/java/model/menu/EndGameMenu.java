package model.menu;

public class EndGameMenu extends AbstractMenu {

    /**
     * Constructor for a menu when reaching the treasure
     */
    public EndGameMenu(){
        super();
        items = new MenuItem[2];
        items[0] = MenuItem.RESTART;
        items[1] = MenuItem.EXIT;
    }

    public String getText(){
        return "YOU WIN !";
    }
}
