package model.game;

import controller.Command;
import model.element.Hero;
import model.element.Position;

/**
 * classe façade faisant le lien dans le MVC entre le controlleur la vue et le modèle.
 * dans cette version:
 * le héro peut se déplacer dans les différents niveaux
 * les monstres se mettent à jour i.e ils se déplacent
 * les cases trésors et escaliers s'activent quand le héro est dessus
 * Game connaît un Héro
 * @author gouth
 */
public class Game {
    /*
    pour effectuer les tests j'ai (gouth) pris la liberté de rajouter ces deux attributs width et height
    pcq on a pas encore de lecture de fichier avec Parser etc
     */
    private int width; //largeur du niveau
    private int height; //hauteur du niveau

    private Hero hero;
    private Level level; //current

    public Game(int width, int height){
        this.width = width;
        this.height = height;

        //position initiale du héro en 0,0 le coin en haut à gauche POUR L'INSTANT
        Position posStartHero = new Position(0,0);
        hero = new Hero(posStartHero);
    }


    public void moveHero(Command command) {

        Position p = hero.getPosition();
        //la position actuelle du héro qu'on va modifier suivant la (commande)
        //pour avoir la position potentielle et vérifier si la case est libre etc..

        p.setPosition(command); //la classe Position traduit la Command en une nouvelle position

        if(canHeroMove(p)){ //si le hero peut bouger à cette nouvelle position alors
            hero.move(p);        //le hero bouge
        }

    }

    private boolean canHeroMove(Position p) {
        return level.canHeroMove(p);
    }
}
