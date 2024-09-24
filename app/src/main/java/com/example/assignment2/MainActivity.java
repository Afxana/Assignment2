package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private Button change;

    private Button customize;

    private int[] images = {R.drawable.cappuccino, R.drawable.latte, R.drawable.americano, R.drawable.espresso};

    private String[] captions = {"Cappuccino", "Latte", "Americano","Espresso"};

    private int currentImageIndex = 0;
    private int currentCaptionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    imageView = findViewById(R.id.cappuccino);
    textView = findViewById(R.id.name);
    change = findViewById(R.id.change);
    customize = findViewById(R.id.customize);

    change.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            currentImageIndex++;
            currentCaptionIndex++;
            if(currentImageIndex >= images.length){
                currentImageIndex = 0;
                currentCaptionIndex = 0;
            }
            imageView.setImageResource(images[currentImageIndex]);
            textView.setText(captions[currentCaptionIndex]);
        }
    });


    customize.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CustomizeActivity.class)));





    }
}