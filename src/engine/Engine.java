package engine;

/**
 * Classe permettant de faire un rendu graphique du jeu
 * @author Horatiu Cirstea, Vincent Thomas, Julien Claisse
 */
import controller.Controller;
import model.game.Game;

public class Engine {

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
    public void run() {
        this.gui = new GUI(this.gamePainter,this.gameController);
        update();
    }

    /**
     * Mets à jour l'affichage du jeu
     */
    public void update() {
        // affiche le game
        this.gui.paint();
        gameController.listen();
    }
}
