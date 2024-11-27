package com.example.puzzled;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameScreenA extends AppCompatActivity {
    private Button option1, option2, option3, option4,buttonPrevious,buttonNext;
    private TextView questionText;
    private MediaPlayer buttonClickSound;
    String[] questions = {
            "What is the chemical symbol for gold?",
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "Who wrote 'Hamlet'?",
            "What is the square root of 64?",
            "What is the largest ocean in the world?",
            "Who wrote the play Romeo and Juliet?",
            "What is the hardest natural substance on Earth?"
    };

    private int currentQuestionIndex = 0;
    String[][] options = {
            {"Ag", "Au", "Pb", "Pt"},               // Options for question 1
            {"Berlin", "London", "Paris", "Madrid"},  // Options for question 2
            {"Earth", "Mars", "Venus", "Jupiter"},   // Options for question 3
            {"Charles Dickens", "J.K. Rowling", "William Shakespeare", "Mark Twain"},  // Options for question 4
            {"6", "7", "8", "9"} , // Options for question 5
            {"Atlantic Ocean","Indian Ocean","Pacific Ocean", "Arctic Ocean"},// Options for question 6
            {"Charles Dickens", "Leo Tolstoy","Mark Twain","William Shakespeare"}, // Options for question 7
            {"Diamond","Gold", "Iron","Platinum"},// Options for question 8
    };

    int[] correctAnswers =  {1, 2, 1, 2, 2, 2, 3, 0}; // Indexes of correct answers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_screen);




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        questionText = findViewById(R.id.question_text);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        buttonPrevious = findViewById(R.id.button_previous);
        buttonNext = findViewById(R.id.button_next);

        buttonClickSound = MediaPlayer.create(this, R.raw.answer_button);

        loadQuestion();

        // Set click listeners for option buttons with sound
        option1.setOnClickListener(v -> {
                    playSound();
                    checkAnswer(0);
                });

        option2.setOnClickListener(v -> {
            playSound();
            checkAnswer(1);
        });

        option3.setOnClickListener(v -> {
            playSound();
            checkAnswer(2);
        });

        option4.setOnClickListener(v -> {
            playSound();
            checkAnswer(3);
        });

        option1.setOnClickListener(v -> checkAnswer(0));
        option2.setOnClickListener(v -> checkAnswer(1));
        option3.setOnClickListener(v -> checkAnswer(2));
        option4.setOnClickListener(v -> checkAnswer(3));


        // Set up Previous and Next button click listeners
        buttonPrevious.setOnClickListener(v -> goToPreviousQuestion());
        buttonNext.setOnClickListener(v -> goToNextQuestion());
    }
                // Method to play sound
        private void playSound() {
            if (buttonClickSound != null) {
                buttonClickSound.start();  // Play the button click sound
            }
        }

    private void loadQuestion() {
        if (currentQuestionIndex >= 0 && currentQuestionIndex < questions.length) {
            questionText.setText(questions[currentQuestionIndex]);
            option1.setText(options[currentQuestionIndex][0]);
            option2.setText(options[currentQuestionIndex][1]);
            option3.setText(options[currentQuestionIndex][2]);
            option4.setText(options[currentQuestionIndex][3]);

            // Disable "Previous" button if on the first question
            buttonPrevious.setEnabled(currentQuestionIndex > 0);

            // Disable "Next" button if on the last question
            buttonNext.setEnabled(currentQuestionIndex < questions.length - 1);
        }
    }


    // Check the selected answer
    private void checkAnswer(int selectedOptionIndex) {
        if (selectedOptionIndex == correctAnswers[currentQuestionIndex]) {
            Toast.makeText(GameScreenA.this, "Correct", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(GameScreenA.this, "Incorrect", Toast.LENGTH_SHORT).show();
        }
    }

    // Navigate to the previous question
    private void goToPreviousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            loadQuestion();
        }
    }
    // Navigate to the next question
    private void goToNextQuestion() {
        if (currentQuestionIndex < questions.length - 1) {
            currentQuestionIndex++;
            loadQuestion();
        }else {
            // No more questions, show "Quiz Completed!" and disable the Next button
            Toast.makeText(GameScreenA.this, "Quiz Completed!", Toast.LENGTH_SHORT).show();
            buttonNext.setEnabled(false);  // Disable the "Next" button
        }
    }

    protected void onDestroy() {
        // Release the MediaPlayer resources when the activity is destroyed
        if (buttonClickSound != null) {
            buttonClickSound.release();
            buttonClickSound = null;
        }
        super.onDestroy();
    }

    public void buttonClick(View view){
        MediaPlayer playSound = MediaPlayer.create(this,R.raw.button_click);
        playSound.start();
    }


}