package com.example.assignment2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class CustomizeActivity extends AppCompatActivity {

    private CheckBox ice, iceCream, cream;

    ArrayList<String> arr = new ArrayList<>();
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    private SeekBar seekBar;
    private TextView textView, quantityTV, priceTV, coffee, ratingText;

    private Button increment, decrement, placeOrder;
    private int quantity = 0;
    private int price = 0;
    private int slot = 5;
    private Switch switch1;

    private RatingBar ratingBar;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);


        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.value);

        ice = findViewById(R.id.ice);
        iceCream = findViewById(R.id.iceCream);
        cream = findViewById(R.id.cream);

        radioGroup = findViewById(R.id.radioGroup);

        builder = new AlertDialog.Builder(this);
        coffee = findViewById(R.id.array);

        quantityTV = findViewById(R.id.quantity);
        priceTV = findViewById(R.id.price);
        increment = findViewById(R.id.increment);
        decrement = findViewById(R.id.decrement);


        switch1 = findViewById(R.id.switch1);
        ratingBar = findViewById(R.id.ratingBar);
        ratingText = findViewById(R.id.rating);
        placeOrder = findViewById(R.id.Order);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = findViewById(checkedId);
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(CustomizeActivity.this, "Hot coffee selected!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CustomizeActivity.this, "Cold coffee selected!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ice.setOnCheckedChangeListener((buttonView, isChecked) -> {
            check(buttonView, isChecked);
        });
        iceCream.setOnCheckedChangeListener((buttonView, isChecked) -> {
            check(buttonView, isChecked);
        });
        cream.setOnCheckedChangeListener((buttonView, isChecked) -> {
            check(buttonView, isChecked);

        });


        seekBar.setMax(slot-1);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textView.setText("Strength: " + (progress+1));


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        increment.setOnClickListener(v ->{
            quantity++;
            price = quantity * 200;
            quantityTV.setText("" + quantity);
            priceTV.setText("৳" + price);
        });

        decrement.setOnClickListener(v ->{
            if(quantity > 0){
                quantity--;
                price = quantity * 200;
                quantityTV.setText("" + quantity);
                priceTV.setText("৳" + price);
            }
        });




        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingText.setText("Rating: " + rating);
            }
        });

        placeOrder.setOnClickListener(v -> {
            try { String radioValue = radioButton.getText().toString();

                int progress = seekBar.getProgress();

                boolean switchValue = switch1.isChecked();
                if (switchValue){
                    radioValue = "Hot " + radioValue;
                } else {
                    radioValue = "Cold " + radioValue;
                }


                if (quantity == 0) {
                    Toast.makeText(getApplicationContext(), "Please add quantity!!", Toast.LENGTH_SHORT).show();
                } else {
                    builder.setTitle("Order Placed!!")
                            .setMessage("Order Summary:\n" + "Choice of Coffee: " + radioValue + "\nAdd-Ons: " + arr + "\nStrength: " + progress + "/5"+ "\nQuantity: " + quantity + "\nTotal Price: ৳ " + price + "\nRating: " + ratingBar.getRating() +"\nThank you for ordering!")
                            .setCancelable(false)
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(), "Order Placed!!", Toast.LENGTH_SHORT).show();
                                    quantity = 0;
                                    price = 0;
                                    quantityTV.setText("0");
                                    priceTV.setText("BDT 0");
                                    coffee.setText("");
                                    ice.setChecked(false);
                                    iceCream.setChecked(false);
                                    cream.setChecked(false);
                                    radioGroup.clearCheck();
                                    ratingBar.setRating(0);
                                }
                            }).show();
                }
            } catch (Exception e){
                Toast.makeText(getApplicationContext(), "Please select your choice of coffee!!", Toast.LENGTH_SHORT).show();
            }
        });

    }


    void check(CompoundButton buttonView, Boolean isChecked){
        if (isChecked){
            arr.add(buttonView.getText().toString());
            Log.d("array", String.valueOf(arr));
        } else {
            arr.remove(buttonView.getText().toString());
        }
        coffee.setText(String.valueOf(arr));
    }
}