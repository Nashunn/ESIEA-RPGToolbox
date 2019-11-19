package fr.nashunn.rpg_toolbox.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingletonAPI {
    static final String BASE_URL = "http://roll.diceapi.com/";
    private static DiceAPI instanceAPI;

    public static DiceAPI getInstance() {
        // If instanceAPI doesn't exist, create it before using it
        if(instanceAPI == null){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            // Create the instanceAPI of DiceAPI
            instanceAPI = retrofit.create(DiceAPI.class);
        }
        // Set instanceAPI of api
        return instanceAPI;
    }
}
