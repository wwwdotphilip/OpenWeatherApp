# OpenWeatherApp

### Installation:

add this to your app gradle.build dependency

    dependencies {
          compile 'ph.com.juan.lazy.openweather:openweather:1.0.0'
    }

### Usage:

Declair OpenWeather class

    OpenWeather openweather = new OpenWeather(this, <your OpenWeather API key>);
    openweather.getWeather();
    
now add a callback

    openWeather.setCallback(new OpenWeather.OpenWeatherCallback() {
        @Override
        public void onDownloadComplete(data data) {
            // Do your stuff here
        }
    });
    
That's it your done.
    
## License
MIT<br>
https://opensource.org/licenses/mit-license.php
