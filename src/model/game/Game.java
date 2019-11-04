package model.game;

import controller.Command;
import engine.Engine;
import model.PoolPosition;
import model.element.Hero;
import model.element.Position;
import model.element.Stairs;
import model.element.Treasure;

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
    public static int width; //largeur du niveau
    public static int height; //hauteur du niveau

    private Level level; //current

    private Engine observer;

    private Timer timer;

    private boolean isFinished = false;

    private boolean won = false; //détermine si le jeu est gagné ou non

    public Game(int width, int height){
        this.width = width;
        this.height = height;

        this.timer = new Timer();

        PoolPosition.init(width, height); //pour initialisé la pool singleton

        //position initiale du héro en 0,0 le coin en haut à gauche POUR L'INSTANT
        Position posStartHero = PoolPosition.getInstance().getPosition(1,1);
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

    public Level getLevel () {
        return level;
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
        level.moveHero(command);
        update();
    }

    /**
     * Update the current game state by updating all the monsters of the level
     */
    private void update () {
        level.update();
        if (timer.tick() <= 0) {
            finish(false);
        }
        System.out.println(timer.getTimeLeft());
        notifyObserver();
    }

    public int getTimeLeft () {
        return timer.getTimeLeft();
    }

    public Position getHeroPosition () {
        return level.getHero().getPosition();
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
        level2.addTile(p, new Treasure(p, this));


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
    public void finish (boolean won) {
        this.won = won;
        isFinished = true;
    }

    // return si le jeu est gagné ou non
    public boolean isGameWon () {
        return won;
    }

}


