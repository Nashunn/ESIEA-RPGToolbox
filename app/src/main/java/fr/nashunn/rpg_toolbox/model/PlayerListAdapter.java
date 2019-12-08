package fr.nashunn.rpg_toolbox.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.nashunn.rpg_toolbox.R;

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

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        // Get the drink at indicated position in the dataset
        final Player currentPlayer = dataset.get(position);
        holder.setCurrentPlayer(currentPlayer);

        //Picasso.get().load(currentPlayer.getIcon()).into(holder.iv_image);
        holder.tv_playerName.setText("Player");
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
