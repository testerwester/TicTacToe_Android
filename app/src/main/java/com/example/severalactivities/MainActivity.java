package com.example.severalactivities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonPlay;
    private Button buttonSettings;

    private String player1Name = "Player 1";
    private String player2Name = "Player 2";

    private static int REQUEST_GET_MAP_LOCATION  = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPlay = findViewById(R.id.buttonStartGame);
        buttonSettings = findViewById(R.id.buttonSettings);


        buttonPlay.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                newGame();
            }
        });


        buttonSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent settingsIntent = new Intent(view.getContext(), SettingsActivity.class);
                startActivityForResult(settingsIntent, REQUEST_GET_MAP_LOCATION);
            }
        });

        //Adding a small change here


    }

    public void newGame(){
        Intent gameIntent = new Intent(this, GameActivity.class);
        gameIntent.putExtra("Player1Name", player1Name);
        gameIntent.putExtra("Player2Name", player2Name);
        startActivity(gameIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == REQUEST_GET_MAP_LOCATION && resultCode == Activity.RESULT_OK){
            player1Name = data.getStringExtra("Player1Name");
            player2Name = data.getStringExtra("Player2Name");
        } else newGame();
    }
}
