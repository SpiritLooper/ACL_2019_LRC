package view.drawMode;

import java.awt.*;

/**
 * Interface permettant de decrire comment dessiner un etat du jeu
 */
public interface DrawMode {

    public void draw(Graphics2D g, int iFrame);
}
