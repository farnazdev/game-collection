package com.example.menuapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class Medium extends AppCompatActivity {
    ImageView iv_button, iv_arrow;
    TextView tv_points, tv_turns;
    ProgressBar progressBar;
    Handler handler;
    Runnable runnable;
    Random r;


    private final static int STATE_BLUE = 1;
    private final static int STATE_GREEN2 = 2;
    private final static int STATE_PINK = 3;
    private final static int STATE_YELLOW = 4;
    private final static int STATE_GREEN = 5;
    private final static int STATE_ORANGE = 6;


    int buttonState = STATE_BLUE;
    int arrowState = STATE_BLUE;

    int currentTime = 4000;
    int startTime = 4000;
    int currentTurn = 1;

    int currentPoints = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium);

        iv_button = findViewById(R.id.iv_button);
        iv_arrow = findViewById(R.id.iv_arrow);
        tv_points = findViewById(R.id.tv_points);
        tv_turns = findViewById(R.id.tv_turns);
        progressBar = findViewById(R.id.progressBar);


        //set the initial progressbar time to 4 seconds
        progressBar.setMax(startTime);
        progressBar.setProgress(startTime);

        //display the starting points
        tv_points.setText("Points: " + currentPoints);
        tv_turns.setText("Turns: " + currentTurn);

        //generate random arrow color at the start of the game
        r = new Random();
        arrowState = r.nextInt(6) + 1;
        setArrowImage(arrowState);

        iv_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rotate the button with the colors
                setButtonImage(setButtonPosition(buttonState));

            }
        });
        //main game loop
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                //show progress
                currentTime = currentTime - 100;
                progressBar.setProgress(currentTime);

                //check if the colors of the arrow and the button are the same

                if (currentTime > 0) {
                    handler.postDelayed(runnable, 100);
                } else {
                    //check if the colors of the arrow and the button are the same
                    if (buttonState == arrowState) {
                        //increase points and show them
                        currentPoints = currentPoints + 1;
                        currentTurn = currentTurn + 1;
                        tv_points.setText("Points: " + currentPoints);
                        tv_turns.setText("Turns: " + currentTurn);
                        //make the speed higher every turn/ if the speed is 1 second make it again 2 seconds
                        startTime = startTime - 100;
                        if (startTime < 1000) {
                            startTime = 2000;
                        }
                        progressBar.setMax(startTime);
                        currentTime = startTime;
                        progressBar.setProgress(currentTime);

                        //generate new color of the arrow
                        arrowState = r.nextInt(6) + 1;
                        setArrowImage(arrowState);

                        handler.postDelayed(runnable, 100);
                    } else {
                        finish();
                        iv_button.setEnabled(false);
                        Toast.makeText(Medium.this, "Game Over!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Medium.this, FlagQuiz.class);

                        startActivity(intent);


                    }
                }
                if (currentPoints==10){
                    finish();
                    Toast.makeText(Medium.this, "YOU WIN!!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Medium.this, FlagQuiz.class);
                    startActivity(intent);

                }


            }
        };

        //start the game loop
        handler.postDelayed(runnable, 100);

    }

    //display the arrow color according to the generated number
    private void setArrowImage(int state) {
        switch (state) {
            case STATE_BLUE:
                iv_arrow.setImageResource(R.drawable.ic_blue);
                arrowState = STATE_BLUE;
                break;

            case STATE_ORANGE:
                iv_arrow.setImageResource(R.drawable.ic_orange);
                arrowState = STATE_ORANGE;
                break;

            case STATE_GREEN2:
                iv_arrow.setImageResource(R.drawable.ic_green2);
                arrowState = STATE_GREEN2;
                break;

            case STATE_YELLOW:
                iv_arrow.setImageResource(R.drawable.ic_yellow);
                arrowState = STATE_YELLOW;
                break;

            case STATE_PINK:
                iv_arrow.setImageResource(R.drawable.ic_pink);
                arrowState = STATE_PINK;
                break;

            case STATE_GREEN:
                iv_arrow.setImageResource(R.drawable.ic_green);
                arrowState = STATE_GREEN;
                break;
        }
    }

    //rotate animation of the button when clicked
    private void setRotation(final ImageView image, final int drawable) {
        //rotate 60 degrees
        RotateAnimation rotateAnimation = new RotateAnimation(0, 60, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(100);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image.setImageResource(drawable);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        image.startAnimation(rotateAnimation);
    }

    //set button colors position 1-7
    private int setButtonPosition(int position) {
        position = position + 1;
        if (position == 7) {
            position = 1;
        }
        return position;
    }

    //display the button colors positions
    private void setButtonImage(int state){
        switch (state) {
            case STATE_BLUE:
                setRotation(iv_button,R.drawable.ic_button_blue);
                buttonState = STATE_BLUE;
                break;

            case STATE_ORANGE:
                setRotation(iv_button,R.drawable.ic_button_orange);
                buttonState = STATE_ORANGE;
                break;

            case STATE_GREEN2:
                setRotation(iv_button,R.drawable.ic_button_green2);
                buttonState = STATE_GREEN2;
                break;

            case STATE_YELLOW:
                setRotation(iv_button,R.drawable.ic_button_yellow);
                buttonState = STATE_YELLOW;
                break;

            case STATE_PINK:
                setRotation(iv_button,R.drawable.ic_button_pink);
                buttonState = STATE_PINK;
                break;

            case STATE_GREEN:
                setRotation(iv_button,R.drawable.ic_button_green);
                buttonState = STATE_GREEN;
                break;
        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.item1)
        {
            Intent intent = new Intent(Medium.this, FlagQuiz.class);
            startActivity(intent);
            Toast.makeText(this,"Menu",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}