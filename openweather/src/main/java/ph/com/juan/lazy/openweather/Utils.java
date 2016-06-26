package ph.com.juan.lazy.openweather;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {

    /**
     * checks connection status from Context passed as parameter
     *
     * @param context context to check if connection is present
     * @return <code>true</code> if connected to or <code>false</code> otherwise
     */
    public static boolean isConnectedToNetwork(Context context) {
        boolean isMobileData = false;
        boolean isWifi = false;
        boolean isEthernet = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            if (networkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                isWifi = networkInfo.isConnected();
            } else if (networkInfo.getTypeName().equalsIgnoreCase("MOBILE")) {
                isMobileData = networkInfo.isConnected();
            } else if (networkInfo.getTypeName().equalsIgnoreCase("ETHERNET")) {
                isEthernet = networkInfo.isConnected();
            }
        }

        return isMobileData || isWifi || isEthernet;
    }
}
