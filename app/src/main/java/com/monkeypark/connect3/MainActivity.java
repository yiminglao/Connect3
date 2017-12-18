package com.monkeypark.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow  1 = red
    int activePlayer = 0;
    Boolean isGameActive = true;
    //2 means unplayed
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPosition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        Boolean isGameOver = true;
        for (int counterState : gameState)
        {
            if(counterState == 2)
                isGameOver = false;
        }
        if(isGameOver)
        {
            TextView message = (TextView) findViewById(R.id.winnerMessage);
            message.setText("It's a draw");
            LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
            layout.setVisibility(View.VISIBLE);
        }
        
        if(gameState[tappedCounter] == 2 && isGameActive)
        {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if(activePlayer == 0)
            {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }
            else
            {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).setDuration(300);

            for (int[] winningPositions : winningPosition)
            {
                if(gameState[winningPositions[0]] == gameState[winningPositions[1]]
                        && gameState[winningPositions[1]] == gameState[winningPositions[2]]
                        && gameState[winningPositions[0]] != 2)
                {
                    isGameActive = false;
                    String winner = "Red";
                    if(gameState[winningPositions[0]] == 0)
                    {
                        winner = "Yellow";
                    }
                    TextView message = (TextView) findViewById(R.id.winnerMessage);
                    message.setText(winner + " has won!!!");
                    //someone has won
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }
            }
        }



    }

    public void playAgainBtn(View view)
    {
        isGameActive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        GridLayout gameBoard = (GridLayout) findViewById(R.id.gameBoardGridLayout);

        activePlayer = 0;
        for (int i = 0; i< gameState.length; i++)
        {
            gameState[i] = 2;
        }

        for(int i = 0; i < gameBoard.getChildCount(); i++)
        {
            ( (ImageView) gameBoard.getChildAt(i)).setImageResource(0);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
