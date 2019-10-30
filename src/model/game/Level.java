package model.game;

import model.PoolPosition;
import model.element.*;

import java.util.Collection;
import java.util.HashMap;

/**
 * Dans cette version , le level sert à retrouver les différents élements du niveau actuel
 * et dans un soucis de passer les niveaux via des escaliers, un level connait son prochain level
 * @author gouth
 */
public class Level {

    private Level nextLevel;
    private HashMap<Position, Entity> hashmapMonsters;
    private HashMap<Position, Tile> hashMapTile;

    public Level(){
        hashmapMonsters = new HashMap<>();
        hashMapTile = new HashMap<>();
    }

    /**Fonction qui ajoute une entité au level
     *
     * @param p position où placer l'entité dans le level
     * @param e entité à ajouter
     */
    protected void addEntity(Position p, Entity e){
        hashmapMonsters.put(p, e);
    }

    /**Fonction qui ajoute une tuile au niveau
     *
     * @param p position où placer la tuile dans le level
     * @param t tuile à ajouter
     */
    protected void addTile(Position p, Tile t) { hashMapTile.put(p, t); }

    /**Fonction qui supprime une entité se trouvant à la position donnée en paramêtre
     *
     * @param p
     */
    protected void removeEntity(Position p){ hashmapMonsters.remove(p);}

    /**Fonction qui supprime une tuile se trouvant à la position donnée en paramêtre
     *
     * @param p
     */
    protected void removeTile(Position p){ hashMapTile.remove(p);}

    /**Fonction booléen qui retourne vrai si le héro peut se déplacer à la position donnée en paramêtre, faux sinonv
     *
     * @param p
     * @return
     */
    public boolean canHeroMove(Position p) {
        boolean res = true;
        if(hashmapMonsters.containsKey(p)) //si y'a un monstre à l'emplacement on se déplace pas
            res = false;
        if(hashMapTile.containsKey(p)){ //s'il y a une tuile
            Tile t = hashMapTile.get(p);
            if(!t.isWalkable()){//si la tuile n'est pas marchable
                res = false;
            }
        }
        return res;
    }

    protected Level generateDefaultLevel(Game g){
        /*
          0   1   2
         _____________
      0  |_H_|___|___|
      1  |___|___|_W_|
      2  |___|___|_M_|

         */

        Position p = PoolPosition.getInstance().getPosition(2,2);
        addEntity(p, new Zombie(p));

        //Ajout mur
        p = PoolPosition.getInstance().getPosition(2,1);
        addTile(p, new Tile(p,false) {});

        return this;
    }

    /**
     * Permet d'obtenir l'ensmble des positions des monstres
     * @return
     */
    public Collection<Position> getMonstersPosition() {
        return hashmapMonsters.keySet();
    }

    /**
     * Permet d'obtenir l'ensemble des positions des murs
     * @return positions de chaque tiles filtrees par murs
     */
    public Collection<Position> getTilesEventPosition() { //A corriger plus tard, il faudra filtrer les murs et les autres tiles a partir de la hashmap Tiles
        return hashMapTile.keySet();                // c'est juste pour test la vue
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
        for (Position p : hashMapTile.keySet()) {
            Tile t = hashMapTile.get(p);
            if(t instanceof Tresure){
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
        for (Position p : hashMapTile.keySet()) {
            Tile t = hashMapTile.get(p);
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

    /**
     * Active l'évenement de case de position p
     * @param p
     */
    public void fireEventTile(Position p) {
        if(hashMapTile.containsKey(p)){
            Tile t = hashMapTile.get(p);
            if(t instanceof EventTile){
                ((EventTile)t).fireEnvent();
            }
        }
    }
}
