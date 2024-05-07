package com.example.day10;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.NumberFormat;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String selectedCar = getIntent().getStringExtra("selectedCar");
        String selectedCity = getIntent().getStringExtra("selectedCity");
        int rentDays = getIntent().getIntExtra("rentDays", 0);
        double totalPrice = getIntent().getDoubleExtra("totalPrice", 0);

        double carRentalPricePerDay;
        switch (selectedCar) {
            case "Pajero":
                carRentalPricePerDay = 2400000; // Harga rental per hari
                break;
            case "Alpard":
                carRentalPricePerDay = 1550000; // Harga rental per hari
                break;
            case "Innova":
                carRentalPricePerDay = 850000; // Harga rental per hari
                break;
            case "Brio":
                carRentalPricePerDay = 550000; // Harga rental per hari
                break;
            default:
                carRentalPricePerDay = 0;
        }

        TextView selectedCarTextView = findViewById(R.id.selectedCarTextView);
        TextView selectedCityTextView = findViewById(R.id.selectedCityTextView);
        TextView rentalDurationTextView = findViewById(R.id.rentalDurationTextView);
        TextView discountTextView = findViewById(R.id.discountTextView);
        TextView carRentalPriceTextView = findViewById(R.id.carRentalPriceTextView); // TextView untuk harga rental mobil per hari
        TextView totalPriceTextView = findViewById(R.id.totalPriceTextView);
        TextView cityPriceTextView = findViewById(R.id.cityPriceTextView); // TextView untuk harga tambahan kota

        selectedCarTextView.setText("Mau Mobil Apa : " + selectedCar);
        selectedCityTextView.setText("Tujuan : " + selectedCity);
        rentalDurationTextView.setText("Rental Duration : " + rentDays + " days");

        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        String formattedCarRentalPricePerDay = formatRupiah.format(carRentalPricePerDay);
        carRentalPriceTextView.setText("Harga Rental Perhari : " + formattedCarRentalPricePerDay);

        double cityPrice = 0;
        if (selectedCity.equals("Inside city")) {
            cityPrice = 135000;
        } else if (selectedCity.equals("Outside city")) {
            cityPrice = 250000;
        }
        String formattedCityPrice = formatRupiah.format(cityPrice);
        cityPriceTextView.setText("Harga Tambahan Kota: " + formattedCityPrice);

        double discount = 0;
        if (totalPrice > 10000000) {
            discount = totalPrice * 0.07;
        } else if (totalPrice > 5000000) {
            discount = totalPrice * 0.05;
        }

        String formattedDiscount = formatRupiah.format(discount);
        String formattedTotalPrice = formatRupiah.format(totalPrice);
        discountTextView.setText("Discount : " + formattedDiscount);
        totalPriceTextView.setText("Total Pembayaran : " + formattedTotalPrice);
    }
}