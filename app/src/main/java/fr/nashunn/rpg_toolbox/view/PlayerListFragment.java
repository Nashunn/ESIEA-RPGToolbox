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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.nashunn.rpg_toolbox.R;

public class PlayerListFragment extends Fragment {
    private FloatingActionButton btn_dice;
    // private FloatingActionButton btn_addPlayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        // Set fav button clickable
        //btn_dice = view.findViewById(R.id.btn_addPlayer);
        btn_dice = view.findViewById(R.id.btn_dice);
        // setFLoatBtnClick(this, btn_addPlayer, AddJoueurActivity.class);
        setFLoatBtnClick(this.getContext(), btn_dice, DiceActivity.class);

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
}
