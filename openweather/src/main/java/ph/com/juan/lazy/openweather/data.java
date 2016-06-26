package ph.com.juan.lazy.openweather;

import com.google.gson.annotations.SerializedName;

public class data {

    @SerializedName("coord")
    public Coordinates coordinates;
    public Weather[] weather;
    public String base;
    public Main main;
    public String visibility;
    public Wind wind;
    public Clouds clouds;
    public String dt;
    public Sys sys;
    public int id;
    public String name;
    public String cod;

    public class Coordinates{
        @SerializedName("lon")
        public Double lonitude;
        @SerializedName("lat")
        public Double latitude;
    }

    public class Weather{
        public int id;
        public String main;
        public String description;
        public String icon;
    }

    public class Main{
        public Double temp;
        public Double pressure;
        public Double humidity;
        public Double temp_min;
        public Double temp_max;
    }

    public class Wind{
        public Double speed;
        public Double degree;
    }

    public class Clouds{
        public String all;
    }

    public class Sys{
        public String type;
        public int id;
        public String message;
        public String country;
        public String sunrise;
        public String sunset;
    }

    public String getWeatherIconURL(){
        return "http://openweathermap.org/img/w/" + weather[0].icon + ".png";
    }

    public String getLocationName(){
        return name + ", " + sys.country;
    }


    public Double getTemp() {
        return main.temp;
    }

    public Double getPressure() {
        return main.pressure;
    }

    public Double getHumidity() {
        return main.humidity;
    }

    public Double getTemp_min() {
        return main.temp_min;
    }

    public Double getTemp_max() {
        return main.temp_max;
    }

    public String getWeatherDescription(){
        return weather[0].description;
    }
}
