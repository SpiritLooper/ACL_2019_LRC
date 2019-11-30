package start;

import model.game.Game;
import view.AudioPlayer;
import view.Engine;

import java.lang.reflect.InvocationTargetException;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		//Création et génération du jeu
		Game game = new Game();
		try {
			game.generateGame();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		// classe qui lance le moteur de jeu generique
		Engine engine = new Engine(game);
		game.setEngine(engine);

		engine.run();
		AudioPlayer.playSoundtrack();
	}

}
