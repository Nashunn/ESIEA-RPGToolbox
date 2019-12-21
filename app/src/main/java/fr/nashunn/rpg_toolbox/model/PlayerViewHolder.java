package fr.nashunn.rpg_toolbox.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fr.nashunn.rpg_toolbox.R;
import fr.nashunn.rpg_toolbox.view.PlayerListFragment;


public class PlayerViewHolder extends RecyclerView.ViewHolder {
    private final Context context;
    public View view;
    public TextView tv_playerName;
    public ImageView iv_deletePlayer;
    public ImageView iv_playerAvatar;
    public ImageView iv_resetPlayer;
    public ImageView iv_scoreMinus;
    public ImageView iv_scorePlus;
    public EditText et_playerScore;
    private Player currentPlayer;
    private SharedPreferences sharedPreferences_players;

    public PlayerViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext(); // Get context to do the onClick
        view = itemView;
        tv_playerName = view.findViewById(R.id.tv_playerName);
        iv_deletePlayer = view.findViewById(R.id.iv_deletePlayer);
        iv_playerAvatar = view.findViewById(R.id.iv_playerAvatar);
        iv_resetPlayer = view.findViewById(R.id.iv_resetPlayer);
        iv_scoreMinus = view.findViewById(R.id.iv_scoreMinus);
        iv_scorePlus = view.findViewById(R.id.iv_scorePlus);
        et_playerScore = view.findViewById(R.id.et_playerScore);

        sharedPreferences_players = context.getSharedPreferences(context.getString(R.string.pref_players), Context.MODE_PRIVATE);

        //Setting onClick on button
        onClickCounterActionsPlayer(iv_scoreMinus, false);
        onClickCounterActionsPlayer(iv_scorePlus, true);
        onClickDeletePlayer();
        onClickRefreshScore();
        OnChangeScore();
    }

    // Set a listener on the + ou - of the counter
    private void onClickCounterActionsPlayer(ImageView btn, final boolean isPlus) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPlus)
                    currentPlayer.setScore(currentPlayer.getScore() + 1);
                else
                    currentPlayer.setScore(currentPlayer.getScore() - 1);

                updatePlayerData();
            }
        });
    }

    // Set a listener on the refresh button
    private void onClickRefreshScore() {
        iv_resetPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPlayer.setScore(0);
                updatePlayerData();
            }
        });
    }

    // Set a listener on the delete button
    private void onClickDeletePlayer() {
        iv_deletePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences_players.edit()
                    .remove(currentPlayer.getId())
                    .apply();

                PlayerListFragment.initPlayerList(getCachePlayers());
            }
        });
    }

    private void OnChangeScore() {
        et_playerScore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentPlayer.setScore(Integer.parseInt(s.toString()));
                //updatePlayerData();
            }
        });
    }


    // Update player data
    public void updatePlayerData() {
        // Update
        Gson oGson = new Gson();
        String playerStr = oGson.toJson(currentPlayer);
        sharedPreferences_players.edit()
            .putString(currentPlayer.getId(), playerStr)
            .apply();

        PlayerListFragment.updatePlayerList();
    }

    private List<Player> getCachePlayers() {
        List<Player> result = new LinkedList<>();
        Map<String, ?> playersList = sharedPreferences_players.getAll();

        for (Map.Entry<String, ?> player : playersList.entrySet()) {
            Type typePlayer = new TypeToken<Player>(){}.getType();
            String strPlayer = player.getValue().toString();

            Player currentPlayer = new Gson().fromJson(strPlayer, typePlayer);
            result.add(currentPlayer);
        }

        return result;
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }
}
