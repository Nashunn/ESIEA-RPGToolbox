 package fr.nashunn.rpg_toolbox.view;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.nashunn.rpg_toolbox.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new PlayerListFragment());
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static List<Integer> randomColor() {
        List result = new ArrayList<Integer>();
        Random rnd = new Random();
        int r, g, b;
        r = rnd.nextInt(256);
        g = rnd.nextInt(256);
        b = rnd.nextInt(256);

        result.add(Color.argb(255, r, g, b));
        result.add(Color.argb(80, r, g, b));

        return result;
    }
}
