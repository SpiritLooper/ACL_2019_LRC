package engine.spriteManager;

import engine.Painter;
import engine.spriteManager.basicSprite.*;
import engine.spriteManager.biomManager.BiomLevel;
import engine.spriteManager.biomManager.NicoDark;

import java.awt.*;
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

    private final static BiomLevel levelDesign = new NicoDark();

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

    public static BiomLevel getLevelDesign() {
        return levelDesign;
    }

    /**
     * Redimmensionne l'image pour qu'elle corresponde à la taille de monde
     * @param bi image a redimmensionner
     * @return img redimensionnee
     */
    public static BufferedImage resizeBufferedImageAsWorldUnit(BufferedImage bi){
        Image tmp = bi.getScaledInstance(Painter.WORLD_UNIT, Painter.WORLD_UNIT, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(Painter.WORLD_UNIT, Painter.WORLD_UNIT, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
        //return bi;
    }
}
