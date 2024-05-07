package com.example.day10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radiogroupcar;
    private RadioGroup radiogroupcity;
    private EditText rentDaysEditText;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radiogroupcar = findViewById(R.id.radiogroupcar);
        radiogroupcity = findViewById(R.id.radiogroupcity);
        rentDaysEditText = findViewById(R.id.rentDaysEditText);
        confirmButton = findViewById(R.id.btnRent);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedCarId = radiogroupcar.getCheckedRadioButtonId();
                RadioButton selectedCarRadioButton = findViewById(selectedCarId);
                String selectedCar = selectedCarRadioButton.getText().toString();

                int selectedCityId = radiogroupcity.getCheckedRadioButtonId();
                RadioButton selectedCityRadioButton = findViewById(selectedCityId);
                String selectedCity = selectedCityRadioButton.getText().toString();

                String rentDaysStr = rentDaysEditText.getText().toString();
                int rentDays = Integer.parseInt(rentDaysStr);

                double basePrice;
                switch (selectedCar) {
                    case "Pajero":
                        basePrice = 2400000;
                        break;
                    case "Alpard":
                        basePrice = 1550000;
                        break;
                    case "Innova":
                        basePrice = 850000;
                        break;
                    case "Brio":
                        basePrice = 550000;
                        break;
                    default:
                        basePrice = 0;
                }

                double totalPrice = basePrice * rentDays;

                // Add additional charges for inside or outside city
                if (selectedCity.equals("Inside city")) {
                    totalPrice += 135000;
                } else if (selectedCity.equals("Outside city")) {
                    totalPrice += 250000;
                }

                // Apply discount based on total price
                if (totalPrice > 10000000) { // If total price is more than 10 million
                    totalPrice *= 0.93; // 7% discount
                } else if (totalPrice > 5000000) { // If total price is more than 5 million
                    totalPrice *= 0.95; // 5% discount
                }

                // Example: Intent to DetailActivity
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("selectedCar", selectedCar);
                intent.putExtra("selectedCity", selectedCity);
                intent.putExtra("rentDays", rentDays);
                intent.putExtra("totalPrice", totalPrice);
                startActivity(intent);
            }
        });
    }
}