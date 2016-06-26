package ph.com.juan.lazy.openweather;


import android.content.Context;
import android.location.Location;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;


public class OpenWeather {

    private Context context;
    private String APIKey;
    private OpenWeatherCallback callback;
    private LCL lcl;

    public OpenWeather(Context context){
        this.context = context;
        init();
    }

    public OpenWeather(Context context, String APIKey){
        this.context = context;
        this.APIKey = APIKey;
        init();
    }

    public interface OpenWeatherCallback{
        void onDownloadComplete(data data);
    }

    /**
     * Initialize open weather
     */
    private void init(){
        lcl = new LCL(context);
        lcl.setCallback(new LCL.LCLCallback() {
            @Override
            public void onLocationFound(Location location) {
                requestWeather(location);
            }
        });
    }

    /**
     * Call open weather api
     */
    public void getWeather(){
        lcl.findLocation();
        lcl.requestUpdates();
    }

    /**
     * Set your API Key
     * You can get your api key by signing up to <a href="url">https://home.openweathermap.org/users/sign_up</a>
     * @param APIKey apikey from http://openweathermap.org/
     */
    public void setAPIKey(String APIKey){
        this.APIKey = APIKey;
    }

    private void requestWeather(Location location){
        if (Utils.isConnectedToNetwork(context)){
            RequestQueue queue = Volley.newRequestQueue(context);
            String url ="http://api.openweathermap.org/data/2.5/weather?lat=" +
                    location.getLatitude() + "2&lon=" +
                    location.getLongitude() + "&APPID=" +
                    APIKey;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    data data = new Gson().fromJson(response, data.class);
                    callback.onDownloadComplete(data);
                    lcl.removeUpdates();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(stringRequest);
        }
    }

    /**
     * Set callback to listen
     * @param callback OpenWeatherCallback
     */
    public void setCallback(OpenWeatherCallback callback){
        this.callback = callback;
    }
}
