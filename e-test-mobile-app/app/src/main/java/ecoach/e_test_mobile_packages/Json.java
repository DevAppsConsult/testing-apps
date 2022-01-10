package ecoach.e_test_mobile_packages;

import android.app.Activity;
import android.os.Bundle;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import ecoach.e_test_mobile_application.R;

/**
 * Created by banktech on 12/8/2014.
 */
public class Json extends Activity {

    String JSONString = null;
    JSONObject JSONObject = null;
    String test;
    JSONParser parser = new JSONParser();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


    }


}
