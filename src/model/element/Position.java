package model.element;

import controller.Command;

/**
 * classe position en coordonnées entière, (x,y)
 */
public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    private void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Position getPosition(){
        return this;
    }

    private int getX(){
        return this.x;
    }

    private int getY(){
        return this.y;
    }

    void setPosition(Position p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * a partir d'une position déjà existante, on la modifie en fonction de la commande
     * @param command : la commande
     */
    public void setPosition(Command command) {

        switch (command){
            case UP:
                this.setPosition(this.getX(), this.getY()-1); //-1 pour aller vers le haut (IG)
                break;
            case DOWN:
                this.setPosition(this.getX(), this.getY()+1);
                break;
            case LEFT:
                this.setPosition(this.getX()-1, this.getY());
                break;
            case RIGHT:
                this.setPosition(this.getX()+1, this.getY());
                break;

            default://par defaut on a pas eu de direction on reste au meme endroit

        }
    }
}
