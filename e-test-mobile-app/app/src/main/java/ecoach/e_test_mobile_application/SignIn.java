package ecoach.e_test_mobile_application;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URLEncoder;

import ecoach.e_test_mobile_core.SessionManager;

/**
 * Created by banktech on 10/7/2014.
 */
public class SignIn extends Activity {


    public String msg,user,gender,telephone,school,residence,country,level,location,dob;
    // Declare variables
    TextView textView;
    EditText password, username = null;
    Button signup, signin = null;
    ProgressDialog dialog = null;
    ProgressBar _spinner = null;

    // Session Manager Class
    SessionManager session;

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.sign_in);

        // Session Manager
        session = new SessionManager(getApplicationContext());
        init();
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();


        //session = new SessionManager(getApplicationContext());

        textView = (TextView) findViewById(R.id.password_recovery);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


    }


    public void init() {
        username = (EditText) findViewById(R.id.uname);
        password = (EditText) findViewById(R.id.password);
        signup = (Button) findViewById(R.id.sign_up);
        signin = (Button) findViewById(R.id.sign_in);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signup = new Intent(SignIn.this, SignUp.class);
                startActivity(signup);

            }
        });

        _spinner = (ProgressBar) findViewById(R.id.loginProgressSpinner);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin(v);

            }


        });

    }


    private void login() {
        try {

            String loginValue = URLEncoder.encode(username.getText().toString(), "UTF-8");
            String passValue = URLEncoder.encode(password.getText().toString(), "UTF-8");

            HttpClient Client = new DefaultHttpClient();
            String URL = "https://api.ecoachsolutions.com/main.php?ecoachsignin=1&server=remote&user=" + loginValue + "&pass=" + passValue;

            //Log.i("httpget", URL);
            try {
                String SetServerString = "";
                String in;

                HttpGet httpget = new HttpGet(URL);
                //HttpResponse response = Client.execute(httpget);
                //InputStream instream = response.getEntity().getContent();

                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                SetServerString = Client.execute(httpget, responseHandler);

                JSONObject json = new JSONObject(SetServerString);
                in = json.getString("msg");
                in.toString();


                Toast.makeText(getBaseContext(), in, Toast.LENGTH_LONG).show();
                System.out.println(SetServerString);
                _spinner.setVisibility(View.INVISIBLE);
                httpget.abort();
            } catch (Exception ex) {
                Toast.makeText(getBaseContext(), "Sorry Failed", Toast.LENGTH_LONG).show();
                System.out.println(ex);
            }
        } catch (UnsupportedEncodingException ex) {
            Toast.makeText(getBaseContext(), "Failed", Toast.LENGTH_LONG).show();
        }
    }

    private void doLogin(View view) {
        Toast.makeText(getBaseContext(), "Logging you in ... ", Toast.LENGTH_LONG).show();
        _spinner.setVisibility(View.VISIBLE);

        //get credentials
        final String uname = username.getText().toString();
        final String pwd = password.getText().toString();

        if (!NonEmptyCredentials(uname, pwd)) {
            ShowLoginError(R.string.EmptyCredentialsWarning);
            return;
        }

        //Attempt validation with server
        new AsyncTask<Void, Void, Intent>() {
            @Override
            protected Intent doInBackground(Void... voids) {

                runOnUiThread(new Runnable() {
                    public void run() {
                        // runs on UI thread
                        try {

                            String loginValue = URLEncoder.encode(username.getText().toString(), "UTF-8");
                            String passValue = URLEncoder.encode(password.getText().toString(), "UTF-8");

                            HttpClient Client = new DefaultHttpClient();
                            String URL = "https://api.ecoachsolutions.com/main.php?ecoachsignin=1&server=remote&user=" + loginValue + "&pass=" + passValue;

                            session.createLoginSession(loginValue, passValue);


                            try {
                                String SetServerString = "";
                                String in;

                                HttpGet httpget = new HttpGet(URL);


                                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                                SetServerString = Client.execute(httpget, responseHandler);

                                JSONObject json = new JSONObject(SetServerString);
                                in = json.getString("msg");
                                JSONObject profile = json.getJSONObject("profile");
                                user = profile.getString("user");
                                gender = profile.getString("gender");
                                telephone = profile.getString("tel");
                                school = profile.getString("sch");
                                residence = profile.getString("location");
                                country = profile.getString("country");
                                level = profile.getString("level");
                                dob = profile.getString("March");
                                msg = in.toString();


                                Toast.makeText(getBaseContext(), in, Toast.LENGTH_LONG).show();
                                System.out.println(SetServerString);
                                _spinner.setVisibility(View.INVISIBLE);
                                // httpget.abort();
                            } catch (Exception ex) {
                                Toast.makeText(getBaseContext(), "Sorry Failed", Toast.LENGTH_LONG).show();
                                System.out.println(ex);
                            }
                        } catch (UnsupportedEncodingException ex) {
                            Toast.makeText(getBaseContext(), "Failed", Toast.LENGTH_LONG).show();
                        }

                    }
                });

                return null;
            }

            @Override
            protected void onPostExecute(Intent intent) {
                if (msg.equals("Login successful")) {
                    finishLogin(intent);
                } else {
                    // username / password doesn't match
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            //Make Progress Bar visible
            protected void onPreExecute() {
                _spinner.setVisibility(View.VISIBLE);
            }
        }.execute();


    }

    private void ShowLoginError(int resource) {
        //  username.setBackgroundColor(Color.RED);
        //  password.setBackgroundColor(Color.RED);
        Toast.makeText(getBaseContext(), resource, Toast.LENGTH_SHORT).show();
        _spinner.setVisibility(View.INVISIBLE);
    }

    //@TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private boolean NonEmptyCredentials(String uname, String pwd) {
        return !uname.equals("") && !pwd.equals("");
    }

    private void finishLogin(Intent intent) {

        goToMainContent();


    }

    private void goToMainContent() {
        Intent intent = new Intent(SignIn.this, DrawerMain.class);
        startActivity(intent);
        finish();
    }


    private void disableStrictMode() {
        // StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        // StrictMode.setThreadPolicy(policy);

        try {
            Class<?> strictModeClass = Class.forName("android.os.StrictMode", true, Thread.currentThread().getContextClassLoader());
            Class<?> threadPolicyClass = Class.forName("android.os.StrictMode$ThreadPolicy", true, Thread.currentThread().getContextClassLoader());
            Class<?> threadPolicyBuilderClass = Class.forName("android.os.StrictMode$ThreadPolicy$Builder", true, Thread.currentThread().getContextClassLoader());

            Method setThreadPolicyMethod = strictModeClass.getMethod("setThreadPolicy", threadPolicyClass);

            Method detectAllMethod = threadPolicyBuilderClass.getMethod("detectAll");
            Method penaltyMethod = threadPolicyBuilderClass.getMethod("penaltyLog");
            Method buildMethod = threadPolicyBuilderClass.getMethod("build");

            Constructor<?> threadPolicyBuilderConstructor = threadPolicyBuilderClass.getConstructor();
            Object threadPolicyBuilderObject = threadPolicyBuilderConstructor.newInstance();

            Object obj = detectAllMethod.invoke(threadPolicyBuilderObject);

            obj = penaltyMethod.invoke(obj);
            Object threadPolicyObject = buildMethod.invoke(obj);
            setThreadPolicyMethod.invoke(strictModeClass, threadPolicyObject);
        } catch (Exception ex) {
            Log.w("disableStrictMode", ex);
        }
    }


}

