package fr.nashunn.rpg_toolbox.model;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import fr.nashunn.rpg_toolbox.R;

public class PlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
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

        // Setting onClick
        itemView.setClickable(true);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO
        Toast.makeText(context, "Click on player : "+currentPlayer, Toast.LENGTH_LONG).show();
        // Intent intent = new Intent(context, PlayerDescriptionActivity.class);
        // intent.putExtra("Player", currentPlayer);
        // context.startActivity(intent);
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }
}
