package model.game;

import controller.Command;
import engine.Engine;
import model.PoolPosition;
import model.element.Hero;
import model.element.Position;
import model.element.Stairs;
import model.element.Tresure;

import java.util.Collection;

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

    private Engine observer;

    private boolean isFinished = false;

    public Game(int width, int height){
        this.width = width;
        this.height = height;

        PoolPosition.init(width, height); //pour initialisé la pool singleton

        //position initiale du héro en 0,0 le coin en haut à gauche POUR L'INSTANT
        Position posStartHero = PoolPosition.getInstance().getPosition(1,1);
        hero = new Hero(posStartHero);

    }

    public void setObserver(Engine e){
        this.observer = e;
    }

    private void notifyObserver() { // Equivalent à l'update
        this.observer.update();
    }


    /**
     * Fonction qui instensie le level actuel
     * @param l
     */
    private void setLevel(Level l){
        level = l;
    }

    /**
     * Fonction façade pour connaitre la position des monstres
     * @return l'ensemble des positions
     */
    public Collection<Position> getMonstersPosition() {
        return level.getMonstersPosition();
    }

    /**
     * Fonction façade pour connaitre la position des murs
     * @return l'ensemble des positions
     */
    public Collection<Position> getWallsPosition() { //TODO : a corriger il faut choisir les murs seulement
        return level.getTilesEventPosition();
    }

    /**
     * Fonction façade pour connaitre la position des tuiles evenements
     * @return l'ensemble des positions
     */
    public Collection<Position> getEventTilesPosition() {
        return level.getTilesEventPosition();
    }

    /**Fonction qui, selon la commande rentré en paramêtre, applique un mouvement au héro
     *
     * @param command
     */
    public void moveHero(Command command) {
        Position p = hero.getNewPosition(command); //le jeu demande au héro sa position s'il execute sa commande
        if(level.canHeroMove(p)){ //si le hero peut bouger à cette nouvelle position alors
            hero.move(p);        //le hero bouge
            level.fireEventTile(p);
        }
        notifyObserver(); // Mis à jour de la vue
    }

    protected Hero getHero() {
        return hero;
    }

    /**
     * Indique si le level possède un trésor
     * @return vrai s'il y en a un
     */
    public boolean hasATresorInLevel() {
        return level.hasATresor();
    }

    /**
     * Permet de savoir la position du tresor
     * @return la position du tresor
     */
    public Position getTresorPosition() {
        return level.getPositionTresor();
    }

    /**
     * Permet de savoir la position de l'escalier
     * @return la position de l'escalier
     */
    public Position getStairsPosition() {
        return level.getPositionStairs();
    }

    /**
     * Fonction qui change de level et qui redeplace le heros
     */
    public void nextLevel() {
        level = level.nextLevel();

        Position p = PoolPosition.getInstance().getPosition(0,0);

        hero.move(p);
    }

    /**
     * Permet d'obtenir la position du hero
     * @return
     */
    public Position getHeroPosition() {
        return hero.getPosition();
    }

    /**
     * Génére le jeu et ses niveaux
     */
    public void generateGame() {
        //Génération par défaut
        Level level1 = new Level();
        level1 = level1.generateDefaultLevel(this);

        //Ajout stairs
        Position p = PoolPosition.getInstance().getPosition(4,4);
        level1.addTile(p, new Stairs(p, this));

        Level level2 = new Level();
        level2 = level2.generateDefaultLevel(this);

        //Ajout Tresor
        p = PoolPosition.getInstance().getPosition(5,4);
        level2.addTile(p, new Tresure(p, this));


        level1.setNextLevel(level2);

        this.setLevel(level1);//on bind la game au level
    }

    /**
     * Permet d'obtenir la dimension du level
     * @return la derniere tuile au max HAUTEUR et LARGEUR
     */
    public Position getMaxDimLevel() {
        return PoolPosition.getInstance().getPosition(width - 1, height - 1);
    }

    /////---------------TEST-------------------

    public static void main(String[] argv){
        testLevel();
    }

    public static void testLevel() {
        Game game = new Game(3,3);
        Level level = new Level();
        level.generateDefaultLevel(game);

        game.setLevel(level);//on bind la game au level

        Command commands[] = new Command[5];
        commands[0] = Command.UP;
        commands[1] = Command.DOWN;
        commands[2] = Command.LEFT;
        commands[3] = Command.RIGHT;
        commands[4] = Command.IDLE;

        for(int i=0; i<5; i++){
            game.moveHero(commands[i]);
        }


        System.out.println(game.getHero());
        game.moveHero(commands[1]);
        System.out.println(game.getHero()); //test coord 2,2, y'a un monstre il ne peut pas y aller
        game.moveHero(commands[3]);
        System.out.println(game.getHero());

    }

    /**
     * Indique si le jeu est fini ou non
     * @return
     */
    public boolean isFinished() {
        return isFinished;
    }

    /**
     * REnd le jeu à l'état fini
     */
    public void finished() {
        isFinished = true;
    }
}


