# OpenWeatherApp

This is an android library for http://openweathermap.org/ to use for it's weather api

### Installation:

add this to your app gradle.build dependency

    dependencies {
          compile 'ph.com.juan.lazy.openweather:openweather:1.0.0'
    }

### Usage:

Make sure you have registered to openweathermap.org and have the api key if you didn't you can register [here](https://home.openweathermap.org/users/sign_up). Once you sing in go to Api Key tab ang copy the api key.

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
    
### Features:

At the moment it can only get your current location weather but I will add a feature later where you can add multiple locations. Or you can fork this repo and help improve it.
    
## License
MIT<br>
https://opensource.org/licenses/mit-license.php
