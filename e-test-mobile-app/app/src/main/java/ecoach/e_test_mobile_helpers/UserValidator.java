package ecoach.e_test_mobile_helpers;

/**
 * Created by banktech on 12/1/2014.
 */
//import com.fasterxml.*;

import android.util.Log;

import org.apache.http.HttpStatus;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import ecoach.e_test_mobile_application.Utils;

public class UserValidator {

    // This class is responsible for validating a user using static methods

    final static String DEV_URL = "http://192.168.1.123:4067/api";
    final static String PROD_URL = "http://10.0.2.2/4067/api";
    final static String authUrl = EtestUrls.authUrl;
    private static Thread t;

    public static UserValidationResult authenticateUser(String email, String pwd) {
        final JSONObject creds = new JSONObject();
        try {
            creds.put("email", email);
            creds.put("password", pwd);
        } catch (Exception ex) {
            Log.e(UserValidator.class.getName(), ex.getMessage());
        }
        //get auth content for the user
        UserValidationResult result = _validateUser2(creds, authUrl);

        return result;
    }

    private static UserValidationResult _validateUser2(JSONObject obj, String url) {
        UserValidationResult result = new UserValidationResult();
        result.EtestAuth = "";
        URL urlOb1j;
        HttpURLConnection urlConnection = null;
        try {

            /*urlOb1j = new URL(url);
            urlConnection = (HttpsURLConnection) urlOb1j.openConnection();

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null,null, new SecureRandom());
            urlConnection.setSSLSocketFactory(sslContext.getSocketFactory());

            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Content-type", "application/json");
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);*/

            if (MasterSettings.IsDevMode()) {
                urlConnection = Utils.MakeUnsecureConnection(url, "POST", null, null);
            } else {
                urlConnection = Utils.MakeSecureConnection(url, "POST", null, null);
            }
            //Set data
            OutputStream os = urlConnection.getOutputStream();
            os.write(obj.toString().getBytes("UTF-8"));
            os.close();

            //get response
            Log.d("Validator", "Status code: " + urlConnection.getResponseCode());
            if (urlConnection.getResponseCode() > HttpStatus.SC_BAD_REQUEST) {
                Log.e("Validator", "An error occurred: " + org.apache.commons.io.IOUtils.toString(urlConnection.getErrorStream(), "UTF-8"));
            }

            result.Result = org.apache.commons.io.IOUtils.toString(urlConnection.getInputStream(), "UTF-8");

            List<String> cookies = urlConnection.getHeaderFields().get("Set-Cookie");

            for (String cookie : cookies) {
                String[] splitCookie = cookie.split("=", 2);
                if (splitCookie[0].equals(Constants.Etest_COOKIE_AUTH_NAME)) {
                    result.EtestAuth = splitCookie[1];
                }
            }


            /*DefaultHttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(url);

            request.setEntity(new StringEntity(obj.toString()));
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");

            HttpResponse response = client.execute(request);

            List<Cookie> cookies  = client.getCookieStore().getCookies();

            for (int i = 0; i < cookies.size(); i++){
                if(cookies.get(i).getName().equals(Constants.EC_BOOKS_COOKIE_AUTH_NAME)){
                    //If we have an auth cookie, grab the contents
                    result.EcBooksAuth = cookies.get(i).getValue();
                    break;
                }
            }
            result.Result = EntityUtils.toString(response.getEntity());*/
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result;
    }

    public static class UserValidationResult {
        public String Result;
        public String EtestAuth;
    }
}
