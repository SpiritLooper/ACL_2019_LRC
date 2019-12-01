package model.persistency;

import model.element.entities.Monster;
import model.element.Position;
import model.element.entities.buffs.Buff;
import model.element.tiles.buffTiles.BuffTile;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * The game parser is used to write and read game files, i.e saves the game and loads the game
 */
public class GameParser {

    /**
     * package path to the entities package
     */
    public static final String ENTITY_PATH = "model.element.entities.";

    /**
     * package path to the tiles package
     */
    public static final String TILE_PATH = "model.element.tiles.";

    /**
     * package path to the buffTiles package
     */
    public static final String BUFF_TILE_PATH = "model.element.tiles.buffTiles.";

    /**
     * package path to the buffs package
     */
    public static final String BUFF_PATH = "model.element.entities.buffs.";

    /**
     * static instance of the singleton
     */
    private static GameParser INSTANCE = new GameParser();

    /**
     * extension of the levels layout files
     */
    private final String EXTENSION      = ".lyt";

    /**
     * filename of the game layout file
     */
    private final String GAME_FILENAME  = "res/saves/game"+EXTENSION;

    /**
     * filename of the save file
     */
    private final String SAVE_FILENAME  = "res/saves/save.sav";

    /**
     * filename of a level layout file without its number and extension
     */
    private final String LEVEL_FILENAME = "res/saves/level";

    /**
     * @return static instance of the singleton
     */
    public static GameParser getINSTANCE () {
        return INSTANCE;
    }

    /**
     * Writes to the save file the current state of the game regarding the hero
     * @param save save DAO object to save
     * @throws IOException
     */
    public void writeSaveFile (SaveDAO save) throws IOException {
        File file = new File(SAVE_FILENAME);
        FileWriter writer = new FileWriter(file);

        StringBuilder sb = new StringBuilder();

        sb.append("TIMER:" + save.getTimer() + "\n");
        sb.append("HERO:" + save.getHero().getPosition().getX() + "," + save.getHero().getPosition().getY() + "," + save.getHero().getHp() + "," + save.getHero().getAtk() + ",");
        for (Buff b : save.getHero().getBuffs()) {
            sb.append(b.getClass().getSimpleName() + "," + b.getDuration());
        }
        sb.append("\n");
        for (Position p : save.getMonsters().keySet()) {
            Monster m = save.getMonsters().get(p);
            sb.append("MONSTER:" + m.getClass().getSimpleName() + "," + p.getX() + "," + p.getY() + "," + m.getHp() + "," + m.getAtk() + ",");
            for (Buff b : save.getMonsters().get(p).getBuffs()) {
                sb.append(b.getClass().getSimpleName() + "," + b.getDuration());
            }
            sb.append("\n");
        }
        for (Position p : save.getBuffTiles().keySet()) {
            BuffTile t = save.getBuffTiles().get(p);
            sb.append("BUFF_TILE:" + t.getClass().getSimpleName() + "," + p.getX() + "," + p.getY() + "\n");
        }

        writer.write(sb.toString());

        writer.close();
    }

    /**
     * Parses the save file to return a save DAO object
     * @return save DAO object
     * @throws IOException
     */
    public SaveDAO parseSaveFile () throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        SaveDAO save = new SaveDAO();
        File file = new File(SAVE_FILENAME);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("TIMER:")) {
                String timer = line.substring(6);

                save.setTimer(Integer.parseInt(timer));
            } else if (line.startsWith("HERO:")) {
                String[] hero = line.substring(5).split(",");
                String[] buffs = {};
                if (hero.length > 4) {
                    buffs = Arrays.copyOfRange(hero, 4, hero.length);
                    //System.out.print("Hero buffs : " + buffs[0] + " : " + hero[4] + "\n");
                }

                save.setHero(Integer.parseInt(hero[0]), Integer.parseInt(hero[1]), Integer.parseInt(hero[2]), Integer.parseInt(hero[3]), buffs);
            } else if (line.startsWith("MONSTER:")) {
                String[] monster = line.substring(8).split(",");
                String[] buffs = {};
                if (monster.length > 5) {
                    buffs = Arrays.copyOfRange(monster, 5, monster.length-1);
                }

                save.addMonster(monster[0], Integer.parseInt(monster[1]), Integer.parseInt(monster[2]), Integer.parseInt(monster[3]), Integer.parseInt(monster[4]), buffs);
            } else if (line.startsWith("BUFF_TILE:")) {
                String[] tile = line.substring(10).split(",");

                save.addBuffTile(tile[0], Integer.parseInt(tile[1]), Integer.parseInt(tile[2]));
            }
        }

        return save;
    }

    /**
     * parse un fichier levelX.lyt pour générer un niveau dans le modèle
     * @param lvl : l'indice du niveau (1,2,3...N)
     * @return LevelDAO qui va servir à generer le niveau depuis le fichier
     */
    public LevelDAO parseLevelFile(int lvl) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        LevelDAO level = new LevelDAO();
        File file = new File(LEVEL_FILENAME+lvl+EXTENSION);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("HERO:")) {
                String[] hero = line.substring(5).split(",");

                level.setHeroPosition(Integer.parseInt(hero[0]), Integer.parseInt(hero[1]));
            } else if (line.startsWith("MONSTER:")) {
                String[] monster = line.substring(8).split(":");

                for (String m : monster[1].split(";")) {
                    String[] pos = m.split(",");
                    level.addMonster(monster[0], Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
                }
            } else if (line.startsWith("TILE:")){
                String[] tile = line.substring(5).split(":");

                for (String t : tile[1].split(";")) {
                    String[] pos = t.split(",");
                    level.addTile(tile[0], Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
                }

            } else if (line.startsWith("BUFF_TILE:")){
                String[] buffTile = line.substring(10).split(":");

                for (String t : buffTile[1].split(";")) {
                    String[] pos = t.split(",");
                    level.addBuffTile(buffTile[0], Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
                }

            } else if (line.startsWith("WALLS:")){
                String[] walls = line.substring(6).split(";");

                for(String wall : walls){
                    if(wall.contains("to")){ //logique point HG ; BD
                        int HGx;
                        int HGy;
                        int BDx;
                        int BDy;

                        String[] bounds = wall.split("to");
                        String[] HG = bounds[0].split(",");
                        String[] BD = bounds[1].split(",");

                        HGx = Integer.parseInt(HG[0]);
                        HGy = Integer.parseInt(HG[1]);
                        BDx = Integer.parseInt(BD[0]);
                        BDy = Integer.parseInt(BD[1]);

                        for(int i = HGx; i <= BDx; i++){
                            for(int j = HGy; j <= BDy; j++){
                                level.addWall(i,j);
                            }
                        }
                    } else { //just one position
                        String[] tmp = wall.split(",");
                        level.addWall(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
                    }
                }//end for
            }//end if...else..if

        }//end while
        return level;
    }

    /**
     * Parse the first line of game.lyt in order to retrieve the information to the Game class
     * @return : the amount of level in the game
     */
    public int getNbLevel() {
        int nbLevel = 0;
        try{
            File file = new File(GAME_FILENAME);
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            String line;
            while ((line = buffer.readLine()) != null) {
                if (line.startsWith("LEVELS:")) {
                    nbLevel = Integer.parseInt(line.substring(7));
                    break; //moche mais efficace ça evite de parser le reste
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return nbLevel;
    }
}
