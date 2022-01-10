package ecoach.e_test_mobile_application;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Random;

/**
 * Created by banktech on 10/9/2014.
 */
public class Subject extends ActionBarActivity {

    static JSONArray topics,topictests ,pastqtns, quesList = null;
    Intent menu = null;
    BufferedReader bReader = null;
    Button diag, pastqtn, mocktest, topictest;
    TextView tvHeader;
    Random rnd = new Random();
    Boolean setPressed = true;
    String pastquestions;
    String header,year, mockone, name;
    JSONObject debug,getName;
    int i;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject);

        //forceShowActionBarOverflowMenu();

        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        Intent mIntent = getIntent();
        header = mIntent.getStringExtra("header");

       getNames();




        /*try {
        for(i=0; i<topictests.length();i++){
            String send = null;

             debug = topictests.getJSONObject(i);


        }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        Button getButton = (Button) findViewById(R.id.crso1);
        int[] colordiag = getResources().getIntArray(R.array.buttonone);
        int randomcolordiag = colordiag[new Random().nextInt(colordiag.length)];
        tvHeader = (TextView) findViewById(R.id.tvHead);
        tvHeader.setText(name);
        diag = (Button) findViewById(R.id.btndiagnostics);
        diag.setBackgroundColor(randomcolordiag);
        diag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] colordiag = getResources().getIntArray(R.array.buttonone);
                int randomcolordiag = colordiag[new Random().nextInt(colordiag.length)];
                if (setPressed) {
                    diag.setBackgroundColor(randomcolordiag);
                    change(v);
                } else {
                    diag.setBackgroundColor(randomcolordiag);
                    setPressed = !setPressed;
                }

            }
        });

        try {
            year = pastqtns.getJSONObject(0).getString("name");
            final String yeartwo = pastqtns.getJSONObject(1).getString("name");
            final String yearthree = pastqtns.getJSONObject(2).getString("name");
            final String yearfour = pastqtns.getJSONObject(3).getString("name");
            final String yearfive = pastqtns.getJSONObject(4).getString("name");
            final String yearsix = pastqtns.getJSONObject(5).getString("name");
            String yearseven = pastqtns.getJSONObject(6).getString("name");



        int[] colorpastqtn = getResources().getIntArray(R.array.buttontwo);
        int randomcolorpastqtn = colorpastqtn[new Random().nextInt(colorpastqtn.length)];
        pastqtn = (Button) findViewById(R.id.btnPastqtns);
        pastqtn.setBackgroundColor(randomcolorpastqtn);
        pastqtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] colorpastqtn = getResources().getIntArray(R.array.buttontwo);
                int randomcolorpastqtn = colorpastqtn[new Random().nextInt(colorpastqtn.length)];

                if (setPressed) {
                    pastqtn.setBackgroundColor(randomcolorpastqtn);
                    change(v);
                } else {
                    pastqtn.setBackgroundColor(randomcolorpastqtn);
                    setPressed = !setPressed;
                }

                Intent passco = new Intent(Subject.this, SubjectPast.class);
                passco.putExtra("jsonArray", pastqtns.toString());
                passco.putExtra("questions",pastqtns.toString());
                passco.putExtra("header",name);
                startActivity(passco);
                // close this activity
                finish();
            }
        });

                mockone = topictests.getJSONObject(0).getString("name");
                final String mocktwo = topictests.getJSONObject(1).getString("name");
                final String mockthree = topictests.getJSONObject(2).getString("name");
                final String mockfour = topictests.getJSONObject(3).getString("name");
                final String mockfive = topictests.getJSONObject(4).getString("name");
                //final String mocksix = pastqtns.getJSONObject(5).getString("name");
                String mockseven = pastqtns.getJSONObject(6).getString("name");



        int[] colormocktest = getResources().getIntArray(R.array.buttonthree);
        int randomcolormocktest = colormocktest[new Random().nextInt(colormocktest.length)];
        mocktest = (Button) findViewById(R.id.btnMocktest);
        mocktest.setBackgroundColor(randomcolormocktest);
        mocktest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] colormocktest = getResources().getIntArray(R.array.buttonthree);
                int randomcolormocktest = colormocktest[new Random().nextInt(colormocktest.length)];

                if (setPressed) {
                    mocktest.setBackgroundColor(randomcolormocktest);
                    change(v);
                } else {
                    mocktest.setBackgroundColor(randomcolormocktest);
                    setPressed = !setPressed;
                }
                Intent mock = new Intent(Subject.this, Mocktest.class);
                mock.putExtra("mockQuestions",mockone);
                mock.putExtra("mockQuestiontwo",mocktwo);
                mock.putExtra("mockQuestionthree",mockthree);
                mock.putExtra("mockQuestionfour",mockfour);
                mock.putExtra("mockQuestionfive",mockfive);
                mock.putExtra("questions",topictests.toString());
                mock.putExtra("header",name);
                startActivity(mock);
                // close this activity
                finish();
            }
        });

        int[] colortopictest = getResources().getIntArray(R.array.buttonfour);
        int randomcolortopictest = colortopictest[new Random().nextInt(colortopictest.length)];
        topictest = (Button) findViewById(R.id.btnTopictest);
        topictest.setBackgroundColor(randomcolortopictest);
        topictest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] colortopictest = getResources().getIntArray(R.array.buttonfour);
                int randomcolortopictest = colortopictest[new Random().nextInt(colortopictest.length)];
                if (setPressed) {
                    topictest.setBackgroundColor(randomcolortopictest);
                    change(v);
                } else {
                    topictest.setBackgroundColor(randomcolortopictest);
                    setPressed = !setPressed;
                }

                Intent topic = new Intent(Subject.this, TopicTest.class);
                topic.putExtra("topics",topics.toString());
                topic.putExtra("questions",topictests.toString());
                topic.putExtra("header", name);
                startActivity(topic);
                // close this activity
                finish();

            }
        });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    //--- Implement the OnClickListener callback
    public void change(View v) {
        if (v.getId() == R.id.btndiagnostics) {

            diag.setTextSize(34);
            return;
        }
        if (v.getId() == R.id.btnPastqtns) {

            pastqtn.setTextSize(34);
            return;
        }
        if (v.getId() == R.id.btnTopictest) {

            topictest.setTextSize(34);
            return;
        }
        if (v.getId() == R.id.btnMocktest) {

            mocktest.setTextSize(34);
            return;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.profile:
                Intent profile = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(profile);
                // close this activity
                finish();
                return true;
            case R.id.about:
                Intent about = new Intent(getApplicationContext(), About.class);
                startActivity(about);
                // close this activity
                finish();
                return true;
            case R.id.help:
                Intent help = new Intent(getApplicationContext(), Help.class);
                startActivity(help);
                // close this activity
                finish();
                return true;
            case R.id.logout:
                Intent logout = new Intent(getApplicationContext(), Logout.class);
                startActivity(logout);
                // close this activity
                finish();
                return true;
            //case R.id.action_check_updates:
            // check for updates action
            // return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.splash, menu);

        return super.onCreateOptionsMenu(menu);
    }


    private void forceShowActionBarOverflowMenu() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadQuestions(){
        try {
            InputStream questions = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_jhs_asante_twi);
            bReader = new BufferedReader(new InputStreamReader(questions));
            StringBuilder quesString = new StringBuilder();
            String aJsonLine = null;
            while ((aJsonLine = bReader.readLine()) != null) {
                quesString.append(aJsonLine);
            }
            Log.d(this.getClass().toString(), quesString.toString());
            JSONObject quesObj = new JSONObject(quesString.toString());
            pastquestions = quesObj.getString("name");
            quesList = quesObj.getJSONArray("questions");
            pastqtns =quesObj.getJSONArray("Past Questions");
            topictests = quesObj.getJSONArray("topicbanks");


            topics = quesObj.getJSONArray("Topics");

            Log.d(this.getClass().getName(),
                    "Num Questions " + quesList.length());
        } catch (Exception e){

        } finally {
            try {
                bReader.close();
            } catch (Exception e) {
                Log.e("", e.getMessage().toString(), e.getCause());
            }

        }
    }

        private void getNames(){
            try {
                getName = new JSONObject(header);
                name = getName.getString("name");
                topictests = getName.getJSONArray("topicbanks");
                pastqtns =getName.getJSONArray("quizzes");
                topics = getName.getJSONArray("topics");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
}
