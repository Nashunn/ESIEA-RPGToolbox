package fr.nashunn.rpg_toolbox.controller;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.nashunn.rpg_toolbox.data.DiceAPI;
import fr.nashunn.rpg_toolbox.data.ResponseAPI;
import fr.nashunn.rpg_toolbox.model.Dice;
import fr.nashunn.rpg_toolbox.view.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainControllerDiceAPI extends AppCompatActivity {
    private MainActivity activity;
    private DiceAPI api;

    public MainControllerDiceAPI(MainActivity activity, DiceAPI api) {
        this.activity = activity;
        this.api = api;
    }

    public void getDiceByNumber(String number) {
        Call<ResponseAPI> call = api.launchDice(number); // Launch a dice by its number of face
        // Put call in async queue
        call.enqueue(
            new Callback<ResponseAPI>() {
                //On response
                public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                    ResponseAPI responseAPI = response.body(); // Deserialize response body into objects;

                    if (response.isSuccessful()) {
                        List<Dice> diceList = responseAPI.getDice();
                        //if drinks is null, create a list
                        if (diceList == null) {
                            diceList = new ArrayList<Dice>();
                        }

                    } else {
                        Toast.makeText(activity.getBaseContext(), "ERROR : API did not respond successfully", Toast.LENGTH_LONG).show();
                        System.out.println(response.errorBody());
                    }
                }

                // On failure
                public void onFailure(Call<ResponseAPI> call, Throwable t) {
                    Toast.makeText(activity.getBaseContext(), "ERROR : API not reachable", Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            }
        );
    }
}