package com.example.langtranslater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView textView1;
    Button btnHindi;
    Button btnEnglish;
    Context context;
    Resources resources;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1=findViewById(R.id.tv1);
        btnEnglish=findViewById(R.id.btnEnglish);
        btnHindi=findViewById(R.id.btnHidni);


        btnHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = LocalHelper.setLocale(MainActivity.this, "hi");
                resources = context.getResources();
                textView1.setText(resources.getString(R.string.tvgmall));

            }
        });

        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = LocalHelper.setLocale(MainActivity.this, "en");
                resources = context.getResources();
                textView1.setText(resources.getString(R.string.tvgmall));
            }
        });


    }
}