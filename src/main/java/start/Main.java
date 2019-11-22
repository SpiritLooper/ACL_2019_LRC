package start;

import view.AudioPlayer;
import view.Engine;
import model.game.Game;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		//Création et génération du jeu
		Game game = new Game();
		game.generateGame();

		// classe qui lance le moteur de jeu generique
		Engine engine = new Engine(game);
		game.setEngine(engine);

		engine.run();
		AudioPlayer.playSoundtrack();
	}

}
