package ecoach.e_test_mobile_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Random;
import ecoach.e_test_mobile_application.NewTest;

/**
 * Created by banktech on 11/10/2014.
 */
public class SelectCourse extends ActionBarActivity {
    static JSONArray topics,topicbanks,
            quizzes,quesList= null;

    static JSONObject geTnameOne;
    static JSONObject geTnameTwo;
    static JSONObject geTnameThree;
    static JSONObject geTnameFour;
    static JSONObject geTnameFive;
    static JSONObject geTnameSix = null;
    Intent menu = null;
    BufferedReader bReader = null;
    Button english, math, science, french, pe, twi;
    Random rnd = new Random();
    Boolean setPressed = true;
    String subone, subtwo, subthree, subfour,subfive,subsix;
    String name,SubjectOne,SubjectTwo,SubjectThree,
                SubjectFour,SubjectFive,SubjectSix,
                SubjectSeven,SubPackOne,SubPackTwo,
                SubPackThree,SubPackFour,SubPackFive,SubPackSix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectcourse);

        //forceShowActionBarOverflowMenu();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getIntents();
        getObjects();
        try {
            loadQuestions();
        } catch (Exception e) {
            e.printStackTrace();
        }



       /* try {
            JSONObject obj = null;
            obj = new JSONObject(loadJSONFromAsset());

       // JSONObject questionMark = obj.getJSONObject("questions");
        topic_head = obj.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }*/





        int[] colorone = getResources().getIntArray(R.array.buttonsix);
        int randomcolorone = colorone[new Random().nextInt(colorone.length)];
        english = (Button) findViewById(R.id.crso1);
        english.setText(subone);
        english.setBackgroundColor(randomcolorone);
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] colorone = getResources().getIntArray(R.array.buttonsix);
                int randomcolorone = colorone[new Random().nextInt(colorone.length)];

                if (setPressed)
                    english.setBackgroundColor(randomcolorone);
                else
                    english.setBackgroundColor(randomcolorone);
                setPressed = !setPressed;
                Intent sub = new Intent(SelectCourse.this, Subject.class);
                sub.putExtra("header", geTnameOne.toString());
                startActivity(sub);
                // close this activity
                finish();
            }
        });

        int[] colortwo = getResources().getIntArray(R.array.buttonfive);
        int randomcolortwo = colortwo[new Random().nextInt(colortwo.length)];
        math = (Button) findViewById(R.id.crso2);
        math.setText(subtwo);
        math.setBackgroundColor(randomcolortwo);
        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] colortwo = getResources().getIntArray(R.array.buttonfive);
                int randomcolortwo = colortwo[new Random().nextInt(colortwo.length)];

                if (setPressed)
                    math.setBackgroundColor(randomcolortwo);
                else
                    math.setBackgroundColor(randomcolortwo);
                setPressed = !setPressed;
                Intent sub = new Intent(SelectCourse.this, Subject.class);
                sub.putExtra("header",geTnameTwo.toString());
                startActivity(sub);
                // close this activity
                finish();
            }
        });

        int[] colorthree = getResources().getIntArray(R.array.buttonfour);
        int randomcolorthree = colorthree[new Random().nextInt(colorthree.length)];
        science = (Button) findViewById(R.id.crso3);
        science.setText(subthree);
        science.setBackgroundColor(randomcolorthree);
        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] colorthree = getResources().getIntArray(R.array.buttonfour);
                int randomcolorthree = colorthree[new Random().nextInt(colorthree.length)];

                if (setPressed)
                    science.setBackgroundColor(randomcolorthree);
                else
                    science.setBackgroundColor(randomcolorthree);
                setPressed = !setPressed;
                Intent sub = new Intent(SelectCourse.this, Subject.class);
                
                sub.putExtra("header", geTnameThree.toString());
                startActivity(sub);
                // close this activity
                finish();
            }
        });

        int[] colorfour = getResources().getIntArray(R.array.buttonthree);
        int randomcolorfour = colorfour[new Random().nextInt(colorfour.length)];
        french = (Button) findViewById(R.id.crso4);
        french.setText(subfour);
        french.setBackgroundColor(randomcolorfour);
        french.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] colorfour = getResources().getIntArray(R.array.buttonthree);
                int randomcolorfour = colorfour[new Random().nextInt(colorfour.length)];

                if (setPressed)
                    french.setBackgroundColor(randomcolorfour);
                else
                    french.setBackgroundColor(randomcolorfour);
                setPressed = !setPressed;
                Intent sub = new Intent(SelectCourse.this, Subject.class);

                sub.putExtra("header", geTnameFour.toString());
                startActivity(sub);
                // close this activity
                finish();
            }
        });

        int[] colorfive = getResources().getIntArray(R.array.buttontwo);
        int randomcolorfive = colorfive[new Random().nextInt(colorfive.length)];
        pe = (Button) findViewById(R.id.crso5);
        pe.setText(subfive);
        pe.setBackgroundColor(randomcolorfive);
        pe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] colorfive = getResources().getIntArray(R.array.buttontwo);
                int randomcolorfive = colorfive[new Random().nextInt(colorfive.length)];

                if (setPressed)
                    pe.setBackgroundColor(randomcolorfive);
                else
                    pe.setBackgroundColor(randomcolorfive);
                setPressed = !setPressed;
                Intent sub = new Intent(SelectCourse.this, Subject.class);

                sub.putExtra("header", geTnameFive.toString());
                startActivity(sub);
                // close this activity
                finish();
            }
        });

        int[] colorsix = getResources().getIntArray(R.array.buttonone);
        int randomcolorsix = colorsix[new Random().nextInt(colorsix.length)];
        twi = (Button) findViewById(R.id.crso6);
        twi.setText(subsix);
        twi.setBackgroundColor(randomcolorsix);
        twi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] colorsix = getResources().getIntArray(R.array.buttonone);
                int randomcolorsix = colorsix[new Random().nextInt(colorsix.length)];
                if (setPressed)
                    twi.setBackgroundColor(randomcolorsix);
                else
                    twi.setBackgroundColor(randomcolorsix);
                setPressed = !setPressed;
                Intent sub = new Intent(SelectCourse.this, Subject.class);
                sub.putExtra("header", geTnameSix.toString());
                startActivity(sub);
                // close this activity
                finish();
            }
        });
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
   

    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream questions = this.getBaseContext().getResources()
                    .openRawResource(R.raw.document);

            int size = questions.available();

            byte[] buffer = new byte[size];

            questions.read(buffer);

            questions.close();


            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return Html.fromHtml(json).toString();

    }

    private void loadQuestions() throws Exception {
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
            name = quesObj.getString("name");
            quesList = quesObj.getJSONArray("questions");
            topicbanks =quesObj.getJSONArray("Topic Tests");
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
    private  void getIntents(){
        Intent mInent = getIntent();


        SubPackOne = mInent.getStringExtra("topicOne");

        SubPackTwo = mInent.getStringExtra("topicTwo");
        SubPackThree = mInent.getStringExtra("topicThree");
        SubPackFour = mInent.getStringExtra("topicFour");
        SubPackFive = mInent.getStringExtra("topicFive");
        SubPackSix = mInent.getStringExtra("topicSix");
    }

    private void getObjects(){

           geTnameOne = NewTest.getPackage_primary_citizenshipObj();
           geTnameTwo = NewTest.getPackage_primary_englishObj();
           geTnameThree = NewTest.getPackage_primary_frenchObj();
           geTnameFour = NewTest.getPackage_primary_ictObj();
           geTnameFive = NewTest.getPackage_primary_scienceObj();
           geTnameSix = NewTest.getPackage_primary_rmeObj();
        try {
            subone = geTnameOne.getString("name");
            subtwo = geTnameTwo.getString("name");
            subthree = geTnameThree.getString("name");
            subfour = geTnameFour.getString("name");
            subfive = geTnameFive.getString("name");
            subsix = geTnameSix.getString("name");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static JSONObject getGeTnameOne(){
        return geTnameOne;
    }

    public static JSONObject getGeTnameTwo(){
        return geTnameTwo;
    }

    public static JSONObject getGeTnameThree(){
        return geTnameThree;
    }

    public static JSONObject getGeTnameFour(){
        return geTnameFour;
    }

    public static JSONObject getGeTnameFive(){
        return geTnameFive;
    }

    public static JSONObject getGeTnameSix(){
        return geTnameSix;
    }


}