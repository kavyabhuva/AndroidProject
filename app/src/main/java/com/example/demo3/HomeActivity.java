package com.example.demo3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    // Game chalu karva mate
    public void startGame(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Rules batavva mate
    public void showInstructions(View view) {
        new AlertDialog.Builder(this)
                .setTitle("How to Play")
                .setMessage("1. Game is played on a 3x3 grid.\n" +
                        "2. Stars go first, then Moons.\n" +
                        "3. Get 3 in a row (horizontal, vertical, or diagonal) to win!\n" +
                        "4. If grid is full with no winner, it's a Draw.")
                .setPositiveButton("Got it!", null)
                .show();
    }

    // App bandh karva mate
    public void exitGame(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Exit Game")
                .setMessage("Are you sure you want to leave the cosmic battle?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}