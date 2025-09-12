package com.salaar.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

    Button addButton;
    Button delButton;
    Button confButton;
    EditText inputBox;
    String newCity;
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addButton = findViewById(R.id.addButton);
        delButton = findViewById(R.id.delButton);
        confButton = findViewById(R.id.confirm_button);
        inputBox = findViewById(R.id.textInputEditText);

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);


        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                inputBox.setVisibility(View.VISIBLE);
                confButton.setVisibility(View.VISIBLE);
            }
        });

        confButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               newCity = inputBox.getText().toString();
               if (newCity.isEmpty()) {
                   Toast.makeText(MainActivity.this, "Please enter a city name!", Toast.LENGTH_SHORT).show();
                   return;
               }

               dataList.add(newCity);
               cityAdapter.notifyDataSetChanged();
               inputBox.setText("");
               inputBox.setVisibility(View.GONE);
               confButton.setVisibility(View.GONE);
            }
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                selectedPosition = position;
                delButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (selectedPosition != -1) {
                            dataList.remove(position);
                            cityAdapter.notifyDataSetChanged();
                            selectedPosition = -1;
                        } else {
                            Toast.makeText(MainActivity.this, "Please select a city to delete!", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

            }
        });



    }
}