package start;

import engine.Engine;
import model.game.Game;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		Game game = new Game(10,10);

		// classe qui lance le moteur de jeu generique
		Engine engine = new Engine(game);
		engine.run();
	}

}
