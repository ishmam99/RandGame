package com.example.ishmam.randgame;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button play,reset;
    TextView Score,highscore;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private String Pref_Game="com.example.ishmam.randgame.score";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play=findViewById(R.id.btn_play);
        reset=findViewById(R.id.btn_reset);
        Score=findViewById(R.id.txt_score);
        highscore=findViewById(R.id.txt_hs);


        preferences=getSharedPreferences(Pref_Game,MODE_PRIVATE);
        editor=preferences.edit();
        final int highScore=preferences.getInt("high_score",0);
        highscore.setText("High Score : "+highScore);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random=new Random();
                int score=random.nextInt(2000);
                Score.setText(""+score);

                int getSaveScore=preferences.getInt("high_score",0);
                if(score>getSaveScore)
                {
                    highscore.setText("High Score : "+score);
                    editor.putInt("high_score",score);
                    editor.commit();
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                highscore.setText("High Score : 0");
                Score.setText("0");
                editor.putInt("high_score",0);
                editor.commit();
            }
        });
    }
}
