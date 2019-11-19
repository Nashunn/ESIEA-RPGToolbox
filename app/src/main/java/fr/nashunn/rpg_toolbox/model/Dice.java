package fr.nashunn.rpg_toolbox.model;

import java.io.Serializable;

public class Dice implements Serializable {
    private int value;
    private String type;

    // Type
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    // Value
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
