package main.java.view.spriteManager;

import main.java.view.Painter;
import main.java.view.spriteManager.basicSprite.*;
import main.java.view.spriteManager.biomManager.*;
import main.java.model.element.Position;
import main.java.model.game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Set;

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
    private static BasicSprite healSprite;
    private static BasicSprite healOverTimeSprite;

private final static BiomLevel DEFAULT_BIOM = new MurkyForest();

    private static ImageLevelLinked imagesLevel;

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
        healSprite = new HealTile();
        healOverTimeSprite = new HealOverTimeTile();
    }

    public static void loadLevels( Game game ) {
        // TODO Ajouter au main.java.model la lecture du type de biome
        List<Set<Position>> levels =  game.getAllWallsOfEachLevels();

        imagesLevel = new ImageLevelLinked(null);

        for ( Set<Position> ens : levels ) {
            imagesLevel.add(  DEFAULT_BIOM.buildImageLevel(ens) );
        }
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
    }

    public static BufferedImage nextLevel() {
        imagesLevel = imagesLevel.next();
        return imagesLevel.getImage();
    }

    public static BufferedImage getHealTileSprite() {
        return healSprite.getSprite();
    }

    public static BufferedImage getOverTimeTileSprite() {
        return healOverTimeSprite.getSprite();
    }
}
