package model.game;

import model.element.Entity;
import model.element.Position;

import java.util.HashMap;

/**
 * Dans cette version , le level sert à retrouver les différents élements du niveau actuel
 * et dans un soucis de passer les niveaux via des escaliers, un level connait son prochain level
 * @author gouth
 */
public class Level {

    private Level nextLevel;
    private HashMap<Position, Entity> hashmapMonsters;
    public Level(){
        hashmapMonsters = new HashMap<>();
    }

    protected void addEntity(Position p, Entity e){
        hashmapMonsters.put(p, e);
    }

    boolean canHeroMove(Position p) {
        return hashmapMonsters.containsKey(p);
    }
}
