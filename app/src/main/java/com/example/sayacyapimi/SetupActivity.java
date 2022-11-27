package com.example.sayacyapimi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetupActivity extends AppCompatActivity {

    Button uplus,uminus,dplus,dminus;
    EditText upValue,dValue;
    int upLimit=10,dLimit=0;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        uplus=(Button) findViewById(R.id.ustSinirArttir);
        uminus=(Button) findViewById(R.id.ustSinirAzalt);
        dplus=(Button) findViewById(R.id.altLimitArttir);
        dplus=(Button) findViewById(R.id.altLimitAzalt);

        upValue=(EditText) findViewById(R.id.ustSinir);
        dValue=(EditText) findViewById(R.id.altLimit);

        Context context=getApplicationContext();
        sharedPreferences=context.getSharedPreferences((context.getPackageName()),Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        uplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upLimit++;
                upValue.setText(String.valueOf(upLimit));
            }
        });
        uminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upLimit--;
                upValue.setText(String.valueOf(upLimit));
            }
        });
        upValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                upLimit=Integer.valueOf(upValue.getText().toString());
            }
        });


        dplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dLimit++;
                dValue.setText(String.valueOf(dLimit));
            }
        });
        dminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dLimit--;
                dValue.setText(String.valueOf(dLimit));
            }
        });
        dValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                dLimit=Integer.valueOf(dValue.getText().toString());
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        editor.putInt("UpperLimit",upLimit);
        editor.putInt("DLimit",dLimit);
        editor.commit();
    }
}