package ph.com.juan.lazy.openweatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ph.com.juan.lazy.openweather.OpenWeather;
import ph.com.juan.lazy.openweather.data;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);

        OpenWeather openWeather = new OpenWeather(MainActivity.this, BuildConfig.openWeatherAPIKey);
        openWeather.city = "Davao Philippines";
        openWeather.setCallback(new OpenWeather.OpenWeatherCallback() {
            @Override
            public void onDownloadComplete(data data, String error) {
                if (error != null){
                    tv.setText(error);
                } else {
                    tv.setText(data.getLocationName());
                }
            }
        });
        openWeather.requestWeather();
    }
}
