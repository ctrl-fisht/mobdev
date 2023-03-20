package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.test.data.Cities;

public class AddActivity extends AppCompatActivity {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null)
        {
            setId(arguments.getInt("id"));
        }


        // spinner content
        Spinner spinner = findViewById(R.id.spinner8);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                Cities.getCities());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void startGo(View view){
        Spinner spinner = findViewById(R.id.spinner8);
        String selected = spinner.getSelectedItem().toString();

        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("id", getId());
        intent.putExtra("city", selected);
        startActivity(intent);
    }
}