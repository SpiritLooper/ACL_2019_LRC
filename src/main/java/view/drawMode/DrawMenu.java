package view.drawMode;

import model.menu.AbstractMenu;
import view.Painter;
import model.menu.MenuItem;
import model.game.Game;

import java.awt.*;

import static view.Painter.*;

/**
 * Dessine le menu du jeu
 */
public class DrawMenu implements DrawMode {

    private final static char CHAR_SELECTED = '>';

    private final static int MARGIN_TOP = Painter.getHeight() / ( MenuItem.values().length + 1 );
    private final static int MARGIN_LEFT = Painter.getWidth() / 4;
    private static final int PADDING = FONT_SIZE * 2;

    private Game game;
    private AbstractMenu menuActual;

    public DrawMenu(Game game) {
        this.game = game;
    }

    public void setMenu(AbstractMenu m){
        menuActual = m;
    }

    @Override
    public void draw(Graphics2D g, int Iframe) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0 , Painter.getWidth(), Painter.getHeight() );

        drawItem(g);
    }

    /**
     * Dessine les items du menu
     * @param g Image sur laquelle dessinee
     */
    private void drawItem(Graphics2D g) {
        MenuItem[] items;
        items = menuActual.getMenuItems();

        g.setColor(Color.WHITE);
        g.setFont(STANDARD_FONT);

        g.drawString(menuActual.getText(), MARGIN_LEFT - FONT_SIZE , MARGIN_TOP );

        int i = MARGIN_TOP + PADDING;
        for (MenuItem item : items) {
            if( item == MenuItem.IDLE )
                continue;

            String name = item.name();

            g.drawString(name,  MARGIN_LEFT, i );

            i += PADDING;
        }

        g.drawString( ""+CHAR_SELECTED, MARGIN_LEFT - FONT_SIZE , MARGIN_TOP + ((menuActual.getSelected() + 1 )* PADDING));

    }

}
