package model.game;

import controller.Command;
import engine.Engine;
import model.GameParser;
import model.Menu;
import model.PositionPool;
import model.SaveDAO;
import model.element.Position;
import model.element.Stairs;
import model.element.Treasure;

import java.io.IOException;
import java.util.Collection;

/**
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
     * Constructor instantiating a timer and setting the game to not finished
     */
    public Game(){
        this.menu = new Menu();
        this.level = null;
        this.engine = null;
        this.finished = false;
        this.won = false;
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
     * @return a collection of the position of each monsters
     */
    public Collection<Position> getMonstersPosition() {
        return level.getMonstersPosition();
    }

    /**
     * @return a collection of the position of each walls
     */
    public Collection<Position> getWallsPosition() { //TODO : a corriger il faut choisir les murs seulement
        return level.getTilesEventPosition();
    }

    /**
     * @return a collection of the position of each event tiles
     */
    public Collection<Position> getEventTilesPosition() {
        return level.getTilesEventPosition();
    }

    /**
     * @return position of the hero
     */
    public Position getHeroPosition () {
        return level.getHero().getPosition();
    }

    /**
     * @return position of the treasure in the level
     */
    public Position getTreasurePosition() {
        return level.getPositionTresor();
    }

    /**
     * @return position of the set of stairs in the level
     */
    public Position getStairsPosition() {
        return level.getPositionStairs();
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
                    break;

                case LOAD:
                    loadSave();
                    menu.close();
                    break;

                case EXIT:
                    finish(false);
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

    private void saveGame () {
        try {
            GameParser.getINSTANCE().writeSaveFile(level.createSave());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSave () {
        try {
            SaveDAO save = GameParser.getINSTANCE().parseSaveFile();
            level.loadSave(save);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        if (level.getTimer().getTimeLeft()<= 0) {
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
        level = level.nextLevel();
    }

    /**
     * Generates a basic game
     */
    public void generateGame() {
        //Génération par défaut
        Level level1 = new Level();
        level1 = level1.generateDefaultLevel();

        //Ajout stairs
        Position p = PositionPool.getInstance().getPosition(4,4);
        level1.addTile(p, new Stairs(this));

        Level level2 = new Level();
        level2 = level2.generateDefaultLevel();

        //Ajout Tresor
        p = PositionPool.getInstance().getPosition(5,4);
        level2.addTile(p, new Treasure(this));


        level1.setNextLevel(level2);

        this.setLevel(level1);//on bind la game au level
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
        this.engine.update();
    }

}


