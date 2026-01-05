package com.example.final_semesterptoject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GetStarted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        Button button = findViewById(R.id.buttonfinal);

        TextView telehealth = findViewById(R.id.telehealth);
        // Set click listener for ImageButton
    }
    public void home1(View v)
    {
        Intent intent=new Intent(GetStarted.this, MainActivity.class);
        startActivity(intent);
    }
}
