package main.java.model.persistency;

import main.java.model.element.entities.Monster;
import main.java.model.element.Position;

import java.io.*;

/**
 * todo finir le chargement d'un game et de TOUS ses levels
 * The game parser is used to write and read game files, i.e saves the game and loads the game
 */
public class GameParser {

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
        sb.append("HERO:" + save.getHero().getPosition().getX() + "," + save.getHero().getPosition().getY() + "," + 1 + "," + 1 + "\n");
        for (Position p : save.getMonsters().keySet()) {
            Monster m = save.getMonsters().get(p);
            sb.append("MONSTER:" + m.getClass().getSimpleName().toUpperCase() + "," + p.getX() + "," + p.getY() + "," + 1 + "," + 1 + "\n");
        }

        writer.write(sb.toString());

        writer.close();
    }

    /**
     * Parses the save file to return a save DAO object
     * @return save DAO object
     * @throws IOException
     */
    public SaveDAO parseSaveFile () throws IOException {
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

                save.setHero(Integer.parseInt(hero[0]), Integer.parseInt(hero[1]), Integer.parseInt(hero[2]), Integer.parseInt(hero[3]));
            } else if (line.startsWith("MONSTER:")) {
                String[] monster = line.substring(8).split(",");

                save.addMonster(monster[0], Integer.parseInt(monster[1]), Integer.parseInt(monster[2]), Integer.parseInt(monster[3]), Integer.parseInt(monster[4]));
            }
        }

        return save;
    }

    /**
     * todo pis on changera en dynamique sur les noms de monstres
     * parse un fichier levelX.lyt pour générer un niveau dans le modèle
     * @param lvl : l'indice du niveau (1,2,3...N)
     * @return LevelDAO qui va servir à generer le niveau depuis le fichier
     */
    public LevelDAO parseLevelFile(int lvl) throws IOException{
        LevelDAO level = new LevelDAO();
        File file = new File(LEVEL_FILENAME+lvl+EXTENSION);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("DIM:")) {
                String[] dimensions = line.substring(4).split(",");
                int w = Integer.parseInt(dimensions[0]);
                int h = Integer.parseInt(dimensions[1]);
                level.setDimension(w,h);

            } else if (line.startsWith("HERO:")) {
                String[] hero = line.substring(5).split(",");

                level.setHeroPosition(Integer.parseInt(hero[0]), Integer.parseInt(hero[1]));
            } else if (line.startsWith("ZOMBIE:")) {
                String[] zombies = line.substring(7).split(";");
                for(String position : zombies){
                    String[] tmp = position.split(",");
                    level.addBasicMonster(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
                }
            } else if (line.startsWith("WILDROSE:")) {
                String[] roses = line.substring(9).split(";");
                for(String position : roses){
                    String[] tmp = position.split(",");
                    level.addImmovableMonster(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
                }
            } else if (line.startsWith("TREASURE:")){
                String[] position = line.substring(9).split(",");
                level.addTreasure(Integer.parseInt(position[0]), Integer.parseInt(position[1]));
            } else if (line.startsWith("STAIRS:")) {
                String[] position = line.substring(7).split(",");
                level.addStairs(Integer.parseInt(position[0]), Integer.parseInt(position[1]));

            } else if (line.startsWith("HEAL:")) {

                String[] heals = line.substring(5).split(";");
                for (String position : heals) {
                    String[] tmp = position.split(",");
                    level.addHeal(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
                }

            } else if (line.startsWith("HEALOVERTIME:")){

                String[] healsOverTime = line.substring(13).split(";");
                for (String position : healsOverTime) {
                    String[] tmp = position.split(",");
                    level.addHealOverTime(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
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
