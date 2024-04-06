package com.example.androidprojectcollection;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Connect3Exercise extends AppCompatActivity {

    private Button[][] buttons = new Button[5][5];
    private boolean player1Turn = true;
    private int roundCount;
    private TextView textViewPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect3_exercise);

        textViewPlayer = findViewById(R.id.textViewPlayer);

        // Initialize buttons and set click listeners
        buttons[0][0] = findViewById(R.id.btn1);
        buttons[0][1] = findViewById(R.id.btn2);
        buttons[0][2] = findViewById(R.id.btn3);
        buttons[0][3] = findViewById(R.id.btn4);
        buttons[0][4] = findViewById(R.id.btn5);

        buttons[1][0] = findViewById(R.id.btn6);
        buttons[1][1] = findViewById(R.id.btn7);
        buttons[1][2] = findViewById(R.id.btn8);
        buttons[1][3] = findViewById(R.id.btn9);
        buttons[1][4] = findViewById(R.id.btn10);

        Button buttonReset = findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) v).setText("X");
            ((Button) v).setTextColor(Color.parseColor("#FF4081")); // Player 1 color
            textViewPlayer.setText("Player 2's Turn");
        } else {
            ((Button) v).setText("O");
            ((Button) v).setTextColor(Color.parseColor("#3F51B5")); // Player 2 color
            textViewPlayer.setText("Player 1's Turn");
        }

        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 25) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }

    private boolean checkForWin() {
        String[][] field = new String[5][5];

        // Populate field array with button texts
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        // Check rows
        for (int i = 0; i < 5; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 5; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        // Check diagonals
        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        textViewPlayer.setText("Player 1 wins!");
        disableButtons();
    }

    private void player2Wins() {
        textViewPlayer.setText("Player 2 wins!");
        disableButtons();
    }

    private void draw() {
        textViewPlayer.setText("Draw!");
    }

    private void disableButtons() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void resetGame() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setTextColor(Color.BLACK);
                buttons[i][j].setEnabled(true);
            }
        }

        player1Turn = true;
        roundCount = 0;
        textViewPlayer.setText("Player 1's Turn");
    }
}