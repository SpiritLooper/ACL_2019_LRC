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

    private static final int NB_FRAME_MOVE = 3;
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

        while(!game.isFinished()) {

            Command command = gameController.getCommand();

            this.game.execute(command, gameController.getDuration() );

            this.gui.paint();

            Thread.sleep(100);
        }
    }

    /**
     * Mets à jour l'affichage du jeu
     */
    public void update() {
        this.gui.paint();
        gameController.listen();
    }

    public void nextLevelUpdate() {
        gamePainter.updateNextLevel();
    }
}
