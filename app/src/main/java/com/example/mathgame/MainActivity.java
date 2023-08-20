package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    /**
     * Steps:
     * 1)In design we put the system ui and changed the color.
     * 2)To change the color we go to colors.xml and changed the top 3 colors.
     * 3)Put three images set background for the first activity and do other design and put id and
     * center and other stuffs and also the icon of the apps.
     * 4)This project helps to go in other activity like when add is clicked add layout will be
     * displayed in the screen.
     * 5)Always put the image in drawable not as v4 because it will not be seen in github.
     * 6)Then create a new activity so that when add,sub,mu is click it goes to other layout.
     * 7)Design of the add game.
     * 8)Java of game add.
     * 9)method to continue the game and random numbers too.
     * 10)Last exit one design.
     * 11)Then add other stuffs like mul,sub
     */
    Button add;
    Button sub;
    Button mul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.buttonadd);
        sub = findViewById(R.id.buttonsub);
        mul = findViewById(R.id.buttonmult);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), gameadd.class);
                startActivity(intent);
//                to close when the second activity is open
                finish();
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), gamesub.class);
                startActivity(intent);
//                to close when the second activity is open
                finish();
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), gamemul.class);
                startActivity(intent);
//                to close when the second activity is open
                finish();
            }
        });
    }
}