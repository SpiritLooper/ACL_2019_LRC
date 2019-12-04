package view;

/**
 * Classe permettant de faire un rendu graphique du jeu
 * @author Horatiu Cirstea, Vincent Thomas, Julien Claisse
 */
import controller.Command;
import controller.Controller;
import model.game.Game;

import java.lang.reflect.InvocationTargetException;

public class Engine {

    public static final int NB_FRAME_MOVE = 6;
    //Modele du jeu
    private Game game;

    //Rendu de l'image
    private Painter gamePainter;

    //Controller du jeu
    private Controller gameController;

    //Interface graphique contenant l'image de rendu
    private GUI gui;

    /**
     * construit le moteur graphique
     *
     * @param game
     *            game a lancer et a afficher
     *
     */
    public Engine(Game game) {
        this.game = game;
        this.gamePainter = new Painter(game);
        this.gameController = new Controller(game);
    }

    /**
     * Créer l'interface graphique
     */
    public void run() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, InterruptedException {
        this.gui = new GUI(this.gamePainter,this.gameController);
        AudioPlayer.playSoundtrack();
        game.saveGame();

        while(!game.isFinished()) {

            Command command = gameController.getCommand();

            this.game.execute(command);

            if(     (command == Command.UP
                    || command == Command.DOWN
                    || command == Command.RIGHT
                    || command == Command.LEFT  ) && !game.isMenuOpen()) {
                for(int i = 0 ; i < NB_FRAME_MOVE ; i++) {
                    this.gui.paint(i);
                    Thread.sleep(75);
                }
                this.gamePainter.cleanCache();
            }

            this.gui.paint(-1);

            Thread.sleep(100);
        }
    }

    public void nextLevelUpdate() {
        gamePainter.updateNextLevel();
    }
}
