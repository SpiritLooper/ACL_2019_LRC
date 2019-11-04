package model.game;

import controller.Command;
import model.PoolPosition;
import model.element.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Dans cette version , le level sert à retrouver les différents élements du niveau actuel
 * et dans un soucis de passer les niveaux via des escaliers, un level connait son prochain level
 * @author gouth
 */
public class Level {

    private Level nextLevel;
    private Map<Position, Monster> monsters;
    private Map<Position, Tile> tiles;
    private Hero hero;

    public Level(){
        monsters = new HashMap<>();
        tiles = new HashMap<>();

        hero = new Hero(PoolPosition.getInstance().getPosition(0, 0));
    }

    /**Fonction qui ajoute une entité au level
     *
     * @param p position où placer l'entité dans le level
     * @param m entité à ajouter
     */
    protected void addMonster(Position p, Monster m){
        monsters.put(p, m);
    }

    /**Fonction qui ajoute une tuile au niveau
     *
     * @param p position où placer la tuile dans le level
     * @param t tuile à ajouter
     */
    protected void addTile(Position p, Tile t) { tiles.put(p, t); }

    /**Fonction qui supprime une entité se trouvant à la position donnée en paramêtre
     *
     * @param p
     */
    protected void removeEntity(Position p){ monsters.remove(p);}

    /**Fonction qui supprime une tuile se trouvant à la position donnée en paramêtre
     *
     * @param p
     */
    protected void removeTile(Position p){ tiles.remove(p);}

    protected Level generateDefaultLevel(Game g){
        /*
          0   1   2
         _____________
      0  |_H_|___|___|
      1  |___|___|_W_|
      2  |___|___|_M_|

         */

        Position p = PoolPosition.getInstance().getPosition(2,2);
        addMonster(p, new Zombie());

        //Ajout mur
        p = PoolPosition.getInstance().getPosition(2,1);
        addTile(p, new Tile(false,false, p) {});

        return this;
    }

    /**
     * Permet d'obtenir l'ensmble des positions des monstres
     * @return
     */
    public Collection<Position> getMonstersPosition() {
        return monsters.keySet();
    }

    /**
     * Permet d'obtenir l'ensemble des positions des murs
     * @return positions de chaque tiles filtrees par murs
     */
    public Collection<Position> getTilesEventPosition() { //A corriger plus tard, il faudra filtrer les murs et les autres tiles a partir de la hashmap Tiles
        return tiles.keySet();                // c'est juste pour test la vue
    }

    public boolean hasATresor() {
        return nextLevel == null;
    }

    public Level nextLevel() {
        return nextLevel;
    }

    /**
     * Permet de trouver la position du trésor du niveau
     * @return null si pas de trésor
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
     * Permet de trouver la position des escaliers du niveau
     * @return null si pas d'escaliers
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
     * Défini le prochain niveau du level
     * @param l
     */
    public void setNextLevel(Level l){
        this.nextLevel = l;
    }

    public void update () {
        //test d'un évènement à déclencher
        if (tiles.containsKey(hero.getPosition()) && tiles.get(hero.getPosition()).hasEvent()) {
            ((EventTile)tiles.get(hero.getPosition())).fireEvent();
        }

        //mise à jour des monstres
        Set<Position> monstersPositions = monsters.keySet(); //on récupère les positions avant changement de la hashmap

        for (Position p : monstersPositions) { //parcours des monstres
            Command monsterCommand = monsters.get(p).behave(); //sauvegarde de la position
            Monster m = monsters.get(p); //sauvegarde du monstre
            Position newPosition = p.applyCommand(monsterCommand); //nouvelle position du monstre
            if (newPosition.isInBounds() && isEmpty(newPosition) && hero.getPosition() != newPosition) {
                monsters.remove(p);
                monsters.put(newPosition, m);
            }
        }
    }

    private boolean isEmpty (Position p) {
        return (!tiles.containsKey(p) || (tiles.containsKey(p) && tiles.get(p).isWalkable())) && !monsters.containsKey(p);
    }

    public void moveHero (Command command) {
        Position newPosition = hero.getPosition().applyCommand(command);
        if (newPosition.isInBounds() && isEmpty(newPosition)) {
            hero.setPosition(newPosition);
        }
    }

    public Hero getHero () {
        return hero;
    }

}
