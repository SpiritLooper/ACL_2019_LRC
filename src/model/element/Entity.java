package model.element;

/**
 * interface modelisant le comportement des entités dans le jeu
 * les entités sont les objets qui "bougent" dans le niveau
 */
public interface Entity {
    Position getPosition();
    boolean move(Position p);
}
