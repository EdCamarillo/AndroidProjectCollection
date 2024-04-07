package com.example.androidprojectcollection;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Connect3Exercise extends AppCompatActivity {
    Button[][] buttons = new Button[5][5];
    boolean player1Turn = true;
    int roundCount;
    TextView textViewPlayer;
    Button buttonReset;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect3_exercise);

        textViewPlayer = findViewById(R.id.textViewPlayer);
        buttonReset = findViewById(R.id.buttonReset);

        int[] buttonIds = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
                R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn10,
                R.id.btn11, R.id.btn12, R.id.btn13, R.id.btn14, R.id.btn15,
                R.id.btn16, R.id.btn17, R.id.btn18, R.id.btn19, R.id.btn20,
                R.id.btn21, R.id.btn22, R.id.btn23, R.id.btn24, R.id.btn25};

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[j][i] = findViewById(buttonIds[i * 5 + j]);
            }
        }

        topButtonClickListener(buttons[0][0]);
        topButtonClickListener(buttons[0][1]);
        topButtonClickListener(buttons[0][2]);
        topButtonClickListener(buttons[0][3]);
        topButtonClickListener(buttons[0][4]);

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
    }

    private void topButtonClickListener(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int columnIndex = -1;
                for (int j = 0; j < 5; j++) {
                    if (buttons[0][j] == button) {
                        columnIndex = j;
                        break;
                    }
                }

                if (!buttons[0][columnIndex].getText().toString().isEmpty()) {
                    // Column is full, do nothing
                    return;
                }

                for (int i = 4; i >= 0; i--) {
                    if (buttons[i][columnIndex].getText().toString().isEmpty()) {
                        if (player1Turn) {
                            buttons[i][columnIndex].setText("X");
                            buttons[i][columnIndex].setBackground(getDrawable(R.drawable.btn_black));
                        } else {
                            buttons[i][columnIndex].setText("O");
                            buttons[i][columnIndex].setBackground(getDrawable(R.drawable.btn_red));
                        }
                        break;
                    }
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
                    textViewPlayer.setText(player1Turn ? "Black's Turn" : "Red's Turn");
                }
            }
        });
    }

    private boolean checkForWin() {
        // Check horizontally
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                if (!buttons[i][j].getText().toString().isEmpty() &&
                        buttons[i][j].getText().toString().equals(buttons[i][j + 1].getText().toString()) &&
                        buttons[i][j].getText().toString().equals(buttons[i][j + 2].getText().toString())) {
                    return true; // Horizontal win
                }
            }
        }

        // Check vertically
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if (!buttons[i][j].getText().toString().isEmpty() &&
                        buttons[i][j].getText().toString().equals(buttons[i + 1][j].getText().toString()) &&
                        buttons[i][j].getText().toString().equals(buttons[i + 2][j].getText().toString())) {
                    return true; // Vertical win
                }
            }
        }

        // Check diagonally (top-left to bottom-right)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!buttons[i][j].getText().toString().isEmpty() &&
                        buttons[i][j].getText().toString().equals(buttons[i + 1][j + 1].getText().toString()) &&
                        buttons[i][j].getText().toString().equals(buttons[i + 2][j + 2].getText().toString())) {
                    return true; // Diagonal win
                }
            }
        }

        // Check diagonally (top-right to bottom-left)
        for (int i = 0; i < 3; i++) {
            for (int j = 4; j > 1; j--) {
                if (!buttons[i][j].getText().toString().isEmpty() &&
                        buttons[i][j].getText().toString().equals(buttons[i + 1][j - 1].getText().toString()) &&
                        buttons[i][j].getText().toString().equals(buttons[i + 2][j - 2].getText().toString())) {
                    return true; // Diagonal win
                }
            }
        }

        return false; // No win
    }

    private void player1Wins() {
        textViewPlayer.setText("Black wins!");
        disableButtons();
    }

    private void player2Wins() {
        textViewPlayer.setText("Red wins!");
        disableButtons();
    }

    private void draw() {
        textViewPlayer.setText("Draw!");
        disableButtons();
    }

    private void disableButtons() {
        // Disable all buttons
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void resetGame() {
        // Reset game state
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackground(getDrawable(R.drawable.btn_empty));
                buttons[i][j].setEnabled(true);
            }
        }
        player1Turn = true;
        roundCount = 0;
        textViewPlayer.setText("Black's Turn");
    }

}
