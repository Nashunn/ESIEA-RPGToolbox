package fr.nashunn.rpg_toolbox.model;

import android.graphics.Color;

import java.util.UUID;

public class Player {
    private String id;
    private String name;
    private int score;
    private int colorPrimary;
    private int colorSecondary;

    public Player(String name) {
        this.id = generateId();
        this.name = name;
        this.score = 0;
    }

    public Player(String name, int score, Integer color1, Integer color2) {
        this.id = generateId();
        this.name = name;
        this.score = score;
        this.colorPrimary = color1;
        this.colorSecondary = color2;
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

    // Color primary
    public int getColorPrimary() {
        return colorPrimary;
    }
    public void setColorPrimary(int colorPrimary) {
        this.colorPrimary = colorPrimary;
    }

    // Color secondary
    public int getColorSecondary() {
        return colorSecondary;
    }
    public void setColorSecondary(int colorSecondary) {
        this.colorSecondary = colorSecondary;
    }
}
