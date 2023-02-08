package ws2022.Client.Model;

/**
 * The class that models a player in your game.
 * <p>
 *  The class has a few methods: addScore(), which adds a score to the player's total; 
 *  getScore(), which returns the current score; 
 *  and addScore(int score), which adds a given amount to the player's total score. 
 * <p>
 * 
 * @author
 */

public class Player {
    private String name;
    private Integer age;
    private int score = 0;

    public Player(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void addScore() {
        score++;
    }

    public void addScore(int score) {
        this.score = score;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

}
