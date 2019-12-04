package fr.nashunn.rpg_toolbox.view;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import fr.nashunn.rpg_toolbox.R;
import fr.nashunn.rpg_toolbox.controller.MainControllerDiceAPI;
import fr.nashunn.rpg_toolbox.data.SingletonAPI;
import fr.nashunn.rpg_toolbox.model.Dice;
import fr.nashunn.rpg_toolbox.model.ShakeDetector;
import fr.nashunn.rpg_toolbox.model.ShakeService;

public class DiceActivity extends AppCompatActivity {
    private MainControllerDiceAPI controller;
    private TextView diceValue;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private DrawDice diceView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        diceValue = findViewById(R.id.diceValue);
        diceView = findViewById(R.id.diceView);
        diceView.setElevation((float) 0.5);

        // Set controller
        controller = new MainControllerDiceAPI(this, SingletonAPI.getInstance());
        // Init ShakeDetector
        InitShakeDetector();
    }

    private void InitShakeDetector() {
        Intent intent = new Intent(this, ShakeService.class);
        //Start Service
        startService(intent);
        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            // When the device is shook, do the following actions
            @Override
            public void onShake(int count) {
                diceView.invalidate();
                diceView.init();
                loadDiceValue();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        controller.getDiceByNumber("d6");
                    }
                }, 2000);
            }
        });
    }

    public void updateDiceNumber(List<Dice> dice) {
        diceValue = findViewById(R.id.diceValue);
        diceValue.setText(String.valueOf(dice.get(0).getValue()));
    }


    private void loadDiceValue() {
        diceValue.setText("");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                diceValue.setText(".");
            }
        }, 500);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                diceValue.setText("..");
            }
        }, 1000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                diceValue.setText("...");
            }
        }, 1500);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
