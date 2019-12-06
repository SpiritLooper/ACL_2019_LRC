package model.menu;

public class GameOver extends AbstractMenu {

    public GameOver(){
        super();
    }

    @Override
    public MenuItem[] getMenuItems(){
        MenuItem[] items = new MenuItem[3];
        items[0] = MenuItem.RESTART;
        items[1] = MenuItem.LOAD;
        items[2] = MenuItem.EXIT;

        return items;
    }

    public String getText(){
        return "GAME OVER";
    }
}
