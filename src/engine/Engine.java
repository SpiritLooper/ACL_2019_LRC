package engine;

/**
 * Classe permettant de faire un rendu graphique du jeu
 * @author Horatiu Cirstea, Vincent Thomas, Julien Claisse
 */
public class Engine {

    //Modele du jeu
    private Game game;

    //Rendu de l'image
    private Painter gamePainter;

    //Controller du jeu
    private GameController gameController;

    //Interface graphique contenant l'image de rendu
    private GUI gui;

    /**
     * construit le moteur graphique
     *
     * @param game
     *            game a lancer
     * @param gamePainter
     *            afficheur a utiliser
     * @param gameController
     *            controlleur a utiliser
     *
     */
    public Engine(Game game, Painter gamePainter, GameController gameController) {
        this.game = game;
        this.gamePainter = gamePainter;
        this.gameController = gameController;
    }

    /**
     * permet de lancer le game
     */
    public void run() throws InterruptedException {

        // creation de l'interface graphique
        this.gui = new GUI(this.gamePainter,this.gameController);

        // boucle de game
        while (!this.game.isFinished()) {
            // demande controle utilisateur
            Cmd c = this.gameController.getCommand();
            // fait evoluer le game
            this.game.evolve(c);
            // affiche le game
            this.gui.paint();
            // met en attente
            Thread.sleep(100);
        }
    }
}
