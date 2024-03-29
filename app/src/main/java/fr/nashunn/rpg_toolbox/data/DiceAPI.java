package fr.nashunn.rpg_toolbox.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DiceAPI {
    @GET("json/{dice_nb}")
    Call<ResponseAPI> launchDice(@Path(value = "dice_nb", encoded = true) String dice_nb);
}
