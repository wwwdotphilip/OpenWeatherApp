# OpenWeatherApp

This is an android library for http://openweathermap.org/ to use for it's weather api

### Installation:

add this to your app gradle.build dependency

    dependencies {
          compile 'ph.com.juan.lazy.openweather:openweather:1.0.3'
    }

Make sure you have jcenter() in your project build.gradle which is default when you create your android project but just to make sure.

    buildscript {
        repositories {
            jcenter()
        }
    }

### Usage:

Make sure you have registered to openweathermap.org and have the api key if you didn't you can register [here](https://home.openweathermap.org/users/sign_up). Once you sing in go to Api Key tab ang copy the api key.

Declare OpenWeather class

    OpenWeather openweather = new OpenWeather(this, "Your OpenWeather API key");
    
enter city name you want

    openweather.city = "Your city";
    
or provide the long and latitude
    
    openweather.longitude = 128.2345324234;
    openweather.latitude = 75.132432454;
    
now add a callback

    openWeather.setCallback(new OpenWeather.OpenWeatherCallback() {
        @Override
        public void onDownloadComplete(data data, String error) {
            if (error != null){
                // show error message here
            } else {
                // do your stuff here.
            }
        }
    });
    
finally execute the weather search

    openweather.getWeather();
    
That's it, your done.
    
### Features:

At the moment it can only get your current location weather but I will add a feature later where you can add multiple locations. Or you can fork this repo and help improve it.
    
## License
MIT<br>
https://opensource.org/licenses/mit-license.php
