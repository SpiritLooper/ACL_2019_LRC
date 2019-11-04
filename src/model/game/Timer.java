package model.game;

public class Timer {

    private int timeLeft; //temps restant représenté sous forme de déplacements

    public Timer () {
        this.timeLeft = 30;
    }

    public Timer (int timeLeft) {
        this.timeLeft = timeLeft;
    }

    // Décrémente le temps restant et le renvoie
    public int tick () {
        return --timeLeft;
    }

    // retourne le temps restant
    public int getTimeLeft() {
        return timeLeft;
    }
}
