package com.example.conect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*
    Author @Shubham
 */

public class MainActivity extends AppCompatActivity {

    // 0 : yellower , 1: red player
    int activePlayer = 0;   // Game will start from yeloow player

    // It will say the state of game, 2 defines empty place
    // 0 define yellow and 1 defines red
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    // Game is inactive until some has won
    boolean gameActivity = true;

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        counter.setTranslationY(-1500);

        Log.i("tag ",counter.getTag().toString()+" Clicked!");

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameActivity) {

            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(500);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    // Someone has won

                    gameActivity = false;

                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "red";
                    }

                    Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();

                    Button PlayAgainButton = (Button) findViewById(R.id.PlayAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " has won!");
                    winnerTextView.setVisibility(view.VISIBLE);

                    PlayAgainButton.setVisibility(view.VISIBLE);



                }

            }

        }

    }

    // This method help to play again when some has won
     public void PlayAgain(View view) {

        Button PlayAgainButton = (Button) findViewById(R.id.PlayAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        PlayAgainButton.setVisibility(view.INVISIBLE);
        winnerTextView.setVisibility(view.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        
        for(int i=0;i<gridLayout.getChildCount();i++){

            ImageView child = (ImageView) gridLayout.getChildAt(i);

            child.setImageDrawable(null);

        }

        for(int i = 0;i<gameState.length; i++){

            gameState[i] = 2;

        }

        activePlayer = 0;   // Game will start from yeloow player

        gameActivity = true;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}