package model.game;

import model.element.*;
import model.element.entities.Hero;
import model.element.entities.ImmovableMonster;
import model.element.entities.BasicMonster;
import model.element.entities.Monster;
import model.element.tiles.Stairs;
import model.element.tiles.Treasure;
import model.element.tiles.Wall;
import model.element.tiles.buffTiles.HealOverTimeTile;
import model.element.tiles.buffTiles.HealTile;

import java.util.*;

/**
 * Instance de l'etat actuel du jeu
 * Utilise par la vue pour dessiner correctement les sprites
 */
public class GameStatement {

    /**
     * Constantes
     */
    public static final String HERO = Hero.class.getSimpleName();
    public static final String STAIRS = Stairs.class.getSimpleName();
    public static final String TREASURE = Treasure.class.getSimpleName();
    public static final String WILD_ROSE = ImmovableMonster.class.getSimpleName();
    public static final String ZOMBIE = BasicMonster.class.getSimpleName();
    public static final String WALL = Wall.class.getSimpleName() ;
    public static final String HEAL = HealTile.class.getSimpleName();
    public static final String HEALOVERTIME = HealOverTimeTile.class.getSimpleName();
    /**
     * Stockage des elements
     */
    private HashMap<String, Set<Position>> elements;
    private Map<Position, Monster> monsters;
    private Hero hero;

    public GameStatement () {
        elements = new HashMap<>();

        elements.put(HERO, new HashSet<>());
        elements.put(STAIRS, new HashSet<>());
        elements.put(TREASURE, new HashSet<>());
        elements.put(WALL, new HashSet<>());
        elements.put(WILD_ROSE, new HashSet<>());
        elements.put(ZOMBIE, new HashSet<>());
        elements.put(HEAL, new HashSet<>());
        elements.put(HEALOVERTIME, new HashSet<>());
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

    public void setMonster(Map<Position, Monster> monsters) {
        this.monsters = monsters;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Hero getHeroStatement() {
        return hero;
    }

    public  Monster getMonster(Position p){
        return this.monsters.get(p);
    }
}
