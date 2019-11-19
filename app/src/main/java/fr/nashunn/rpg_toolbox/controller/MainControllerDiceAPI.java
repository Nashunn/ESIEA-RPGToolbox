package fr.nashunn.rpg_toolbox.controller;

import fr.nashunn.rpg_toolbox.data.DiceAPI;
import fr.nashunn.rpg_toolbox.view.MainActivity;

public class MainControllerDiceAPI {
    private MainActivity activity;
    private DiceAPI api;

    public MainControllerDiceAPI(MainActivity activity, DiceAPI api) {
        this.activity = activity;
        this.api = api;
    }

    public void getDiceByNumber(String number) {

    }
}
