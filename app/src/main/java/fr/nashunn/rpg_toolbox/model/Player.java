package fr.nashunn.rpg_toolbox.model;

import java.util.UUID;

public class Player {
    private String id;
    private String name;
    private int score;

    public Player() {
        this.id = generateId();
        this.name = "Player";
        this.score = 0;
    }

    public Player(String name) {
        this.id = generateId();
        this.name = name;
        this.score = 0;
    }

    public Player(String name, int score) {
        this.id = generateId();
        this.name = name;
        this.score = score;
    }

    // Id
    private String generateId() {
        return Integer.toString(UUID.randomUUID().hashCode());
    }

    public String getId() {
        return id;
    }

    // Name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Score
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
}
