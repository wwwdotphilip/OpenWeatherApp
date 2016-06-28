package ph.com.juan.lazy.openweather;


import android.content.Context;

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
    public Long longitude, latitude;
    public String city;

    public OpenWeather(Context context, String APIKey){
        this.context = context;
        this.APIKey = APIKey;
    }

    public interface OpenWeatherCallback{
        void onDownloadComplete(data data, String error);
    }

    public void requestWeather(){
        if (Utils.isConnectedToNetwork(context)){
            String request;
            if (longitude != null && latitude != null){
                request = "lat=" + longitude + "2&lon=" + longitude;
            } else if(city != null){
                request = "q=" + city.replaceAll(" ", "%20");
            } else {
                if (callback != null){
                    callback.onDownloadComplete(null, "No location provided.");
                }
                return;
            }
            RequestQueue queue = Volley.newRequestQueue(context);
            String url ="http://api.openweathermap.org/data/2.5/weather?"
                    + request + "&appid=" + APIKey;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    data data = new Gson().fromJson(response, data.class);
                    if (callback != null) {
                        callback.onDownloadComplete(data, null);
                    }
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
