package ws2022.Client.Model;

import java.time.LocalDate;

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

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

}
