package fr.nashunn.rpg_toolbox.model;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.nashunn.rpg_toolbox.R;
import fr.nashunn.rpg_toolbox.view.MainActivity;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerViewHolder> {
    private List<Player> dataset;
    private Context context;

    public PlayerListAdapter(Context context, List<Player> playerList) {
        this.dataset = playerList;
        this.context = context;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create a new view item
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.player_row_layout, viewGroup, false);

        return new PlayerViewHolder(v);
    }

    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        // Get the player at indicated position in the dataset
        final Player currentPlayer = dataset.get(position);
        holder.setCurrentPlayer(currentPlayer);

        // Avatar : Get color and prepare avatar
        ImageViewCompat.setImageTintList(holder.iv_playerAvatar, ColorStateList.valueOf(currentPlayer.getColorPrimary()));
        // EditText Score : Change color and set score
        holder.et_playerScore.setBackgroundColor(currentPlayer.getColorSecondary());
        holder.et_playerScore.setText(Integer.toString(currentPlayer.getScore()));

        // Set player information
        holder.tv_playerName.setText(currentPlayer.getName());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
