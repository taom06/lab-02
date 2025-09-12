package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    Button addCity, deleteCity, Confirm;
    EditText inputText;

    enum Mode { NONE, ADD, DELETE }
    Mode currentMode = Mode.NONE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        addCity = findViewById(R.id.add_city);
        deleteCity = findViewById(R.id.delete_city);
        Confirm = findViewById(R.id.confirm_button);
        inputText = findViewById(R.id.input_text);
        String []cities = {"Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo",
        "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        addCity.setOnClickListener(v -> currentMode = Mode.ADD);
        deleteCity.setOnClickListener(v -> currentMode = Mode.DELETE);

        Confirm.setOnClickListener(v -> {
            String cityName = inputText.getText().toString().trim();

            if (cityName.isEmpty()) return;

            if (currentMode == Mode.ADD) {
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
            } else if (currentMode == Mode.DELETE) {
                dataList.remove(cityName);
                cityAdapter.notifyDataSetChanged();
            }

            inputText.setText("");   
            currentMode = Mode.NONE;
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



    }
}