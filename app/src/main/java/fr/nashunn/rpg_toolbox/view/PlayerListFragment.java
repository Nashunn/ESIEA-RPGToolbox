package fr.nashunn.rpg_toolbox.view;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import fr.nashunn.rpg_toolbox.R;
import fr.nashunn.rpg_toolbox.model.Player;
import fr.nashunn.rpg_toolbox.model.PlayerListAdapter;

public class PlayerListFragment extends Fragment {
    private SharedPreferences sharedPreferences_players;
    public static PlayerListAdapter playerListAdapter;
    public static View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate fragment
        view = inflater.inflate(R.layout.fragment_user_list, container, false);

        // Set float button clickable
        FloatingActionButton btn_addPlayer = view.findViewById(R.id.btn_addPlayer);
        FloatingActionButton btn_dice = view.findViewById(R.id.btn_dice);
        setFLoatBtnClickPopup(this.getContext(), btn_addPlayer);
        setFLoatBtnClickChangeActivity(this.getContext(), btn_dice, DiceActivity.class);

        // Get SharePreference Player in cache
        initPlayerList(getCachePlayers()); // Update list with cache

        return view;
    }

    private void setFLoatBtnClickPopup(final Context context, FloatingActionButton btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog(context);
            }
        });
    }

    private void setFLoatBtnClickChangeActivity(final Context context, FloatingActionButton btn, final Class targetActivity) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, targetActivity);
                context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle()); // With transition
            }
        });
    }

    public static void initPlayerList(List<Player> data) {
        // Initalize a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        playerListAdapter = new PlayerListAdapter(view.getContext(), data);

        // Set view components
        RecyclerView playerRecyclerView = (RecyclerView) view.findViewById(R.id.player_recylerView);
        playerRecyclerView.setLayoutManager(layoutManager);
        playerRecyclerView.setHasFixedSize(true);

        // Specify an adapter
        playerRecyclerView.setAdapter(playerListAdapter);
    }

    public static void updatePlayerList() {
        playerListAdapter.notifyDataSetChanged();
    }

    protected void showInputDialog(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptView = layoutInflater.inflate(R.layout.popup_add_player, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptView);


        // Setup the popup
        final EditText playerName = (EditText) promptView.findViewById(R.id.et_addPlayer_name);
        final List<Integer> colors = MainActivity.randomColor();
        alertDialogBuilder.setCancelable(false)
            // If yes
            .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Do actions to add a player to the list
                    addPlayerActions(
                        new Player(playerName.getText().toString(), 0, colors.get(0), colors.get(1))
                    );
                }
            })
            // If no
            .setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }
            );

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private List<Player> getCachePlayers() {
        List<Player> result = new LinkedList<>();
        sharedPreferences_players = Objects.requireNonNull(this.getContext()).getSharedPreferences(getString(R.string.pref_players), Context.MODE_PRIVATE);
        Map<String, ?> playersList = sharedPreferences_players.getAll();

        for (Map.Entry<String, ?> player : playersList.entrySet()) {
            Type typePlayer = new TypeToken<Player>(){}.getType();
            String strPlayer = player.getValue().toString();

            Player currentPlayer = new Gson().fromJson(strPlayer, typePlayer);
            result.add(currentPlayer);
        }

        return result;
    }

    private void addPlayerActions(Player player) {
        // Add player to cache
        Gson oGson = new Gson();
        String playerStr = oGson.toJson(player);
        sharedPreferences_players.edit()
            .putString(player.getId(), playerStr)
            .apply();

        initPlayerList(getCachePlayers()); // Update list with cache
    }
}
