package model.menu;

public class EndGame extends AbstractMenu {

    public EndGame(){
        super();
    }

    @Override
    public MenuItem[] getMenuItems(){
        MenuItem[] items = new MenuItem[2];
        items[0] = MenuItem.RESTART;
        items[1] = MenuItem.EXIT;

        return items;
    }

    public String getText(){
        return "You Win !";
    }
}
