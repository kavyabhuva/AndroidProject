package com.example.demo3; //

import android.graphics.Color; //
import android.os.Bundle; //
import android.view.View; //
import android.widget.Button; //
import android.widget.TextView; //
import androidx.appcompat.app.AppCompatActivity; //
import com.example.demo3.R; //

public class MainActivity extends AppCompatActivity { //
    boolean gameActive = true;
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //
    }

    public void playerTap(View view) {
        Button btn = (Button) view;
        int tappedTag = Integer.parseInt(btn.getResources().getResourceEntryName(btn.getId()).replace("btn", ""));

        if (gameState[tappedTag] == 2 && gameActive) {
            gameState[tappedTag] = activePlayer;
            if (activePlayer == 0) {
                btn.setText("⭐"); activePlayer = 1;
                ((TextView) findViewById(R.id.status)).setText("Moon's Turn 🌙");
            } else {
                btn.setText("🌙"); activePlayer = 0;
                ((TextView) findViewById(R.id.status)).setText("Star's Turn ⭐");
            }
        }
        checkWinner();
    }

    private void checkWinner() {
        for (int[] winPos : winPositions) {
            if (gameState[winPos[0]] == gameState[winPos[1]] && gameState[winPos[1]] == gameState[winPos[2]] && gameState[winPos[0]] != 2) {
                gameActive = false;
                String winner = (gameState[winPos[0]] == 0) ? "STAR ⭐" : "MOON 🌙";
                ((TextView) findViewById(R.id.status)).setText(winner + " WON! 🏆");
            }
        }
    }

    public void gameReset(View view) { recreate(); }
}