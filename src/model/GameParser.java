package model;

import model.element.Monster;
import model.element.Position;

import java.io.*;

public class GameParser {

    private static GameParser INSTANCE = new GameParser();

    private final String GAME_FILENAME = "res/saves/game.lyt";
    private final String SAVE_FILENAME = "res/saves/save.sav";
    private String[] levelsFilenames;

    private GameParser() {

    }

    public static GameParser getINSTANCE () {
        return INSTANCE;
    }

    public void writeSaveFile (SaveDAO save) throws IOException {
        File file = new File("res/saves/temp.sav");
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

}
