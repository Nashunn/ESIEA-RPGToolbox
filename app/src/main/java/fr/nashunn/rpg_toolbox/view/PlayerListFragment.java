package fr.nashunn.rpg_toolbox.view;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.nashunn.rpg_toolbox.R;
import fr.nashunn.rpg_toolbox.model.Player;
import fr.nashunn.rpg_toolbox.model.PlayerListAdapter;

public class PlayerListFragment extends Fragment {
    private FloatingActionButton btn_dice;
    private View view;
    // private FloatingActionButton btn_addPlayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_list, container, false);

        // Set fav button clickable
        //btn_dice = view.findViewById(R.id.btn_addPlayer);
        btn_dice = view.findViewById(R.id.btn_dice);
        // setFLoatBtnClick(this, btn_addPlayer, AddJoueurActivity.class);
        setFLoatBtnClick(this.getContext(), btn_dice, DiceActivity.class);

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player());
        players.add(new Player());
        updatePlayerList(players);

        return view;
    }

    private void setFLoatBtnClick(final Context context, FloatingActionButton btn, final Class targetActivity) {
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, targetActivity);
                context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle()); // With transition
            }
        });
    }

    public void updatePlayerList(List<Player> data) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        PlayerListAdapter cocktailListAdapter = new PlayerListAdapter(this.getContext(), data);
        // Initalize a linear layout manager

        // Set view components
        RecyclerView cocktailRecyclerView = (RecyclerView) view.findViewById(R.id.player_recylerView);
        cocktailRecyclerView.setLayoutManager(layoutManager);
        cocktailRecyclerView.setHasFixedSize(true);


        // specify an adapter (see also next example)

        cocktailRecyclerView.setAdapter(cocktailListAdapter);
    }
}
