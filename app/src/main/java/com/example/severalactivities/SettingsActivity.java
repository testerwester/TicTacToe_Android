package com.example.severalactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    private Button ButtonSubmit;
    private Button ButtonDefault;
    private TextInputEditText textInput1;
    private TextInputEditText textInput2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        textInput1 = findViewById(R.id.textPlayer1Input);
        textInput2 = findViewById(R.id.textPlayer2Input);

        ButtonSubmit = findViewById(R.id.submitButton);
        ButtonSubmit.setOnClickListener(this);
        ButtonDefault = findViewById(R.id.defaultButton);
        ButtonDefault.setOnClickListener(this);
    }

    public boolean checkText(TextInputEditText textObject, String input){
        if(input.length() < 1 || input.length() > 10) {
            textObject.setError("Name must be between 1 and 10 characters.");
            return false;
        } else return true;
    }

    @Override
    public void onClick(View v) {
        String id = (String)v.getTag();
        switch(id){
            case "Submit":
                String tmpText1 = textInput1.getEditableText().toString();
                String tmpText2 = textInput2.getEditableText().toString();
                if(checkText(textInput1, tmpText1) && checkText(textInput2, tmpText2)){
                    setResult(Activity.RESULT_OK, new Intent().putExtra("Player1Name", tmpText1).putExtra("Player2Name", tmpText2));
                    finish();
                }
                break;

            case "Default":
                setResult(Activity.RESULT_CANCELED);
                finish();
                break;
        }


    }
}
