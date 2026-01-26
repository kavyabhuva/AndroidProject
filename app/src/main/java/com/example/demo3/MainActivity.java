package com.example.demo3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    int activePlayer = 0; // 0: Star, 1: Moon
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playerTap(View view) {
        Button btn = (Button) view;
        int tappedTag = Integer.parseInt(btn.getResources().getResourceEntryName(btn.getId()).replace("btn", ""));

        if (gameState[tappedTag] == 2 && gameActive) {
            gameState[tappedTag] = activePlayer;

            // Star ane Moon set karva mate
            if (activePlayer == 0) {
                btn.setText("⭐");
                activePlayer = 1;
            } else {
                btn.setText("🌙");
                activePlayer = 0;
            }
            checkWinner();
        }
    }

    private void checkWinner() {
        boolean winnerFound = false;

        // 1. Winner Check logic
        for (int[] winPos : winPositions) {
            if (gameState[winPos[0]] == gameState[winPos[1]] &&
                    gameState[winPos[1]] == gameState[winPos[2]] &&
                    gameState[winPos[0]] != 2) {

                winnerFound = true;
                gameActive = false;
                String winner = (gameState[winPos[0]] == 0) ? "STAR ⭐" : "MOON 🌙";
                showFinalMessage(winner + " WON!");
                return;
            }
        }

        // 2. Draw Check logic (Jo koi winner na male to)
        if (!winnerFound) {
            boolean isDraw = true;
            for (int state : gameState) {
                if (state == 2) { // Hji jagya khali che
                    isDraw = false;
                    break;
                }
            }
            if (isDraw) {
                gameActive = false;
                showFinalMessage("MATCH DRAW! 🤝");
            }
        }
    }

    private void showFinalMessage(String msg) {
        // XML IDs check karva
        TextView winnerBox = findViewById(R.id.winnerBox);
        winnerBox.setText(msg);
        winnerBox.setVisibility(View.VISIBLE);

        findViewById(R.id.resetBtn).setVisibility(View.VISIBLE);
        findViewById(R.id.mainGrid).setVisibility(View.INVISIBLE);
    }

    public void gameReset(View v) {
        recreate(); // Game fari chalu karva mate
    }
}