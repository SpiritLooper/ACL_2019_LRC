package model.element;

/**
 * interface modelisant le comportement des entités dans le jeu
 * les entités sont les objets qui "bougent" dans le niveau
 */
public interface Entity {

    /**Fonction qui retourne la position de l'entité
     *
     * @return la position de l'entité
     */
    Position getPosition();

    /**Fonction qui déplace l'entité à la position donnée en paramêtre
     *
     * @param p la position de l'entité
     */
    void move(Position p);
}
