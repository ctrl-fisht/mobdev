package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test.data.Main;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class ListActivity extends AppCompatActivity {

    DatabaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        adapter = new DatabaseAdapter(this);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null)
        {
            int id = arguments.getInt("id");
            String city = arguments.getString("city");
            adapter.open();
            adapter.insert(id, city);
            adapter.close();
        }


        adapter.open();

        Map<Integer, TextView> views = new HashMap<>();

        views.put(0, findViewById(R.id.cityView1));
        views.put(1, findViewById(R.id.cityView2));
        views.put(2, findViewById(R.id.cityView3));
        views.put(3, findViewById(R.id.cityView4));


        Map<Integer, String> cities = new HashMap<Integer, String>();
        for (int i = 0; i < 4; i++) {
            City got = adapter.getCity(i);
            if (got != null){
                cities.put(i, got.city);
            }
            else {
                cities.put(i, "Добавить +");
            }
        }

        adapter.close();


        for (int i = 0; i < 4; i++)
        {
            views.get(i).setText(cities.get(i));
        }

    }
    public void card(int cardId){
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra("id", cardId);
        startActivity(intent);
    }

    public void card1(View view){
        TextView cityView1 = findViewById(R.id.cityView1);
        if (cityView1.getText() == "Добавить +") {
            card(0);
        }
        else{
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("id", 0);
            startActivity(intent);
        }
    }
    public void card2(View view){
        TextView cityView2 = findViewById(R.id.cityView2);
        if (cityView2.getText() == "Добавить +") {
            card(1);
        }
        else{
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("id", 1);
            startActivity(intent);
        }
    }
    public void card3(View view){
        TextView cityView3 = findViewById(R.id.cityView3);
        if (cityView3.getText() == "Добавить +") {
            card(2);
        }
        else{
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("id", 2);
            startActivity(intent);
        }
    }
    public void card4(View view){
        TextView cityView4 = findViewById(R.id.cityView4);
        if (cityView4.getText() == "Добавить +") {
            card(3);
        }
        else{
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("id", 3);
            startActivity(intent);
        }
    }

}