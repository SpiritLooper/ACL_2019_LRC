package model.game;

import controller.Command;
import model.element.tiles.Tile;
import view.Engine;
import model.*;
import model.element.Position;
import model.element.tiles.Treasure;
import model.menu.Menu;
import model.persistency.GameParser;
import model.persistency.LevelDAO;
import model.persistency.SaveDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * todo nettoyer ca
 * Facade connecting the Model to the View for the MVC architecture
 * @author gouth
 */
public class Game {

    /**
     * Width of the game
     */
    public static final int WIDTH = 10; //largeur du niveau

    /**
     * Heigth of the game
     */
    public static final int HEIGHT = 10; //hauteur du niveau

    /**
     * menu of the game
     */
    private Menu menu;

    /**
     * current level to play in
     */
    private Level level;

    /**
     * graphical engine drawing the game
     */
    private Engine engine;

    /**
     * is the game finished or not?
     */
    private boolean finished;

    /**
     * is the game won or lost? (used when the game is finished)
     */
    private boolean won;

    /**
     * Etat du jeu, a envoyer a la vue
     */
    private final GameStatement gameStatement = new GameStatement();

    /**
     * Constructor instantiating a timer and setting the game to not finished
     */
    public Game(){
        this.menu = new Menu();
        this.level = null;
        this.engine = null;
        this.finished = false;
        this.won = false;
        EventManager.getINSTANCE().setGame(this);
    }

    /**
     * Setting the current level
     * @param l current level
     */
    private void setLevel(Level l){
        this.level = l;
    }

    /**
     * Setting the observer
     * @param e engine observing the game
     */
    public void setEngine (Engine e){
        this.engine = e;
    }

    /**
     * @return current level
     */
    public Level getLevel () {
        return level;
    }

    /**
     * @return time left before its out
     */
    public int getTimeLeft () {
        return level.getTimer().getTimeLeft();
    }

    /**
     * @return true if the game is finished, false else
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * @return true if the game is won, false if lost
     */
    public boolean isGameWon () {
        return won;
    }

    /**
     * Executes the received command for the menu of open or the hero
     * @param command user input
     */
    public void execute (Command command) {
        //controls the menu if it is open
        if (menu.isOpen()) {
            switch (menu.control(command)) {
                case CONTINUE:
                    menu.close();
                    break;

                case SAVE:
                    saveGame();
                    menu.close();
                    break;

                case LOAD:
                    loadSave();
                    menu.close();
                    break;

                case EXIT:
                    finish(false);
                    menu.close();
                    break;

                case IDLE:
                default:
                    break;
            }

            notifyEngine();
            return;
        }

        //if the escape key is input while in game we open the menu
        if (command == Command.ESCAPE) {
            menu.open();
            notifyEngine();
            return;
        }

        moveHero(command);
        update();
    }

    /**
     * Method that makes a save of the game
     */
    private void saveGame () {
        try {
            GameParser.getINSTANCE().writeSaveFile(level.createSave());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that load a level and set the current level to it
     */
    private void loadSave () {
        try {
            SaveDAO save = GameParser.getINSTANCE().parseSaveFile();
            level.loadSave(save);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generate the levelX.lyt in the game
     * @param lvl : the indice of the file levelX.lyt
     * @return : a Level class, which correspond to the levelX.lyt
     */
    private Level generateLevel(int lvl){
        try {
            LevelDAO lvlDAO = GameParser.getINSTANCE().parseLevelFile(lvl);
            Level level = new Level();
            level.generate(lvlDAO);
            return level;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Level(); //astuce pour le try catch normalement on ne retourne jamais un lvl vide
    }

    /**
     * @return true if the menu is open, false else
     */
    public boolean isMenuOpen () {
        return menu.isOpen();
    }

    /**
     * @return an integer (0..3) representing the selected item in the menu : 0:CONTINUE, 1:SAVE, 2:LOAD, 3:EXIT
     */
    public int getSelectedMenuItem () {
        return menu.getSelected().ordinal();
    }

    /**
     * Moves the hero based on a command
     * @param command command representing the move to make
     */
    public void moveHero(Command command) {
        level.moveHero(command);
    }

    /**
     * Updates the level, decrease the remaining time (finish the game if the time is out and notifies the engine
     */
    private void update () {
        level.update();

        if (level.getTimer().getTimeLeft()<= 0 || level.getHeroHp() <= 0) {
            finish(false);
        }

        notifyEngine();
    }

    /**
     * @return true if the level contains a treasure, false else
     */
    public boolean hasATreasureInLevel() {
        return level.hasATreasure();
    }

    /**
     * Put the current level to the next level
     */
    public void nextLevel() {
        int lifeHero = level.getHeroHp();
        level = level.nextLevel();
        level.setHeroHp(lifeHero);
    }

    /**
     * Generates a basic game
     */
    public void generateGame() {


        int nbLevel = GameParser.getINSTANCE().getNbLevel();

        //on instancie les niveaux du plus grand au plus petit
        //en effet le level_i étant bindé au level_i+1
        //il faut bien avoir déjà instancié le level_i+1
        //le parcours dans l'autre sens résoud ce problème
        //je les stocks dans un tableau histoire de pouvoir bien
        //les binder sans trop de prise de tête

        System.out.println("Will generate " + nbLevel + " levels....");
        Level[] arrayLevels = new Level[nbLevel];

        for(int i = nbLevel; i!=0; i--){
            System.out.println("generating level"+i);
            arrayLevels[i-1] = generateLevel(i);
            System.out.println(arrayLevels[i-1]);
        }

        setLevel(arrayLevels[0]); //on commence au premier niveau

        for(int i = 0; i<nbLevel-1; i++){ //on bind les niveaux entre eux
            arrayLevels[i].setNextLevel(arrayLevels[i+1]);
            arrayLevels[i] = null;//on libère la mémoire, on en a plus besoin
            //l'idée est que les niveaux se connaissent entre eux mtnt plus besoin de tabs
        }


        level.updateGameStatement(this.gameStatement); // Mis a jour de l'instance de jeu
    }


    /**
     * Ends the game
     * @param won true if the player won, false if he lost
     */
    public void finish (boolean won) {
        this.won = won;
        finished = true;
    }

    /**
     * Notifies to the engine that the game updated
     */
    private void notifyEngine() { // Equivalent à l'update
        this.gameStatement.clear();

        level.updateGameStatement(this.gameStatement);

        this.engine.update();
    }

    public void notifyNextLevelEngine() {
        this.engine.nextLevelUpdate();
    }

    /**
     * getter
     * @return : the current state of the game, id est every Entity or Tile but the walls
     */
    public GameStatement getGameStatement() {
        return gameStatement;
    }

    /**
     * Utilisé à la vue de build les images de fond de chaque level
     * @return List d'ensemble de position des murs de chaque level
     */
    public List<Set<Position>> getAllWallsOfEachLevels() {
        ArrayList<Set<Position>> res = new ArrayList<>();
        Level indiceLevel = level;
        while (  indiceLevel != null ) {
            res.add(indiceLevel.getWallsPosition());
            indiceLevel = indiceLevel.nextLevel();
        }
        return res;
    }

    /**
     * //TODO
     * @param tile
     */
    public void destroyTile (Tile tile) {
        level.destroyTile(tile);
    }
}


