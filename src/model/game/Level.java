package model.game;

import model.PoolPosition;
import model.element.Entity;
import model.element.Position;
import model.element.Tile;
import model.element.Zombie;

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

    protected void addEntity(Position p, Entity e){
        hashmapMonsters.put(p, e);
    }
    protected void addTile(Position p, Tile t) { hashMapTile.put(p, t); }
    protected void removeEntity(Position p){ hashmapMonsters.remove(p);}
    protected void removeTile(Position p){ hashMapTile.remove(p);}

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

    protected void generateDefaultLevel(){
        /*
          0   1   2
         _____________
      0  |_H_|___|___|
      1  |___|___|___|
      2  |___|___|_M_|

         */

        Position p = PoolPosition.getInstance().getPosition(2,2);
        addEntity(p, new Zombie(p));

    }

}
