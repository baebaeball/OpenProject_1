package com.example.software;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void OnButtonClicked1(View v) {
        Intent intent = new Intent(getApplicationContext(), Menu1.class);
        startActivity(intent);
    }

    public void OnButtonClicked2(View v) {
        Intent intent2 = new Intent(getApplicationContext(), Menu2.class);
        startActivity(intent2);
    }

}