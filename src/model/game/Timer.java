package model.game;

public class Timer {

    /**
     * Default time to use
     */
    private final int DEFAULT_TIME = 30;

    /**
     * Remaining time (rounds) until the game finishes
     */
    private int timeLeft;

    /**
     * Constructor with default time (30)
     */
    public Timer () {
        this.timeLeft = DEFAULT_TIME;
    }

    /**
     * Constructor with a set time
     * @param timeLeft time until the game finishes
     */
    public Timer (int timeLeft) {
        this.timeLeft = timeLeft;
    }

    /**
     * @return time left before the galme finishes
     */
    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     * Decreases the remaining time and returns it
     * @return decreased time
     */
    public int tick () {
        return --timeLeft;
    }

    /**
     * Resets the timer to the default time
     */
    public void reset () {
        timeLeft = DEFAULT_TIME;
    }

    /**
     * Resets the timer to a given time
     * @param time time to reset the timer to
     */
    public void reset (int time) {
        timeLeft = time;
    }

}