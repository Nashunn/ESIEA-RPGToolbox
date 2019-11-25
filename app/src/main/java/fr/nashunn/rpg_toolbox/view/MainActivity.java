 package fr.nashunn.rpg_toolbox.view;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import fr.nashunn.rpg_toolbox.R;
import fr.nashunn.rpg_toolbox.model.Dice;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton btn_dice;
    // private FloatingActionButton btn_addPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set fav button clickable
        //btn_dice = findViewById(R.id.btn_addPlayer);
        btn_dice = findViewById(R.id.btn_dice);
        // setFLoatBtnClick(this, btn_addPlayer, AddJoueurActivity.class);
        setFLoatBtnClick(this, btn_dice, DiceActivity.class);
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
