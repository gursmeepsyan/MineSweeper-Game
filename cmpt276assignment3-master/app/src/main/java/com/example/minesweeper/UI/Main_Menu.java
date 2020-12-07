package com.example.minesweeper.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.minesweeper.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Main_Menu extends AppCompatActivity {

    static private int played = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu);


        Button playGame = (Button) findViewById(R.id.playGameButton);
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                played++;
                Intent launchGame = PlayGame.makeLaunchIntent(Main_Menu.this,Options_Menu.getNumRows(Main_Menu.this),Options_Menu.getNumCols(Main_Menu.this),Options_Menu.getNumMine(Main_Menu.this));
                startActivity(launchGame);
            }
        });

        Button options = (Button) findViewById(R.id.optionsButton);
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent optionsScreen = Options_Menu.makeLaunchIntent(Main_Menu.this);
                startActivity(optionsScreen);
            }
        });

        Button help = (Button) findViewById(R.id.helpButton);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent helpScreen = Help_Screen.makeLaunchIntent(Main_Menu.this);
                startActivity(helpScreen);
            }
        });

    }


    static public int returnGamesPlayed(){
        return played;
    }
    public static Intent makeLaunchIntent(Context c){
        Intent intent = new Intent(c, Main_Menu.class);
        return intent;
    }
}