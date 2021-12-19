package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // player representation
    // 0-X
    // 1-o
    int activePlayer = 0;
    int count=0;
    boolean gameactive=true;
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    //  State meanings
    // 0-x
    //1-o
    //2-Null

    int [][] winPositions={{0,1,2},{3,4,5},{6,7,8},
                           {0,3,6},{1,4,7},{2,5,8},
                           {0,4,8},{2,4,6}};


    public void Playertap(View view) {
        ImageView img = (ImageView) view;
        int TappedImage = Integer.parseInt((img.getTag().toString()));
        if (!gameactive) {

            ResetGame(view);
        }
        if (gameState[TappedImage] == 2)
        {
            count++;
            gameState[TappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn-Tap to Play");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn-Tap to Play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        // Check if any player has won

        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                // somebody has won -Find out who
                String winnerStr;
                gameactive= false;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "X has won";
                }
                else
                {
                    winnerStr = "O has won";
                }
                //Update status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);

            }
        }

        if(count==9 && gameactive)
        {
            TextView status = findViewById(R.id.status);
            status.setText("Game is Draw");
            gameactive = false;
        }
    }
        public void ResetGame(View view)
        {
            gameactive=true;
            count=0;
            activePlayer=0;
            //  int[] gameState = {2,2,2,2,2,2,2,2,2,}; or
            for(int i=0;i<gameState.length; i++)
            {
                gameState[i]=2;
            }
            ((ImageView)findViewById(R.id.imageView17)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView18)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView19)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView13)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView12)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView11)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView14)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView15)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView16)).setImageResource(0);

            TextView status=findViewById(R.id.status);
            status.setText("X's Turn-Tap to play");


        }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}