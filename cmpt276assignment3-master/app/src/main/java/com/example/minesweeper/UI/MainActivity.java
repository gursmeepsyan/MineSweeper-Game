package com.example.minesweeper.UI;

import android.content.Intent;
import android.os.Bundle;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.minesweeper.R;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animationPlay();
        Button skipButton = (Button) findViewById(R.id.skipButton);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainMenu = Main_Menu.makeLaunchIntent(MainActivity.this);
                startActivity(mainMenu);
                //setGames();
            }
        });

    }
    private void animationPlay() {

        TextView title = (TextView) findViewById(R.id.titleGame);
        YoYo.with(Techniques.FadeIn)
                .duration(4000)
                .repeat(0)
                .playOn(title);
        ImageView bombImage = (ImageView) findViewById(R.id.zombie_Face);
        YoYo.with(Techniques.FadeIn)
                .duration(4000)
                .repeat(0)
                .playOn(bombImage);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent MainMenu = Main_Menu.makeLaunchIntent(MainActivity.this);
                startActivity(MainMenu);
            }
        },4000);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}