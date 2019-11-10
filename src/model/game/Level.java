package model.game;

import controller.Command;
import model.LevelDAO;
import model.PositionPool;
import model.SaveDAO;
import model.element.*;

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
     * @return timer of the level
     */
    public Timer getTimer () {
        return timer;
    }

    /**
     * Adds a monster to the level at a given position
     * @param p position to spawn the monster at
     * @param m monster to spawn
     */
    protected void addMonster(Position p, Monster m){
        monsters.put(p, m);
    }

    /**
     * Adds a tile to the level at a given position
     * @param p position to put the tile at
     * @param t tile to put
     */
    protected void addTile(Position p, Tile t) { tiles.put(p, t); }

    /**
     * Removes the monster identified by its position
     * @param p position identifying the monster
     */
    protected void removeMonster(Position p){ monsters.remove(p);}

    /**
     * Removes the tile indentified by its position
     * @param p position identifying the tile
     */
    protected void removeTile(Position p){ tiles.remove(p);}

    /**
     * Generates a default level
     * @return the generated level
     */
    protected Level generateDefaultLevel(){
        /*
          0   1   2
         _____________
      0  |_H_|___|___|
      1  |___|___|_W_|
      2  |___|___|_M_|

         */

        Position p = PositionPool.getInstance().getPosition(2,2);
        addMonster(p, new Zombie());

        //Ajout mur
        p = PositionPool.getInstance().getPosition(2,1);
        addTile(p, new Wall());

        return this;
    }

    /**
     * @return a collection of the monsters' positions
     */
    public Collection<Position> getMonstersPosition() {

        return monsters.keySet();
    }

    /**
     * @return a collection of the tiles' positions
     */
    public Collection<Position> getTilesEventPosition() { //A corriger plus tard, il faudra filtrer les murs et les autres tiles a partir de la hashmap Tiles
        return tiles.keySet();                // c'est juste pour test la vue
    }

    /**
     * @return true if the level contains a treasure, false else
     */
    public boolean hasATreasure() {
        return nextLevel == null;
    }

    /**
     * @return next level
     */
    public Level nextLevel() {
        return nextLevel;
    }

    /**
     * Returns the position of the treasure
     * TODO retirer le instanceof!
     * @return position of the treasure
     */
    public Position getPositionTresor() {
        for (Position p : tiles.keySet()) {
            Tile t = tiles.get(p);
            if(t instanceof Treasure){
                return p;
            }
        }
        return null;
    }

    /**
     * Returns the position of the stairs
     * TODO retirer le instanceof
     * @return
     */
    public Position getPositionStairs() {
        for (Position p : tiles.keySet()) {
            Tile t = tiles.get(p);
            if(t instanceof Stairs){
                return p;
            }
        }
        return null;
    }

    /**
     * Sets the next level
     * @param level next level
     */
    public void setNextLevel(Level level){
        this.nextLevel = level;
    }

    /**
     * Updates the level, firing the event of the hero position if any and updates each monsters using their behaviors
     */
    public void update () {
        //test d'un évènement à déclencher
        if (tiles.containsKey(hero.getPosition()) && tiles.get(hero.getPosition()).hasEvent()) {
            ((EventTile)tiles.get(hero.getPosition())).fireEvent();
        }

        //mise à jour des monstres

        //on récupère les positions avant changement de la hashmap
        List<Position> monstersPositions = new ArrayList<>(monsters.keySet());

        for (Position p : monstersPositions) { //parcours des monstres

            Command monsterCommand = monsters.get(p).behave(); //sauvegarde du comportement du monstre
            Monster m = monsters.get(p); //sauvegarde du monstre
            Position newPosition = p.applyCommand(monsterCommand); //nouvelle position du monstre par rapport à son comportement

            if (isEmpty(newPosition) && hero.getPosition() != newPosition) {
                //si la case est libre ET le hero n'y est pas on se déplace
                monsters.remove(p);
                if(m.getHp() > 0) {//si le monstre n'a plus d'HP alors on le remet dans la HasMap
                    monsters.put(newPosition, m);
                }
            }else if(newPosition.equals(hero.getPosition())){
                m.attack(hero);
                //Debug combat
                System.out.println("Attaque du monstre");
                System.out.println("hero hp:"+hero.getHp());
                System.out.println("monstre hp:" + m.getHp());
            }
        }
        //mise à jour du timer
        timer.tick();
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
            hero.attack(m);
            //Debug combat
            System.out.println("ATTAQUE!");
            System.out.println("hero hp:"+hero.getHp());
            System.out.println("monstre hp:" + m.getHp());
        }
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
     * Loads a save given by the game containing the data of the hero and monsters
     * @param save save DAO containing the data to load
     */
    public void loadSave (SaveDAO save) {
        timer.reset(save.getTimer());
        hero = save.getHero();
        monsters = save.getMonsters();
    }


    /**
     * TODO faire javadoc
     * @return
     */
    public SaveDAO createSave () {
        SaveDAO save = new SaveDAO();
        save.setTimer(timer.getTimeLeft());
        save.setHero(hero.getPosition().getX(), hero.getPosition().getY(), 1, 1);
        for (Position p : monsters.keySet()) {
            save.addMonster(monsters.get(p).getClass().getSimpleName().toUpperCase(), p.getX(), p.getY(), 1, 1);
        }
        System.out.println(save);
        return save;
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
     * @return life of the Hero
     */
    public int heroLife(){
        return hero.getHp();
    }
}
