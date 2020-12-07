package com.example.minesweeper.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.minesweeper.R;

public class Help_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help__screen);

        Button returnToMainMenu = (Button)findViewById(R.id.returnMainMenu);
        returnToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public static Intent makeLaunchIntent(Context c){
        Intent intent = new Intent(c, Help_Screen.class);
        return intent;
    }
}