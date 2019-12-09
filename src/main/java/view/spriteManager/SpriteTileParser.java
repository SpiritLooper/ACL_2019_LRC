package view.spriteManager;

import model.game.Biom;
import model.game.Game;
import model.game.Level;
import view.Painter;
import view.spriteManager.biomManager.*;
import view.spriteManager.sprite.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * Classe principale de la gestion des sprites
 */
public class SpriteTileParser {

    /**
     * Constante
     */
    public static final String TEXTURE_PATH = "textures/";

    /**
     * Ensemble des sprites stockee
     */
    private static BasicSprite treasureSprite;
    private static BasicSprite stairsSprite;
    private static BasicSprite wildRoseSprite;
    private static BasicSprite healSprite;
    private static BasicSprite healOverTimeSprite;

    private final static BiomLevel MURKY_BIOM = new MurkyForest();
    private final static BiomLevel DARK_BIOM = new DarkWasteland();
    private final static BiomLevel STATION_BIOM = new StationPass();
    private final static BiomLevel TEMPORAL_BIOM = new TemporalTower();
    private final static BiomLevel ABYSSE = new Abysse();

    private static ImageLevelLinked imageslevelCurrent;
    private static ImageLevelLinked imageslevelStock;

    /**
     * Charge l'ensemble des sprites
     * @throws IOException Fichiers a charger non existant
     */
    public static void loadSprites() throws IOException {
        treasureSprite = new Treasure();
        stairsSprite = new Stairs();
        wildRoseSprite = new WildRose();
        healSprite = new HealTile();
        healOverTimeSprite = new HealOverTimeTile();
    }

    public static void loadLevels( Game game ) {
        List<Level> levels =  game.getAllWallsOfEachLevels();

        imageslevelCurrent = new ImageLevelLinked(null);

        for ( Level l : levels ) {
            BiomLevel biom;
            switch ( l.getBiomLevel()) {
                case STATION_PASS:
                    biom = STATION_BIOM;
                    break;
                case DARK_WASTLAND:
                    biom = DARK_BIOM;
                    break;
                case TEMPORAL_TOWER:
                    biom = TEMPORAL_BIOM;
                    break;
                case ABYSSE:
                    biom = ABYSSE;
                    break;
                case MURKY_FOREST:
                default:
                    biom = MURKY_BIOM;
                    break;
            }
            imageslevelCurrent.add(  biom.buildImageLevel(l.getWallsPosition()) );
        }

        imageslevelStock = imageslevelCurrent.clone();
    }

    /*
     * Getters
     */

    public static BufferedImage getTreasureSprite() {
        return treasureSprite.getSprite();
    }

    public static BufferedImage getStairsSprite() {
        return stairsSprite.getSprite();
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
        imageslevelCurrent = imageslevelCurrent.next();
        return imageslevelCurrent.getImage();
    }

    public static BufferedImage getHealTileSprite() {
        return healSprite.getSprite();
    }

    public static BufferedImage getOverTimeTileSprite() {
        return healOverTimeSprite.getSprite();
    }

    public static void reloadFirstBackground() {
        imageslevelCurrent = imageslevelStock.clone();
    }

    public static BiomLevel getBiomFromEnum(Biom b) {
        switch ( b) {
            case STATION_PASS:
                return STATION_BIOM;
            case DARK_WASTLAND:
                return DARK_BIOM;
            case TEMPORAL_TOWER:
                return TEMPORAL_BIOM;
            case ABYSSE:
                return ABYSSE;
            case MURKY_FOREST:
            default:
                return MURKY_BIOM;
        }
    }
}
