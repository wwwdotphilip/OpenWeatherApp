package ph.com.juan.lazy.openweather;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class LCL implements LocationListener {

    private Context context;
    private String TAG = "LCL";
    private LocationManager locationManager;
    private String provider;
    public Location location;
    private LCLCallback callback;

    public interface LCLCallback{
        void onLocationFound(Location location);
    }

    public LCL(Context context) {
        this.context = context;
    }

    public void findLocation(){

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(context, "Please enable location permission on this app", Toast.LENGTH_LONG).show();
//            return;
//        }

        // Initialize the location fields
        if (location != null) {
            onLocationChanged(location);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.v(TAG, "Latitude: " + location.getLatitude());
        Log.v(TAG, "Longitude: " + location.getLongitude());
        this.location = location;
        callback.onLocationFound(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(context, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(context, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }

    public void requestUpdates(){
        if (locationManager != null){
            locationManager.requestLocationUpdates(provider, 400, 1, this);
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(context, "Please enable location permission on this app", Toast.LENGTH_LONG).show();
//            return;
//        }
        }
    }

    public void removeUpdates(){
        if (locationManager != null){
            locationManager.removeUpdates(this);
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(context, "Please enable location permission on this app", Toast.LENGTH_LONG).show();
//            return;
//        }
        }
    }

    public void setCallback(LCLCallback lclCallback){
        this.callback = lclCallback;
    }
}
