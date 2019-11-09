package engine.spriteManager;

import engine.spriteManager.basicSprite.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Classe principale de la gestion des sprites
 */
public class SpriteTileParser {

    /**
     * Constante
     */
    public static final String TEXTURE_PATH = "res/textures/";

    /**
     * Ensemble des sprites stockee
     */
    private static BasicSprite treasureSprite;
    private static BasicSprite stairsSprite;
    private static BasicSprite zombieSprite;
    private static BasicSprite wildRoseSprite;
    private static BasicSprite heroSprite;

    /**
     * Charge l'ensemble des sprites
     * @throws IOException Fichiers a charger non existant
     */
    public static void loadSprites() throws IOException {
        treasureSprite = new Treasure();
        stairsSprite = new Stairs();
        zombieSprite = new Zombie();
        wildRoseSprite = new WildRose();
        heroSprite = new Hero();
    }

    /*
     * Getters
     */

    public static BufferedImage getHeroSprite() {
        return heroSprite.getSprite();
    }

    public static BufferedImage getTreasureSprite() {
        return treasureSprite.getSprite();
    }

    public static BufferedImage getStairsSprite() {
        return stairsSprite.getSprite();
    }

    public static BufferedImage getZombieSprite() {
        return zombieSprite.getSprite();
    }

    public static BufferedImage getWildRoseSprite() {
        return wildRoseSprite.getSprite();
    }
}
