package com.example.menuapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;


public class MainActivity5 extends AppCompatActivity {
    TextView score , turn , question ;
    Button ok,next;
    EditText answer ;
    Random randomnum=new Random();
    int num1,num2,useranswer,correctanswer;

    int scoree=0;
    int turnn=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        score=(TextView) findViewById(R.id.tv_score);
        turn=findViewById(R.id.tv_turn);
        question=findViewById(R.id.tv_question);
        answer=findViewById(R.id.tv_answer);
        ok=findViewById(R.id.btn_Ok);
        next=findViewById(R.id.btn_next);
        
        run_reset();
        gamecontinue();


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                useranswer= Integer.valueOf(answer.getText().toString());
                if(correctanswer==useranswer)
                {
                    question.setText("Well Done   Correct !");
                    scoree=scoree+1;
                    score.setText(""+scoree);
                }
                else
                {
                    question.setText("Oops!!      InCorrect !");
                }

            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                turnn=turnn+1;
                turn.setText(""+turnn);


                if(turnn==16){
                    if(scoree>=10){

                        Snackbar snackbar=Snackbar.make(view,"YOU WON",Snackbar.LENGTH_INDEFINITE);
                        snackbar.setDuration(5000);
                        snackbar.setAction("ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                snackbar.dismiss();
                            }
                        });
                        snackbar.show();

                        run_reset();
                        gamecontinue();

                    }
                    else{
                        Snackbar snackbar=Snackbar.make(view,"YOU LOSE",Snackbar.LENGTH_INDEFINITE);
                        snackbar.setDuration(3000);
                        snackbar.setAction("ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                snackbar.dismiss();
                            }
                        });
                        snackbar.show();

                        run_reset();
                        gamecontinue();


                    }
                }
                gamecontinue();
            }
        });
    }

    public void gamecontinue()
    {
        num1=randomnum.nextInt(10);
        num2=randomnum.nextInt(10);

        correctanswer = num1*num2;
        question.setText(num1+"   "+"*"+"   "+num2);
        answer.setText("");
    }

    public void run_reset()
    {
        score.setText("0");
        turn.setText("1");
        turnn=1;
        scoree=0;
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
            Intent intent = new Intent(MainActivity5.this, MainActivity2.class);
            startActivity(intent);
            Toast.makeText(this,"Menu",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}