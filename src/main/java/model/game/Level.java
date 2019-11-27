package model.game;

import controller.Command;
import model.element.entities.Entity;
import model.element.tiles.buffTiles.BuffTile;
import model.persistency.LevelDAO;
import model.PositionPool;
import model.persistency.SaveDAO;
import model.element.*;
import model.element.entities.Hero;
import model.element.entities.Monster;
import model.element.entities.BasicMonster;
import model.element.tiles.*;
import view.AudioPlayer;

import java.util.*;

/**
 * The Level contains all of its tiles and monsters, its next level (if any) as well as the hero
 * @author gouth
 */
public class Level {

    /**
     * next level (using stairs)
     */
    private Level nextLevel;

    /**
     * Map of the monsters identified by their positions
     */
    private Map<Position, Monster> monsters;

    /**
     * Map of the tiles identified by their positions
     */
    private Map<Position, Tile> tiles;

    /**
     * hero contained in the level
     */
    private Hero hero;

    /**
     * timer of the level
     */
    private Timer timer;

    /**
     * Constructor instantiating the maps and the hero to the default position
     */
    public Level(){
        monsters = new HashMap<>();
        tiles = new HashMap<>();

        hero = new Hero(PositionPool.getInstance().getPosition(0, 0));
        timer = new Timer();
    }

    /**
     * @return hero
     */
    public Hero getHero () {
        return hero;
    }

    /**
     * @return health points of the hero
     */
    public int getHeroHp(){
        return hero.getHp();
    }

    /**
     * Sets the health points of the hero
     * @param hp health points
     */
    public void setHeroHp(int hp){
        hero.setHp(hp);
    }

    /**
     * Moves the hero given a command
     * @param command command representing the move to make
     */
    public void moveHero (Command command) {
        Position newPosition = hero.getPosition().applyCommand(command);
        if (isEmpty(newPosition)) {//si c'est libre
            hero.setPosition(newPosition);
        }else if( monsters.containsKey(newPosition)){//si un monstre s'y trouve
            Monster m = monsters.get(newPosition);
            combat(hero,m);
            //hero.attack(m);
            //Debug combat
            //System.out.println("ATTAQUE!");
            //System.out.println("hero hp:"+hero.getHp());
            //System.out.println("monstre hp:" + m.getHp());

            if(m.getHp() <= 0){
                removeMonster(newPosition);
            }
        }
    }

    void combat(Entity e1, Entity e2){
        e1.attack(e2);
        e2.attack(e1);
    }

    /**
     * @return timer of the level
     */
    public Timer getTimer () {
        return timer;
    }

    /**
     * @return true if the level contains a treasure, false else
     */
    public boolean hasATreasure() {
        return nextLevel == null;
    }

    /**
     * Checks if a position is empty and enables someone to walk onto it
     * @param p position to check
     * @return true if someone can walk onto it, false else
     */
    private boolean isEmpty (Position p) {
        return (!tiles.containsKey(p) || (tiles.containsKey(p) && tiles.get(p).isWalkable())) && !monsters.containsKey(p);
    }

    /**
     * Adds a monster to the level at a given position
     * @param p position to spawn the monster at
     * @param m monster to spawn
     */
    public void addMonster(Position p, Monster m){
        monsters.put(p, m);
    }

    /**
     * TODO
     * @param m
     * @return
     */
    public boolean containsMonster(Monster m){
        return monsters.containsValue(m);
    }

    /**
     * Removes the monster identified by its position
     * @param p position identifying the monster
     */
    public void removeMonster(Position p){ monsters.remove(p);}

    /**
     * Adds a tile to the level at a given position
     * @param p position to put the tile at
     * @param t tile to put
     */
    public void addTile(Position p, Tile t) { tiles.put(p, t); }

    /**
     * TODO
     * @param t
     * @return
     */
    public boolean constainsTile(Tile t){
        return tiles.containsValue(t);
    }

    /**
     * Destroys a given tile from the map of tiles
     * @param tile tile to remove
     */
    public void destroyTile (Tile tile) {
        //copie des positions de la hashmap
        List<Position> tilesPositions = new ArrayList<>(tiles.keySet());

        //parcours des positions copiées
        for (Position p : tilesPositions) {
            //si la position p contient la tile à supprimer
            if (tiles.get(p).equals(tile)) {
                tiles.remove(p); //suppression de la tile
                return;
            }
        }
    }

    /**
     * Permet d'obtenir la postion de chaque mur du niveau
     * @return Ensemble de position
     */
    public Set<Position> getWallsPosition() {
        Set<Position> res = new HashSet<>();
        for (Position p : tiles.keySet() ) {
            Tile t = tiles.get(p);
            if (t.getClass().getName().equals(Wall.class.getName())) {
                res.add(p);
            }
        }
        return res;
    }

    /**
     * generate a level from a LevelDAO
     * @param lvl ; LevelDAO which was read from a file
     */
    public void generate(LevelDAO lvl) {
        this.monsters = lvl.getMonsters();
        this.tiles = lvl.getTiles();
        this.hero = lvl.getHero();
    }

