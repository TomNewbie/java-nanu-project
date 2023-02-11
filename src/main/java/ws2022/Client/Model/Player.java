package ws2022.Client.Model;

/**
 * The class that models a player in your game.
 * 
 * The class has a few methods: addScore(), which adds a score
 * to the player's total; getScore(), which returns the current score;
 * and addScore(int score), which adds a given amount
 * to the player's total score.
 */

public class Player {
    /**
     * The name of the player.
     */
    private String name;

    /**
     * The age of the player.
     */
    private Integer age;

    /**
     * The score of the player.
     */
    private int score = 0;

    /**
     * Constructs a new player with the given name and age.
     * 
     * @param name the name of the player
     * @param age  the age of the player
     */
    public Player(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Returns the current score of the player.
     * 
     * @return the current score of the player
     */
    public int getScore() {
        return score;
    }

    /**
     * Adds 1 point to the player's score.
     */
    public void addScore() {
        score++;
    }

    /**
     * Adds the given number of points to the player's score.
     * 
     * @param score the number of points to add to the player's score
     */
    public void addScore(int score) {
        this.score = score;
    }

    /**
     * Returns the name of the player.
     * 
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the age of the player.
     * 
     * @return the age of the player
     */
    public int getAge() {
        return this.age;
    }
}
