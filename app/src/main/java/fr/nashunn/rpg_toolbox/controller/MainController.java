package fr.nashunn.rpg_toolbox.controller;

import android.widget.Toast;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {
    private MainActivity activity;
    private CocktailAPI api;

    public MainControllerAPI(MainActivity activity, CocktailAPI api) {
        this.activity = activity;
        this.api = api;
    }

    public void searchCocktailByName(String name) {
        Call<ResponseAPI> call = api.loadDrinks(name); // Search cocktail by name
        // Put call in async queue
        call.enqueue(
                new Callback<ResponseAPI>() {
                    //On response
                    public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                        ResponseAPI responseAPI = response.body(); // Deserialize response body into objects

                        if(response.isSuccessful()) {
                            List<Drink> drinks = responseAPI.getDrinks();
                            //if drinks is null, create a list
                            if(drinks == null) {
                                drinks = new ArrayList<Drink>();
                            }
                            // send data to cocktail list
                            activity.updateCocktailList(drinks);
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