    /**
     * Generates a default level
     * @return the generated level
     */
    protected Level generateDefaultLevel() {

        Position p = PositionPool.getInstance().getPosition(2, 2);
        addMonster(p, new BasicMonster());

        //Ajout mur
        p = PositionPool.getInstance().getPosition(2, 1);
        addTile(p, new Wall());

        return this;
    }

    /**
     * @return next level
     */
    public Level nextLevel() {
        return nextLevel;
    }

    /**
     * Sets the next level
     * @param level next level
     */
    public void setNextLevel(Level level){
        this.nextLevel = level;
    }

    /**
     * Updates the level, thus updating the hero and monsters
     */
    public void update () {
        //mise à jour du héro
        updateHero();

        //mise à jour des monstres
        updateMonsters();

        //mise à jour du timer
        timer.tick();
    }

    /**
     * Updates the hero
     */
    private void updateHero() {
        //test d'un évènement à déclencher
        if (tiles.containsKey(hero.getPosition())) {
            //si la tile où se trouve le héro contient un évènement
            if (tiles.get(hero.getPosition()).hasEvent()) {

                //déclenchement de l'évènement
                ((EventTile)tiles.get(hero.getPosition())).fireEvent();

            //sinon si la tile où se trouve le héro contient un buff
            } else if (tiles.get(hero.getPosition()).hasBuff()) {

                //déclenchement du buff
                ((BuffTile)tiles.get(hero.getPosition())).buff(hero);

            }
        }

        //mise à jour du héro
        hero.update();
    }

    /**
     * Updates the monsters of the level
     */
    private void updateMonsters () {
        //on récupère les positions avant changement de la hashmap
        List<Position> monstersPositions = new ArrayList<>(monsters.keySet());

        //parcours des positions des monstres
        for (Position p : monstersPositions) {

            //mise à jour du monstre à la position donnée
            updateMonsterAtPosition(p);

        }
    }

    /**
     * Updates a monster at a given position
     * @param p position referencing the monster
     */
    private void updateMonsterAtPosition (Position p) {
        //comportement voulu du monstre
        Command monsterCommand = monsters.get(p).behave();

        //retenue du monstre
        Monster m = monsters.get(p);

        //nouvelle position du monstre par rapport à son comportement
        Position newPosition = p.applyCommand(monsterCommand);

        //si la case cible est libre ET le hero n'y est pas on se déplace
        if (isEmpty(newPosition) && hero.getPosition() != newPosition) {

            monsters.remove(p);
            monsters.put(newPosition, m);

            //si la case cible contient un buff
            if (tiles.containsKey(newPosition) && tiles.get(newPosition).hasBuff()) {
                //application du buff au monstre
                ((BuffTile) tiles.get(newPosition)).buff(m);
            }

        //si la case cible est occupée par le héro
        } else if (newPosition.equals(hero.getPosition())) {

            //le monstre attaque le hero
            m.attack(hero);

            //Debug combat
            //System.out.println("Attaque du monstre");
            //System.out.println("hero hp:" + hero.getHp());
            //System.out.println("monstre hp:" + m.getHp());

            //si le monstre n'a plus de hp
            if (m.getHp() <= 0) {
                //System.out.println("===DEBUG==remove monster : =" + m.getHp());
                //on supprime le monstre de la map -> il meurt
                removeMonster(newPosition);
            }

        }

        //mise à jour du monstre
        m.update();
    }

    /**
     * Met a jour l'etat du jeu a renvoyer a la vue
     * @param gs objet stockant les etats du jeu
     */
    public void updateGameStatement(GameStatement gs) {
        // Parcours des monstres
        for(Position p : monsters.keySet()) {
            Monster m = monsters.get(p);
            gs.addElement(m.getClass().getSimpleName(), p);
        }

        //Parcours des tuiles
        for(Position p : tiles.keySet()) {
            Tile t = tiles.get(p);
            gs.addElement(t.getClass().getSimpleName(), p);
        }

        //Ajout position de l'hero*
        gs.addElement(hero.getClass().getSimpleName(), hero.getPosition());

    }

    /**
     * Loads a save given by the game containing the data of the hero and monsters
     * @param save save DAO containing the data to load
     */
    public void loadSave (SaveDAO save) {
        timer.reset(save.getTimer());
        hero = save.getHero();
        monsters = save.getMonsters();
    }

    /**
     * Creates a save DAO object regarding the current state of the game
     * @return save DAO object
     */
    public SaveDAO createSave () {
        //création de la sauvegarde
        SaveDAO save = new SaveDAO();

        //sauvegarde du timer
        save.setTimer(timer.getTimeLeft());

        //sauvegarde du héro
        save.setHero(hero.getPosition().getX(), hero.getPosition().getY(), hero.getHp(), hero.getAtk());

        //sauvegarde des monstres
        for (Position p : monsters.keySet()) {
            Monster m = monsters.get(p);
            save.addMonster(m.getClass().getSimpleName().toUpperCase(), p.getX(), p.getY(), m.getHp(), m.getAtk());
        }

        return save;
    }

    public void rotateHero(Command command) {
        hero.rotate(command);
    }
}
