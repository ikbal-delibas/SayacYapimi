package com.example.sayacyapimi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NextActivity extends AppCompatActivity {

    int counter=0,upLimit=10,dLimit=0;
    Button ayar,arttir,azalt;
    TextView value;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Vibrator vibrator=null;
    MediaPlayer mediaPlayer=null;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN)
        {
            counter-=5;
            value.setText(String.valueOf(counter));
        }
        if(keyCode==KeyEvent.KEYCODE_VOLUME_UP)
        {
            counter+=5;
            value.setText(String.valueOf(counter));
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        upLimit=sharedPreferences.getInt("UpperLimit",10);
        dLimit=sharedPreferences.getInt("UpperLimit",0);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);


        ayar=(Button) findViewById(R.id.ayarlar);
        arttir=(Button) findViewById(R.id.arttir);
        azalt=(Button) findViewById(R.id.azalt);

        value=(TextView) findViewById(R.id.value);

        Context context=getApplicationContext();
        sharedPreferences=context.getSharedPreferences((context.getPackageName()),Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        vibrator=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        mediaPlayer=MediaPlayer.create(context,R.raw.bildirim);

        arttir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter<upLimit){
                    counter++;
                }
                else{
                    vibrator.vibrate(1000);
                    Log.d("*************","Telefon Titreşti");
                    mediaPlayer.start();
                }

                value.setText(String.valueOf(counter));
            }
        });
        azalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter<upLimit){
                counter--;}
                else{
                    vibrator.vibrate(1000);
                    Log.d("*************","Telefon Titreşti");
                    mediaPlayer.start();
                }
                value.setText(String.valueOf(counter));
            }
        });

        ayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(NextActivity.this,SetupActivity.class);
                startActivity(i);
            }
        });


    }
}