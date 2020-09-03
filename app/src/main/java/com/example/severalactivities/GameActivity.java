package com.example.severalactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import static android.widget.Toast.LENGTH_LONG;

public class GameActivity extends AppCompatActivity implements View.OnClickListener, Observer {

    private TextView Player1TextFrame;
    private TextView Player2TextFrame;

    private Button resetGame;
    private Button settings;
    private Button[] buttons;

    private BoxContainer model;

    private static int REQUEST_GET_MAP_LOCATION  = 0;
    private static final int MAX_GRID = 9;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        model = new BoxContainer();
        model.addObserver(this);
        buttons = new Button[MAX_GRID];

        Player1TextFrame = findViewById(R.id.player1Text);
        Player2TextFrame = findViewById(R.id.player2Text);

        Player1TextFrame.setText(getIntent().getStringExtra("Player1Name") + " X");
        Player2TextFrame.setText(getIntent().getStringExtra("Player2Name") + " O");

        resetGame = findViewById(R.id.buttonResetGame);
        settings = findViewById(R.id.buttonSettings);

        for(i = 0; i<MAX_GRID; i++){
            String buttonID = "button" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resID);
            buttons[i].setOnClickListener(this);
            buttons[i].setTag(i);
        }

        //ActionListener for reset game button
        resetGame.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                model.resetGame();
            }
        });
        //Actionlistener for settings button
        settings.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent settingsIntent = new Intent(view.getContext(), SettingsActivity.class);
                startActivityForResult(settingsIntent, REQUEST_GET_MAP_LOCATION);
            }
        });
    }


    //ActionListener for grid buttons
    @Override
    public void onClick(View v) {
        int id = (int)v.getTag();
        model.setSign(id);
    }

    @Override
    public void update(Observable o, Object arg) {
        String winString = "";
        if(model.isReset()){
            if(model.isWin()){
                if(model.getWinner() == 'X'){
                    winString = Player1TextFrame.getText() + " won this round";
                } else {
                    winString = Player2TextFrame.getText() + " won this round";
                }
                Toast.makeText(getApplicationContext(), winString, LENGTH_LONG).show();
            } else if(model.isDraw()){
                Toast.makeText(getApplicationContext(), "Round ended in draw", LENGTH_LONG).show();
            } else if(!model.isDraw() && !model.isWin()){
                Toast.makeText(getApplicationContext(), "Game has been reset", LENGTH_LONG).show();
            }
        }


        for(i=0; i<MAX_GRID; i++){
            Box tmpBox = model.getBox(i);

            if(tmpBox.isFilled()){
                buttons[i].setText(Character.toString(tmpBox.getSign()));
            } else {
                buttons[i].setText("");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == REQUEST_GET_MAP_LOCATION && resultCode == Activity.RESULT_OK){
            Player1TextFrame.setText(data.getStringExtra("Player1Name"));
            Player2TextFrame.setText(data.getStringExtra("Player2Name"));
            model.resetGame();
        }
    }
}
