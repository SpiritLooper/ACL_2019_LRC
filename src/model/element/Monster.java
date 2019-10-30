package model.element;

import controller.Command;
import model.PoolPosition;

/**
 * Classe qui modélise un montre
 * @author gouth
 */
public abstract class Monster implements Entity {

    private Position position;

    /**Constructeur
     *
     * @param position position par défaut du monstre
     */
    public Monster(Position position){
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void move(Position p) {
        this.position = p;
    }

    public abstract Position behave ();

    /**
     * Duplicatiopn de code parce que j'ai r compris à ce qu'il s'est passé & wtf la double info de position sur les entites
     * @param c une commande
     * @return la position après la commande
     */
    public Position getNewPosition(Command c){
        Position p;
        PoolPosition pool = PoolPosition.getInstance();
        switch (c){
            case UP:
                p = pool.getPosition(position.getX(), position.getY()-1); //-1 pour aller vers le haut (IG)
                break;
            case DOWN:
                p = pool.getPosition(position.getX(), position.getY()+1);
                break;
            case LEFT:
                p = pool.getPosition(position.getX()-1, position.getY());
                break;
            case RIGHT:
                p = pool.getPosition(position.getX()+1, position.getY());
                break;

            default://par defaut on a pas eu de direction on reste au meme endroit
                p = position;
        }
        if(p.getX()==-1 && p.getY()==-1){
            p = position;
        }
        return p;
    }
}
