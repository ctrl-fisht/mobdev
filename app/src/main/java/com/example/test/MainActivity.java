package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test.data.Coord;
import com.example.test.data.WeatherData;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import com.example.test.data.WeatherInner;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private String imageUrl = "https://openweathermap.org/img/wn/";

    private final Coord coord = new Coord();

    private Api mApi = Api.Instance.getApi();

    private DatabaseAdapter adapter;


    public Coord getCoord() {
        return coord;
    }

    public void setCoord(float lat, float lon) {
        this.coord.setLat(lat);
        this.coord.setLon(lon);
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void mapGo(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("lat", coord.getLat());
        intent.putExtra("lon", coord.getLon());
        startActivity(intent);
    }

    public void listGo(View view){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int id = 0;

        Bundle arguments = getIntent().getExtras();
        if (arguments != null)
        {
            id = arguments.getInt("id");
        }

        adapter = new DatabaseAdapter(this);
        adapter.open();
        String city = adapter.getCity(id).city;
        adapter.close();

        TextView text_city = findViewById(R.id.cityView);
        TextView text_temp = findViewById(R.id.tempView);
        TextView text_desc = findViewById(R.id.descView);
        TextView text_wind = findViewById(R.id.windView);



        text_city.setText(city);

        mApi.getWeatherDataByCity(city, "7966fbae5ea3aaa7e072e3210c2a4732",
                "metric", "ru")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherData>() {
                    @Override
                    public void accept(WeatherData weatherData) throws Exception {
                        text_temp.setText(Float.toString(weatherData.getMain().getTemp()) + " °C");

                        WeatherInner weatherInner = weatherData.getWeather().get(0);
                        setImageUrl(getImageUrl() +weatherInner.getIcon() + "@4x.png");

                        text_desc.setText(weatherInner.getDescription());

                        ImageView statusImage = findViewById(R.id.imageView);
                        Picasso.get().load(imageUrl).into(statusImage);

                        text_wind.setText(Float.toString(weatherData.getWind().getSpeed()));
                        setCoord(weatherData.getCoord().getLat(), weatherData.getCoord().getLon());
                    }
                });
    }
}