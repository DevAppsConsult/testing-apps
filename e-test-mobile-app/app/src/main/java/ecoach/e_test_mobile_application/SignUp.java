package ecoach.e_test_mobile_application;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by banktech on 10/7/2014.
 */
public class SignUp extends Activity {

    Button signup, signin = null;
    EditText fn, ln, username, email, phone, country, school, passone, passtwo = null;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.sign_up);

        fn = (EditText) findViewById(R.id.fn);
        ln = (EditText) findViewById(R.id.ln);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        country = (EditText) findViewById(R.id.country);
        school = (EditText) findViewById(R.id.school);
        passone = (EditText) findViewById(R.id.password);
        passtwo = (EditText) findViewById(R.id.secondpassword);

        signin = (Button) findViewById(R.id.sign_in);
        signup = (Button) findViewById(R.id.sign_Up);
        init();


    }

    private void init() {

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin = new Intent(SignUp.this, SignIn.class);
                startActivity(signin);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //ALERT MESSAGE
                Toast.makeText(getBaseContext(), "Please wait, connecting to server.", Toast.LENGTH_LONG).show();

                try {

                    // URLEncode user defined data

                    String fnameValue = URLEncoder.encode(fn.getText().toString(), "UTF-8");
                    String lnameValue = URLEncoder.encode(ln.getText().toString(), "UTF-8");
                    String emailValue = URLEncoder.encode(email.getText().toString(), "UTF-8");
                    String phoneValue = URLEncoder.encode(phone.getText().toString(), "UTF-8");
                    String countryValue = URLEncoder.encode(country.getText().toString(), "UTF-8");
                    String schoolValue = URLEncoder.encode(school.getText().toString(), "UTF-8");
                    String usernameValue = URLEncoder.encode(username.getText().toString(), "UTF-8");
                    String passoneValue = URLEncoder.encode(passone.getText().toString(), "UTF-8");
                    String passtwoValue = URLEncoder.encode(passtwo.getText().toString(), "UTF-8");

                    // Create http cliient object to send request to server

                    HttpClient Client = new DefaultHttpClient();

                    // Create URL string

                    String URL = "https://api.ecoachsolutions.com/main.php?ecoachsignup=1&server=remote&user2=" + usernameValue +
                            "&pass1=" + passoneValue + "&pass2=" + passtwoValue + "&fname=" + fnameValue + "&lname=" + lnameValue +
                            "&email=" + emailValue + "&phone=" + phoneValue + "&country=" + countryValue + "&school=" + schoolValue;


                    Log.i("httpget", URL);

                    try {
                        String SetServerString = "";
                        String in;

                        // Create Request to server and get response

                        HttpGet httpget = new HttpGet(URL);
                        ResponseHandler<String> responseHandler = new BasicResponseHandler();
                        SetServerString = Client.execute(httpget, responseHandler);

                        JSONObject json = new JSONObject(SetServerString);
                        in = json.getString("msg");
                        in.toString();


                        Toast.makeText(getBaseContext(), in, Toast.LENGTH_LONG).show();
                    } catch (Exception ex) {
                        Toast.makeText(getBaseContext(), "Sorry Failed", Toast.LENGTH_LONG).show();
                    }
                } catch (UnsupportedEncodingException ex) {
                    Toast.makeText(getBaseContext(), "Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}

