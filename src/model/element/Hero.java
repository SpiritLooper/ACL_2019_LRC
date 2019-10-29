package model.element;


import controller.Command;
import model.PoolPosition;

/**
 * Classe du Hero du jeu
 * @author gouth
 */
public class Hero implements Entity{
    private Position position;

    /**
     * constructeur par defaut position (0,0)
     */
    public Hero() {
        position = PoolPosition.getInstance().getPosition(1,1);
    }

    /**Constructeur avec une position donnée au héro
     *
     * @param p position du hero où placer le héro
     */
    public Hero(Position p){
        position = p;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    /**Fonction qui prend en paramêtre une commande et qui déplace le héro en fonction de cette dernière
     *
     * @param c : la commande
     * @return la nouvelle position selon la commande
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

    @Override
    public void move(Position p) {
        this.position = PoolPosition.getInstance().getPosition(p);
    }

    @Override
    public String toString(){
        return "Hero : " + position.toString();
    }
}
