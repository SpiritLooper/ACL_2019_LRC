package view.drawMode;

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

    private final static int MARGIN_TOP = Painter.getHeight() / MenuItem.values().length;
    private final static int MARGIN_LEFT = Painter.getWidth() / 4;
    private static final int PADDING = FONT_SIZE * 2;

    private Game game;

    public DrawMenu(Game game) {
        this.game = game;
    }

    @Override
    public void draw(Graphics2D g,int IFrame) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0 , Painter.getWidth(), Painter.getHeight() );

        drawItem(g);
    }

    /**
     * Dessine les items du menu
     * @param g Image sur laquelle dessinee
     */
    private void drawItem(Graphics2D g) {
        MenuItem[] items = MenuItem.values();
        g.setColor(Color.WHITE);
        g.setFont(STANDARD_FONT);

        int i = MARGIN_TOP;
        for (MenuItem item : items) {
            if( item == MenuItem.IDLE )
                continue;

            String name = item.name();

            g.drawString(name,  MARGIN_LEFT, i );

            i += PADDING;
        }

        g.drawString( ""+CHAR_SELECTED, MARGIN_LEFT - FONT_SIZE , MARGIN_TOP + (game.getSelectedMenuItem() * PADDING));

    }

}
