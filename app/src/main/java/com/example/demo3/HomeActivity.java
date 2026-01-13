package com.example.demo3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demo3.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    // Game chalu karva mate
    public void startGame(View v) {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
    }

    // --- HOW TO PLAY LOGIC ---
    public void showInstructions(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);

        // Popup nu Title
        builder.setTitle("Cosmic Rules 🚀");

        // Game na Rules
        builder.setMessage("1. Game is played on a 3x3 grid.\n\n" +
                "2. Players take turns placing Stars (⭐) and Moons (🌙).\n\n" +
                "3. Connect 3 symbols in a row (horizontal, vertical, or diagonal) to win!\n\n" +
                "4. If all squares are full and no one has 3 in a row, it's a Draw.");

        // "Got it" button add karo
        builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Popup bandh karva mate
            }
        });

        // Dialog ne create ane show karo
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // App exit karva mate
    public void exitGame(View v) {
        finishAffinity();
    }
}
