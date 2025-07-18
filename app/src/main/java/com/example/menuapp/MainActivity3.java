package com.example.menuapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.Control;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity3 extends AppCompatActivity {
TextView txv,tv_turn,tv_score,lay;
ImageView image, iv_flag;

Button btnflag1,btnflag2,btnflag3,btnflag4,retry,home;

List<CountryItem> list;


Random r;

int turn=1,turnview=1;
int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        iv_flag=(ImageView) findViewById(R.id.iv_flag);
        image=(ImageView) findViewById(R.id.imgstatus);
        btnflag1=(Button) findViewById(R.id.btnflag1);
        btnflag2=(Button) findViewById(R.id.btnflag2);
        btnflag3=(Button) findViewById(R.id.btnflag3);
        btnflag4=(Button) findViewById(R.id.btnflag4);
        retry=(Button) findViewById(R.id.btn_retry);
        home=(Button) findViewById(R.id.btn_home);
        lay=(TextView) findViewById(R.id.lay);
        tv_score=(TextView) findViewById(R.id.tv_score);
        tv_turn=(TextView) findViewById(R.id.tv_turn);
        txv=(TextView) findViewById(R.id.txvstatus);



        tv_turn.setText(""+turnview);
        tv_score.setText(""+score);

        r=new Random();

        list=new ArrayList<>();
        //adding items to list
        for (int i=0 ; i<new Database().answers.length; i++){
            list.add(new CountryItem(new Database().answers[i], new Database().flags[i]));
        }

        //shuffle the countries
        Collections.shuffle(list);

        newQuestion(turn);


        btnflag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //check if the answer is correct
                if (btnflag1.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())){
                    score=score+1;
                    tv_score.setText(""+score);

                    turnview++;
                    tv_turn.setText(""+turnview);
                    Toast.makeText(MainActivity3.this, "correct!", Toast.LENGTH_SHORT).show();

                    //che if the last question
                    if (turn<=list.size()){
                        turn++;

                        newQuestion(turn);

                    }else {
                        Toast.makeText(MainActivity3.this, "you finished the game!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else {
                    Toast.makeText(MainActivity3.this, "incorrect!", Toast.LENGTH_SHORT).show();
                    if (turn<list.size()){
                        turn++;

                        turnview++;
                        tv_turn.setText(""+turnview);
                        newQuestion(turn);

                    }else {
                        Toast.makeText(MainActivity3.this, "you finished the game!", Toast.LENGTH_SHORT).show();
                    }

                }


                if(turn==10) {
                    if (score >= 5) {

                        Snackbar snackbar = Snackbar.make(v, "YOU WON", Snackbar.LENGTH_INDEFINITE);
                        snackbar.setDuration(5000);
                        snackbar.setAction("ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                snackbar.dismiss();
                            }
                        });
                        snackbar.show();

                        tv_score.setText("0");
                        tv_turn.setText("1");
                        turn = 1;
                        turnview = 1;
                        score = 0;
                        newQuestion(turn);
                        lay.setVisibility(View.VISIBLE);
                        retry.setText("New Game");
                        retry.setVisibility(View.VISIBLE);
                        home.setVisibility(View.VISIBLE);
                        image.setVisibility(View.VISIBLE);
                        image.setImageResource(R.drawable.happy);
                        txv.setVisibility(View.VISIBLE);
                        txv.setText("YOU WON!!!!");
                        btnflag1.setVisibility(View.INVISIBLE);
                        btnflag2.setVisibility(View.INVISIBLE);
                        btnflag3.setVisibility(View.INVISIBLE);
                        btnflag4.setVisibility(View.INVISIBLE);
                        retry.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                lay.setVisibility(View.INVISIBLE);
                                retry.setVisibility(View.INVISIBLE);
                                home.setVisibility(View.INVISIBLE);
                                image.setVisibility(View.INVISIBLE);
                                txv.setVisibility(View.INVISIBLE);
                                btnflag1.setVisibility(View.VISIBLE);
                                btnflag2.setVisibility(View.VISIBLE);
                                btnflag3.setVisibility(View.VISIBLE);
                                btnflag4.setVisibility(View.VISIBLE);
                            }
                        });
                        home.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                                startActivity(intent);
                                Toast.makeText(MainActivity3.this, "Home", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else {
                        Snackbar snackbar = Snackbar.make(v, "YOU LOSE", Snackbar.LENGTH_INDEFINITE);
                        snackbar.setDuration(3000);
                        snackbar.setAction("ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                snackbar.dismiss();
                            }
                        });
                        snackbar.show();

                        tv_score.setText("0");
                        tv_turn.setText("1");
                        turn = 1;
                        turnview = 1;
                        score = 0;
                        newQuestion(turn);
                        lay.setVisibility(View.VISIBLE);
                        retry.setText("Retry");
                        retry.setVisibility(View.VISIBLE);
                        home.setVisibility(View.VISIBLE);
                        image.setVisibility(View.VISIBLE);
                        image.setImageResource(R.drawable.sad);
                        txv.setVisibility(View.VISIBLE);
                        txv.setText("YOU LOSE!!!!");
                        btnflag1.setVisibility(View.INVISIBLE);
                        btnflag2.setVisibility(View.INVISIBLE);
                        btnflag3.setVisibility(View.INVISIBLE);
                        btnflag4.setVisibility(View.INVISIBLE);
                        retry.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                lay.setVisibility(View.INVISIBLE);
                                retry.setVisibility(View.INVISIBLE);
                                home.setVisibility(View.INVISIBLE);
                                image.setVisibility(View.INVISIBLE);
                                txv.setVisibility(View.INVISIBLE);
                                btnflag1.setVisibility(View.VISIBLE);
                                btnflag2.setVisibility(View.VISIBLE);
                                btnflag3.setVisibility(View.VISIBLE);
                                btnflag4.setVisibility(View.VISIBLE);
                            }
                        });
                        home.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                                startActivity(intent);
                                Toast.makeText(MainActivity3.this, "Home", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                }



            }
        });
        btnflag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //check if the answer is correct
                if (btnflag2.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())){
                    score=score+1;
                    tv_score.setText(""+score);

                    turnview++;
                    tv_turn.setText(""+turnview);
                    Toast.makeText(MainActivity3.this, "correct!", Toast.LENGTH_SHORT).show();

                    //che if the last question
                    if (turn<list.size()){
                        turn++;
                        newQuestion(turn);

                    }else {
                        Toast.makeText(MainActivity3.this, "you finished the game!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity3.this, "incorrect!", Toast.LENGTH_SHORT).show();
                    if (turn<list.size()){
                        turn++;
                        turnview++;
                        tv_turn.setText(""+turnview);
                        newQuestion(turn);

                    }else {
                        Toast.makeText(MainActivity3.this, "you finished the game!", Toast.LENGTH_SHORT).show();
                    }
                }

                if(turn==10) {
                    if (score >= 5) {

                        Snackbar snackbar = Snackbar.make(v, "YOU WON", Snackbar.LENGTH_INDEFINITE);
                        snackbar.setDuration(5000);
                        snackbar.setAction("ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                snackbar.dismiss();
                            }
                        });
                        snackbar.show();

                        tv_score.setText("0");
                        tv_turn.setText("1");
                        turn = 1;
                        turnview = 1;
                        score = 0;
                        newQuestion(turn);
                        lay.setVisibility(View.VISIBLE);
                        retry.setText("New Game");
                        retry.setVisibility(View.VISIBLE);
                        home.setVisibility(View.VISIBLE);
                        image.setVisibility(View.VISIBLE);
                        image.setImageResource(R.drawable.happy);
                        txv.setVisibility(View.VISIBLE);
                        txv.setText("YOU WON!!!!");
                        btnflag1.setVisibility(View.INVISIBLE);
                        btnflag2.setVisibility(View.INVISIBLE);
                        btnflag3.setVisibility(View.INVISIBLE);
                        btnflag4.setVisibility(View.INVISIBLE);
                        retry.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                lay.setVisibility(View.INVISIBLE);
                                retry.setVisibility(View.INVISIBLE);
                                home.setVisibility(View.INVISIBLE);
                                image.setVisibility(View.INVISIBLE);
                                txv.setVisibility(View.INVISIBLE);
                                btnflag1.setVisibility(View.VISIBLE);
                                btnflag2.setVisibility(View.VISIBLE);
                                btnflag3.setVisibility(View.VISIBLE);
                                btnflag4.setVisibility(View.VISIBLE);
                            }
                        });
                        home.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                                startActivity(intent);
                                Toast.makeText(MainActivity3.this, "Home", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else {
                        Snackbar snackbar = Snackbar.make(v, "YOU LOOSE", Snackbar.LENGTH_INDEFINITE);
                        snackbar.setDuration(3000);
                        snackbar.setAction("ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                snackbar.dismiss();
                            }
                        });
                        snackbar.show();

                        tv_score.setText("0");
                        tv_turn.setText("1");
                        turn = 1;
                        turnview = 1;
                        score = 0;
                        newQuestion(turn);
                        lay.setVisibility(View.VISIBLE);
                        retry.setText("Retry");
                        retry.setVisibility(View.VISIBLE);
                        home.setVisibility(View.VISIBLE);
                        image.setVisibility(View.VISIBLE);
                        image.setImageResource(R.drawable.sad);
                        txv.setVisibility(View.VISIBLE);
                        txv.setText("YOU LOST!!!!");
                        btnflag1.setVisibility(View.INVISIBLE);
                        btnflag2.setVisibility(View.INVISIBLE);
                        btnflag3.setVisibility(View.INVISIBLE);
                        btnflag4.setVisibility(View.INVISIBLE);
                        retry.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                lay.setVisibility(View.INVISIBLE);
                                retry.setVisibility(View.INVISIBLE);
                                home.setVisibility(View.INVISIBLE);
                                image.setVisibility(View.INVISIBLE);
                                txv.setVisibility(View.INVISIBLE);
                                btnflag1.setVisibility(View.VISIBLE);
                                btnflag2.setVisibility(View.VISIBLE);
                                btnflag3.setVisibility(View.VISIBLE);
                                btnflag4.setVisibility(View.VISIBLE);
                            }
                        });
                        home.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                                startActivity(intent);
                                Toast.makeText(MainActivity3.this, "Home", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                }



            }
        });

        btnflag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //check if the answer is correct
                if (btnflag3.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())){
                    score=score+1;
                    tv_score.setText(""+score);

                    turnview++;
                    tv_turn.setText(""+turnview);
                    Toast.makeText(MainActivity3.this, "correct!", Toast.LENGTH_SHORT).show();

                    //che if the last question
                    if (turn<list.size()){
                        turn++;
                        newQuestion(turn);

                    }else {
                        Toast.makeText(MainActivity3.this, "you finished the game!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity3.this, "incorrect!", Toast.LENGTH_SHORT).show();
                    if (turn<list.size()){
                        turn++;
                        turnview++;
                        tv_turn.setText(""+turnview);
                        newQuestion(turn);

                    }else {
                        Toast.makeText(MainActivity3.this, "you finished the game!", Toast.LENGTH_SHORT).show();
                    }
                }

                if(turn==10) {
                    if (score >= 5) {

                        Snackbar snackbar = Snackbar.make(v, "YOU WON", Snackbar.LENGTH_INDEFINITE);
                        snackbar.setDuration(5000);
                        snackbar.setAction("ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                snackbar.dismiss();
                            }
                        });
                        snackbar.show();

                        tv_score.setText("0");
                        tv_turn.setText("1");
                        turn = 1;
                        turnview = 1;
                        score = 0;
                        newQuestion(turn);
                        lay.setVisibility(View.VISIBLE);
                        retry.setText("New Game");
                        retry.setVisibility(View.VISIBLE);
                        home.setVisibility(View.VISIBLE);
                        image.setVisibility(View.VISIBLE);
                        image.setImageResource(R.drawable.happy);
                        txv.setVisibility(View.VISIBLE);
                        txv.setText("YOU WON!!!!");
                        btnflag1.setVisibility(View.INVISIBLE);
                        btnflag2.setVisibility(View.INVISIBLE);
                        btnflag3.setVisibility(View.INVISIBLE);
                        btnflag4.setVisibility(View.INVISIBLE);
                        retry.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                lay.setVisibility(View.INVISIBLE);
                                retry.setVisibility(View.INVISIBLE);
                                home.setVisibility(View.INVISIBLE);
                                image.setVisibility(View.INVISIBLE);
                                txv.setVisibility(View.INVISIBLE);
                                btnflag1.setVisibility(View.VISIBLE);
                                btnflag2.setVisibility(View.VISIBLE);
                                btnflag3.setVisibility(View.VISIBLE);
                                btnflag4.setVisibility(View.VISIBLE);
                            }
                        });
                        home.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                                startActivity(intent);
                                Toast.makeText(MainActivity3.this, "Home", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else {
                        Snackbar snackbar = Snackbar.make(v, "YOU LOOSE", Snackbar.LENGTH_INDEFINITE);
                        snackbar.setDuration(3000);
                        snackbar.setAction("ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                snackbar.dismiss();
                            }
                        });
                        snackbar.show();

                        tv_score.setText("0");
                        tv_turn.setText("1");
                        turn = 1;
                        turnview = 1;
                        score = 0;
                        newQuestion(turn);
                        lay.setVisibility(View.VISIBLE);
                        retry.setText("Retry");
                        retry.setVisibility(View.VISIBLE);
                        home.setVisibility(View.VISIBLE);
                        image.setVisibility(View.VISIBLE);
                        image.setImageResource(R.drawable.sad);
                        txv.setVisibility(View.VISIBLE);
                        txv.setText("YOU LOSE!!!!");
                        btnflag1.setVisibility(View.INVISIBLE);
                        btnflag2.setVisibility(View.INVISIBLE);
                        btnflag3.setVisibility(View.INVISIBLE);
                        btnflag4.setVisibility(View.INVISIBLE);
                        retry.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                lay.setVisibility(View.INVISIBLE);
                                retry.setVisibility(View.INVISIBLE);
                                home.setVisibility(View.INVISIBLE);
                                image.setVisibility(View.INVISIBLE);
                                txv.setVisibility(View.INVISIBLE);
                                btnflag1.setVisibility(View.VISIBLE);
                                btnflag2.setVisibility(View.VISIBLE);
                                btnflag3.setVisibility(View.VISIBLE);
                                btnflag4.setVisibility(View.VISIBLE);
                            }
                        });
                        home.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                                startActivity(intent);
                                Toast.makeText(MainActivity3.this, "Home", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                }



            }
        });

        btnflag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //check if the answer is correct
                if (btnflag4.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())){
                    score=score+1;
                    tv_score.setText(""+score);

                    turnview++;
                    tv_turn.setText(""+turnview);
                    Toast.makeText(MainActivity3.this, "correct!", Toast.LENGTH_SHORT).show();

                    //che if the last question
                    if (turn<list.size()){
                        turn++;
                        turnview++;
                        tv_turn.setText(""+turnview);
                        newQuestion(turn);

                    }else {
                        Toast.makeText(MainActivity3.this, "you finished the game!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity3.this, "incorrect!", Toast.LENGTH_SHORT).show();
                    if (turn<list.size()){
                        turn++;
                        newQuestion(turn);

                    }else {
                        Toast.makeText(MainActivity3.this, "you finished the game!", Toast.LENGTH_SHORT).show();
                    }
                }

                if(turn==10) {
                    if (score >= 5) {

                        Snackbar snackbar = Snackbar.make(v, "YOU WON", Snackbar.LENGTH_INDEFINITE);
                        snackbar.setDuration(5000);
                        snackbar.setAction("ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                snackbar.dismiss();
                            }
                        });
                        snackbar.show();

                        tv_score.setText("0");
                        tv_turn.setText("1");
                        turn = 1;
                        turnview = 1;
                        score = 0;
                        newQuestion(turn);
                        lay.setVisibility(View.VISIBLE);
                        retry.setText("New Game");
                        retry.setVisibility(View.VISIBLE);
                        home.setVisibility(View.VISIBLE);
                        image.setVisibility(View.VISIBLE);
                        image.setImageResource(R.drawable.happy);
                        txv.setVisibility(View.VISIBLE);
                        txv.setText("YOU WON!!!!");
                        btnflag1.setVisibility(View.INVISIBLE);
                        btnflag2.setVisibility(View.INVISIBLE);
                        btnflag3.setVisibility(View.INVISIBLE);
                        btnflag4.setVisibility(View.INVISIBLE);
                        retry.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                lay.setVisibility(View.INVISIBLE);
                                retry.setVisibility(View.INVISIBLE);
                                home.setVisibility(View.INVISIBLE);
                                image.setVisibility(View.INVISIBLE);
                                txv.setVisibility(View.INVISIBLE);
                                btnflag1.setVisibility(View.VISIBLE);
                                btnflag2.setVisibility(View.VISIBLE);
                                btnflag3.setVisibility(View.VISIBLE);
                                btnflag4.setVisibility(View.VISIBLE);
                            }
                        });
                        home.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                                startActivity(intent);
                                Toast.makeText(MainActivity3.this, "Home", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else {
                        Snackbar snackbar = Snackbar.make(v, "YOU LOOSE", Snackbar.LENGTH_INDEFINITE);
                        snackbar.setDuration(3000);
                        snackbar.setAction("ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                snackbar.dismiss();
                            }
                        });
                        snackbar.show();

                        tv_score.setText("0");
                        tv_turn.setText("1");
                        turn = 1;
                        turnview = 1;
                        score = 0;
                        newQuestion(turn);
                        lay.setVisibility(View.VISIBLE);
                        retry.setText("Retry");
                        retry.setVisibility(View.VISIBLE);
                        home.setVisibility(View.VISIBLE);
                        image.setVisibility(View.VISIBLE);
                        image.setImageResource(R.drawable.sad);
                        txv.setVisibility(View.VISIBLE);
                        txv.setText("YOU LOST!!!!");
                        btnflag1.setVisibility(View.INVISIBLE);
                        btnflag2.setVisibility(View.INVISIBLE);
                        btnflag3.setVisibility(View.INVISIBLE);
                        btnflag4.setVisibility(View.INVISIBLE);
                        retry.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                lay.setVisibility(View.INVISIBLE);
                                retry.setVisibility(View.INVISIBLE);
                                home.setVisibility(View.INVISIBLE);
                                image.setVisibility(View.INVISIBLE);
                                txv.setVisibility(View.INVISIBLE);
                                btnflag1.setVisibility(View.VISIBLE);
                                btnflag2.setVisibility(View.VISIBLE);
                                btnflag3.setVisibility(View.VISIBLE);
                                btnflag4.setVisibility(View.VISIBLE);
                            }
                        });
                        home.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                                startActivity(intent);
                                Toast.makeText(MainActivity3.this, "Home", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                }



            }
        });


    }



    private void newQuestion(int number){
        //set flag image to the screen

        iv_flag.setImageResource(list.get(number-1).getImage());

        //decided on which button to place the correct answer

        int correct_answer =r.nextInt(4)+1;

        int firstButton=number-1;
        int secondButton;
        int thirdButton;
        int forthButton;

        switch (correct_answer){
            case 1:
                btnflag1.setText(list.get(firstButton).getName());

                do {
                    secondButton=r.nextInt(list.size());
                }while (secondButton==firstButton);
                do {
                    thirdButton=r.nextInt(list.size());
                }while (thirdButton==firstButton || thirdButton==secondButton);
                do {
                    forthButton=r.nextInt(list.size());
                }while (forthButton==firstButton || forthButton==thirdButton || forthButton==secondButton);

                btnflag2.setText(list.get(secondButton).getName());
                btnflag3.setText(list.get(thirdButton).getName());
                btnflag4.setText(list.get(forthButton).getName());
            break;

            case 2:
                btnflag2.setText(list.get(firstButton).getName());

                do {
                    secondButton=r.nextInt(list.size());
                }while (secondButton==firstButton);
                do {
                    thirdButton=r.nextInt(list.size());
                }while (thirdButton==firstButton || thirdButton==secondButton);
                do {
                    forthButton=r.nextInt(list.size());
                }while (forthButton==firstButton || forthButton==thirdButton || forthButton==secondButton);

                btnflag1.setText(list.get(secondButton).getName());
                btnflag3.setText(list.get(thirdButton).getName());
                btnflag4.setText(list.get(forthButton).getName());
                break;

            case 3:
                btnflag3.setText(list.get(firstButton).getName());

                do {
                    secondButton=r.nextInt(list.size());
                }while (secondButton==firstButton);
                do {
                    thirdButton=r.nextInt(list.size());
                }while (thirdButton==firstButton || thirdButton==secondButton);
                do {
                    forthButton=r.nextInt(list.size());
                }while (forthButton==firstButton || forthButton==thirdButton || forthButton==secondButton);

                btnflag1.setText(list.get(secondButton).getName());
                btnflag2.setText(list.get(thirdButton).getName());
                btnflag4.setText(list.get(forthButton).getName());
                break;

            case 4:
                btnflag4.setText(list.get(firstButton).getName());

                do {
                    secondButton=r.nextInt(list.size());
                }while (secondButton==firstButton);
                do {
                    thirdButton=r.nextInt(list.size());
                }while (thirdButton==firstButton || thirdButton==secondButton);
                do {
                    forthButton=r.nextInt(list.size());
                }while (forthButton==firstButton || forthButton==thirdButton || forthButton==secondButton);

                btnflag1.setText(list.get(secondButton).getName());
                btnflag3.setText(list.get(thirdButton).getName());
                btnflag2.setText(list.get(forthButton).getName());
                break;
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.item1)
        {
            Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
            startActivity(intent);
            Toast.makeText(this,"Menu",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}

