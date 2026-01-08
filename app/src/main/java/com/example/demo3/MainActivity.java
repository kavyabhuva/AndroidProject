package com.example.demo3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    int activePlayer = 0;
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
            btn.setText((activePlayer == 0) ? "⭐" : "🌙");
            activePlayer = (activePlayer == 0) ? 1 : 0;

            String statusText = (activePlayer == 0) ? "Star's Turn ⭐" : "Moon's Turn 🌙";
            ((TextView) findViewById(R.id.status)).setText(statusText);

            checkResult();
        }
    }

    private void checkResult() {
        boolean winnerFound = false;
        for (int[] winPos : winPositions) {
            if (gameState[winPos[0]] == gameState[winPos[1]] &&
                    gameState[winPos[1]] == gameState[winPos[2]] &&
                    gameState[winPos[0]] != 2) {

                winnerFound = true;
                gameActive = false;
                String winnerName = (gameState[winPos[0]] == 0) ? "STAR ⭐" : "MOON 🌙";
                showFinalMessage("🏆\n" + winnerName + "\nWON!");
                break;
            }
        }

        if (!winnerFound) {
            boolean isDraw = true;
            for (int state : gameState) {
                if (state == 2) { isDraw = false; break; }
            }
            if (isDraw) {
                gameActive = false;
                showFinalMessage("🤝\nMATCH\nDRAW!");
            }
        }
    }

    private void showFinalMessage(String message) {
        TextView winnerBox = findViewById(R.id.winnerBox);
        GridLayout mainGrid = findViewById(R.id.mainGrid);
        Button resetBtn = findViewById(R.id.resetBtn);
        TextView status = findViewById(R.id.status);

        mainGrid.setVisibility(View.INVISIBLE);
        winnerBox.setText(message);
        winnerBox.setVisibility(View.VISIBLE);
        resetBtn.setVisibility(View.VISIBLE);
        status.setVisibility(View.GONE);
    }

    public void gameReset(View view) {
        recreate();
    }
}