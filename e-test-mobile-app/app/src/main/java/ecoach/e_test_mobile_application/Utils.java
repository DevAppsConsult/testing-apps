package ecoach.e_test_mobile_application;

/**
 * Created by banktech on 12/1/2014.
 */

import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import ecoach.e_test_mobile_helpers.Constants;

public class Utils {
    /*
         * Meant to be used in a static way. Came up because stupid java has no
         * clean way for escaping strings
         */
    public static String EscapeString(String s) {
        return s.replace("\\", "\\\\");
    }

    //Use secure connections for ALL non dev machines.
    public static HttpsURLConnection MakeSecureConnection(String urlString, String method, String authToken, HashMap<String, String> additionalHeaders) {
        try {
            URL urlObj = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, null, new SecureRandom());
            connection.setSSLSocketFactory(sslContext.getSocketFactory());

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty(Constants.EC_HEADER_APP_TYPE, Constants.EC_MOBILE_APP_TYPE); //we need this to handle special cases for mobile app
            if (authToken != null && !authToken.isEmpty()) {
                connection.setRequestProperty("Cookie", String.format("%s=%s", Constants.Etest_COOKIE_AUTH_NAME, authToken));
            }
            connection.setDoInput(true);
            if (method.toLowerCase().equals("post") || method.toLowerCase().equals("put")) {
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
            }

            if (additionalHeaders != null) {
                for (String key : additionalHeaders.keySet()) {
                    connection.setRequestProperty(key, additionalHeaders.get(key));
                }
            }

            return connection;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //Use insecure connections for dev machines.
    public static HttpURLConnection MakeUnsecureConnection(String urlString, String method, String authToken, HashMap<String, String> additionalHeaders) {
        try {
            URL urlObj = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty(Constants.EC_HEADER_APP_TYPE, Constants.EC_MOBILE_APP_TYPE); //we need this to handle special cases for mobile app
            connection.setRequestProperty("Cookie", String.format("%s=%s", Constants.Etest_COOKIE_AUTH_NAME, authToken));
            connection.setDoInput(true);

            if (method.toLowerCase().equals("post") || method.toLowerCase().equals("put")) {
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
            }

            if (additionalHeaders != null) {
                for (String key : additionalHeaders.keySet()) {
                    connection.setRequestProperty(key, additionalHeaders.get(key));
                }
            }

            return connection;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
