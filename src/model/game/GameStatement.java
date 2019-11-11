package model.game;

import model.element.*;
import model.element.entities.Hero;
import model.element.entities.WildRose;
import model.element.entities.Zombie;
import model.element.tiles.Stairs;
import model.element.tiles.Treasure;
import model.element.tiles.Wall;

import java.util.*;

/**
 * Instance de letat actuel du jeu
 * Utilise par la vue pour dessiner correctement les sprites
 */
public class GameStatement {

    /**
     * Constantes
     */
    public static final String HERO = Hero.class.getSimpleName();
    public static final String STAIRS = Stairs.class.getSimpleName();
    public static final String TREASURE = Treasure.class.getSimpleName();
    public static final String WILD_ROSE = WildRose.class.getSimpleName();
    public static final String ZOMBIE = Zombie.class.getSimpleName();
    public static final String WALL = Wall.class.getSimpleName() ;
    /**
     * Stockage des elements
     */
    private HashMap<String, Set<Position>> elements;

    public GameStatement () {
        elements = new HashMap<>();

        elements.put(HERO, new HashSet<>());
        elements.put(STAIRS, new HashSet<>());
        elements.put(TREASURE, new HashSet<>());
        elements.put(WALL, new HashSet<>());
        elements.put(WILD_ROSE, new HashSet<>());
        elements.put(ZOMBIE, new HashSet<>());
    }

    /**
     * Ajoute une position
     * @param elementType type d'entite a ajouter
     * @param p position de l'entite
     */
    public void addElement(String elementType, Position p){
       if(!elements.containsKey(elementType))
           elements.put(elementType, new HashSet<>());

       elements.get(elementType).add(p);
    }

    /**
     * Donne le premier element de l'ensemble de position
     * @param element Element desire
     * @return Position de l element
     */
    public Position getFirstPosition(String element) {
        Iterator<Position> iterator = elements.get(element).iterator();
        return iterator.next();
    }

    /**
     * Retourne l ensemble des positions
     * @param element element desire
     * @return l ensemble de position
     */
    public Collection<Position> getAllPosition(String element) {
        return  elements.get(element);
    }

    /**
     * Indique si le level contient un tresor
     * @return vrai si un tresor est dispo
     */
    public boolean hasATresure() {
        return elements.containsKey(TREASURE) && !elements.get(TREASURE).isEmpty();
    }

    /**
     * Efface les positions de chaque ensemble
     */
    public void clear() {
        for (Set<Position> set : elements.values())
            set.clear();
    }
}
