package com.example.minesweeper.UI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.minesweeper.R;
import com.example.minesweeper.model.Board;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class PlayGame extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPref";
    public static final int DEFAULT_VALUE = 0;
    public static final String Text = "text";


    private int NUM_OF_ROWS;
    private int NUM_OF_COL;
    private int NUMBER_OF_ZOMBIES;
    private int SCAN_COUNT = 0;
    private int zombiesFound = 0;
    Button[][] buttons = new Button[NUM_OF_ROWS][NUM_OF_COL];
    private List<Button> array = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);


        extractDataFromIntent();
        //setRandomMines(buttons);

        setMinesText();
        saveData();
        setScansText();

        setPlayed();
        //checkMines();
    }
    // Save the number of times game is played
    public void saveData() {
        SharedPreferences sharedPref = this.getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        int played = Main_Menu.returnGamesPlayed();
        editor.putInt( Text , played);
        editor.apply();
    }
    // Set the number of times game is played
    private void setPlayed(){
        int gamesPlayed = Main_Menu.returnGamesPlayed();
        SharedPreferences sharedPreference = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        int gg = sharedPreference.getInt(Text,DEFAULT_VALUE);
        TextView noOfTimesGamePlayed = (TextView)findViewById(R.id.timesPlayed);
        noOfTimesGamePlayed.setText("Times Played: " + gg);
        //Toast.makeText(this, "fhfhfhf", Toast.LENGTH_SHORT).show();
    }

    private void buttonClicked(int row,int col){
        SCAN_COUNT++;
        setScansText();
    }
    // Set number of scans used
    private void setScansText() {
        TextView scansUsed = (TextView)findViewById(R.id.noOfScans);
        scansUsed.setText("Number Of Scans used: " + SCAN_COUNT);
    }

    /**private void countMines(Button[][] b) {
        int count = 0;
        for (int i = 0 ; i < NUM_OF_ROWS;i++){
            for(int j = 0; j<NUM_OF_COL;j++){
                if(buttons[i][j].getText() != "Set"){

                }
            }
        }
    }*/
    // Set random zombies on the field
    private void setRandomZombies(Button[][] table){
        Random rand = new Random();
        int rowRandom;
        int colRandom;
        for (int i = 0; i < NUMBER_OF_ZOMBIES; i++){
            rowRandom = rand.nextInt(NUM_OF_ROWS);
            colRandom = rand.nextInt(NUM_OF_COL);
            if (table[rowRandom][colRandom].getText() != "Set"){
                table[rowRandom][colRandom].setText("Set");
                table[rowRandom][colRandom].setTextColor(0xff0000);
                //array.add(table[rowRandom][colRandom]);
            }
            else {
                i--;
            }
        }
    }
    //
    private void mines(final Button[][] butt){
        final MediaPlayer zombieSound = MediaPlayer.create(this,R.raw.voice );
        for(int i = 0 ; i < NUM_OF_ROWS;i++){
            for(int j = 0; j < NUM_OF_COL;j++){
                if(butt[i][j].getText() == "Set"){
                    final Button b = butt[i][j];
                    //array.add(b);
                    lockButtonSizes();
                    int newWidth = 45;
                    int newHeight = 45;
                    Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.face);
                    final Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                    final Resources resource = getResources();
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            zombieSound.start();
                            setScansText();
                            b.setBackground(new BitmapDrawable(resource, scaledBitmap));
                            if(zombiesFound < NUMBER_OF_ZOMBIES){

                                zombiesFound++;
                                SCAN_COUNT++;
                                setMinesText();

                                if(zombiesFound == NUMBER_OF_ZOMBIES){
                                    // Insert the alert dialogue here boi.
                                    Toast.makeText(PlayGame.this, "All Zombies have been Found. GAME OVER!", Toast.LENGTH_SHORT).show();
                                    for(int m = 0; m < NUM_OF_ROWS;m++){
                                        for(int n = 0 ; n < NUM_OF_COL;n++){
                                            butt[m][n].setText("0");

                                        }
                                    }
                                    FragmentManager manager = getSupportFragmentManager();
                                    MessageFragment dialog = new MessageFragment();
                                    dialog.show(manager,"Message Dialog");
                                    Log.i("TAG", "Just showed the dialog");
                                }
                            }
                        }
                    });

                }
                else
                    {
                        final Button[] bbb = {butt[i][j]};
                        final int finalI = i;
                        final int finalJ = j;
                        //just a normal int had to put it in an array to execute the code
                        final int[] cc = {0};
                        bbb[0].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(SCAN_COUNT < NUM_OF_ROWS*NUM_OF_COL){
                                    SCAN_COUNT++;
                                    setScansText();
                                    //int ccff = numOfMinesLeft(butt,finalI,finalJ);
                                    for(int k = 0; k < NUM_OF_COL;k++){
                                        if(butt[finalI][k].getText() == "Set"){
                                            cc[0]++;}
                                    }
                                    for(int l = 0 ; l < NUM_OF_ROWS;l++){
                                        if(butt[l][finalJ].getText() == "Set"){
                                            cc[0]++;}
                                    }
                                    //String check= bbb[0].getText().toString();
                                    //if(check.matches("(.*)(.*)") ) {
                                    if (bbb[0] == null || bbb[0].getText().toString().isEmpty()) {
                                        bbb[0].setText("" + cc[0]);
                                    }
                                }
                            }
                        });
                    }

            }
        }
    }

    private void setMinesText() {
        TextView noOfMines = (TextView)findViewById(R.id.foundText);
        noOfMines.setText("Found " + zombiesFound + " of " + NUMBER_OF_ZOMBIES + " zombies");
    }


    private void populateCellMines() {
        for (int i = 0 ; i < NUM_OF_ROWS;i++){
            for (int j = 0 ; j < NUM_OF_COL;j++){
                //cells[i][j] = new Board();
            }
        }
    }

    private void populateMines() {
        TableLayout mineLand = (TableLayout)findViewById(R.id.minesMap);
        for (int row = 0 ; row < NUM_OF_ROWS ; row++){
            TableRow tablerow = new TableRow(this);
            tablerow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            mineLand.addView(tablerow);

            for (int col = 0; col < NUM_OF_COL ; col++){
                final int finalRow = row;
                final int finalCol = col;
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));
                //button.setText(""+ int_random +"," + int_random1);
                button.setPadding(0,0,0,0);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //buttonClicked(finalRow,finalCol);
                    }
                });
                tablerow.addView(button);
                buttons[row][col] = button;

            }
        }
    }


    private void lockButtonSizes(){
        for (int row = 0 ; row < NUM_OF_ROWS;row ++){
            for (int col = 0; col < NUM_OF_COL; col++){
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }
    private void congratulate() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Congratulations");

        alertDialogBuilder.setMessage("R.string.congratulations_text")
                .setCancelable(false)
                .setPositiveButton("Yay!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(PlayGame.this, Main_Menu.class);
                        startActivity(intent);
                    }
                });
    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        NUM_OF_ROWS = intent.getIntExtra("Hello",4);
        NUM_OF_COL = intent.getIntExtra("World",6);
        NUMBER_OF_ZOMBIES = intent.getIntExtra("Sir",6);
        buttons = new Button[NUM_OF_ROWS][NUM_OF_COL];
        populateMines();
        setRandomZombies(buttons);
        mines(buttons);
    }

    public static Intent makeLaunchIntent(Context c,int rows,int cols,int zombies){
        Intent intent = new Intent(c, PlayGame.class);
        intent.putExtra("Hello",rows);
        intent.putExtra("World",cols);
        intent.putExtra("Sir",zombies);
        return intent;
    }
}