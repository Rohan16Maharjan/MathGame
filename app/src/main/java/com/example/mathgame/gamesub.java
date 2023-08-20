package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class gamesub extends AppCompatActivity {
    //    setting the time
    private static final long START_TIME_IN_MILLISEC = 60000;
    TextView score;
    TextView life;
    TextView time;
    TextView question;
    EditText answer;
    Button ok;
    Button next;
    //    object to create a random number and it can takes any datatype but now we take int only
    Random random = new Random();
    //    to hold the number in terms of int
    int number1;
    int number2;
    int useranswer;
    int realanswer;
    int userscore = 0;
    //    for timer
    int userlife = 3;
    //    build in object and it consists of method java for count down
    CountDownTimer timer;
    //   to know whether it is running or not
    Boolean time_runner;
    //    time left
    long time_left_in_milisec = START_TIME_IN_MILLISEC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamesub);
        score = findViewById(R.id.textViewscore);
        life = findViewById(R.id.textViewlife);
        time = findViewById(R.id.textViewtime);
        question = findViewById(R.id.textViewsub);
        answer = findViewById(R.id.editTextanswer);
        ok = findViewById(R.id.buttonok);
        next = findViewById(R.id.buttonnextquestion);
//        for question in textview
        gameContinue();
//        for ok button
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                useranswer = Integer.parseInt(answer.getText().toString());
//                when ok is clicked the time is paused
                pauseTimer();
                if (realanswer == useranswer) {
                    userscore = userscore + 10;
                    question.setText("Congratulation your answer is correct!!");
//The easiest way to convert int to String is very simple. Just add to int or Integer an empty string "" and you'll get your int as a String.
                    score.setText("" + userscore);
                } else {
                    userlife = userlife - 1;
                    question.setText("Sorry its wrong!!");
                    life.setText("" + userlife);
                }
            }
        });
//        for next
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText("");
                gameContinue();
//                when next is clicked the time is reset from first
                resetTimer();
//                this will help to go to result page
                if (userlife <= 0) {
                    Toast.makeText(gamesub.this, "Game Over!!", Toast.LENGTH_LONG).show();
                    Intent intents = new Intent(getApplicationContext(), result.class);
                    intents.putExtra("score", userscore);
                    startActivity(intents);
                    finish();
                } else {
                    gameContinue();
                }
            }
        });
    }

    //    method to continue the game
    public void gameContinue() {
        number1 = random.nextInt(100);
        number2 = random.nextInt(100);
        question.setText(number1 + "-" + number2);
        realanswer = number1 - number2;
        timer();

    }

    //    method to run the time
    public void timer() {
        timer = new CountDownTimer(time_left_in_milisec, 1000) {
            //            when the time is running Callback fired on regular interval.
            @Override
            public void onTick(long l) {
                time_left_in_milisec = l;
                updateText();
            }

            //when time is completed
            @Override
            public void onFinish() {
                time_runner = false;
                pauseTimer();
                resetTimer();
                updateText();
                userlife = userlife - 1;
                question.setText("Out of time!");
            }
        }.start();
        time_runner = true;
    }

    public void updateText() {
//        convert ms in sec
        int second = (int) ((time_left_in_milisec / 1000) % 60);
//        to display on the format
//        In java, String format() method returns a formatted string using the given locale,
//        specified format string, and arguments
//        %02d performs decimal integer conversion d , formatted with zero padding ( 0 flag),
//        with width 2 . Thus, an int argument whose value is say 7 , will be formatted into
//        "07" as a String  Locale. getDefault() is the current value
        String time_left = String.format(Locale.getDefault(), "%02d", second);
        time.setText(time_left);
    }

    public void pauseTimer() {
        timer.cancel();
        time_runner = false;
    }

    public void resetTimer() {
        time_left_in_milisec = START_TIME_IN_MILLISEC;
        updateText();
    }
}