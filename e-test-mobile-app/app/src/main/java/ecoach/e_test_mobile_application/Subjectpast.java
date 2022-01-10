package ecoach.e_test_mobile_application;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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

import java.lang.reflect.Field;
import java.util.Random;

/**
 * Created by banktech on 10/10/2014.
 */
public class SubjectPast extends ActionBarActivity {

    Button one, two, three, four, five, six;
    Random rnd = new Random();
    Boolean setPressed = true;
    String getone,gettwo,getthree,getfour,getfive,getsix,type;
    String years,yeartwo, yearthree, yearfour ,yearfive,yearsix;
    JSONObject retreiveqtns = null;

    TextView head;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subjectpast);


        //forceShowActionBarOverflowMenu();

        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        Intent mIntent = getIntent();
        String jsonArray = mIntent.getStringExtra("jsonArray");
        final String header = mIntent.getStringExtra("header");
        System.out.println("this is really a string array "+ jsonArray);
        final String topicstest = mIntent.getStringExtra("questions");
        try {
            JSONArray arrayoftopic = new JSONArray(topicstest);
            for(int i=0; i<arrayoftopic.length();i++){
                JSONObject object = arrayoftopic.getJSONObject(0);
                getone = object.getString("questions");
                years = object.getString("name");
                type = object.getString("type");

                object = arrayoftopic.getJSONObject(1);
                gettwo = object.getString("questions");
                yeartwo = object.getString("name");

                object = arrayoftopic.getJSONObject(2);
                getthree = object.getString("questions");
                yearthree = object.getString("name");

                object = arrayoftopic.getJSONObject(3);
                getfour = object.getString("questions");
                yearfour = object.getString("name");

                object = arrayoftopic.getJSONObject(4);
                getfive = object.getString("questions");
                yearfive = object.getString("name");

                object = arrayoftopic.getJSONObject(5);
                getsix = object.getString("questions");
                yearsix = object.getString("name");


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }



        try {
           /*
            JSONObject jso = new JSONObject(jsonArray); //however you wish to do that.
            JSONArray topics = jso.getJSONArray("Topics");

            for (int i = 0; i < topics.length(); i++) {
                String s = topics.getString(i);
                System.out.println("this a s "+s);
            }
            */
            JSONArray array = new JSONArray(jsonArray);

            System.out.println("this a jsonArray "+jsonArray);
            head = (TextView)findViewById(R.id.tvhelp);
            head.setText(header);


        int[] colorone = getResources().getIntArray(R.array.buttonone);
        int randomcolorone = colorone[new Random().nextInt(colorone.length)];

        //String aAns = array.getJSONObject(0).getString("name");
        one = (Button) findViewById(R.id.btnyrone);
        one.setText(years);
        one.setBackgroundColor(randomcolorone);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] colorone = getResources().getIntArray(R.array.buttonone);
                int randomcolorone = colorone[new Random().nextInt(colorone.length)];

                if (setPressed)
                    one.setBackgroundColor(randomcolorone);
                else
                    one.setBackgroundColor(randomcolorone);
                setPressed = !setPressed;
                Intent yr = new Intent(SubjectPast.this, TestActivity.class);
                yr.putExtra("year", years);
                yr.putExtra("subject",header);
                yr.putExtra("questions",getone);
                yr.putExtra("topic",type);
                startActivity(yr);
                // close this activity
                finish();
            }
        });

        int[] colortwo = getResources().getIntArray(R.array.buttontwo);
        int randomcolortwo = colortwo[new Random().nextInt(colortwo.length)];

           // aAns = array.getJSONObject(1).getString("name");
        two = (Button) findViewById(R.id.btnyrtwo);
        two.setText(yeartwo);
        two.setBackgroundColor(randomcolortwo);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] colortwo = getResources().getIntArray(R.array.buttontwo);
                int randomcolortwo = colortwo[new Random().nextInt(colortwo.length)];

                if (setPressed)
                    two.setBackgroundColor(randomcolortwo);
                else
                    two.setBackgroundColor(randomcolortwo);
                setPressed = !setPressed;
                Intent yr = new Intent(SubjectPast.this, TestActivity.class);
                yr.putExtra("year", yeartwo);
                yr.putExtra("subject",header);
                yr.putExtra("questions",gettwo);
                yr.putExtra("topic",type);
                startActivity(yr);
                // close this activity
                finish();
            }
        });

        int[] colorthree = getResources().getIntArray(R.array.buttonthree);
        int randomcolorthree = colorthree[new Random().nextInt(colorthree.length)];

           //aAns = array.getJSONObject(2).getString("name");
        three = (Button) findViewById(R.id.btnyrthree);
        three.setText(yearthree);
        three.setBackgroundColor(randomcolorthree);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] colorthree = getResources().getIntArray(R.array.buttonthree);
                int randomcolorthree = colorthree[new Random().nextInt(colorthree.length)];

                if (setPressed)
                    three.setBackgroundColor(randomcolorthree);
                else
                    three.setBackgroundColor(randomcolorthree);
                setPressed = !setPressed;
                Intent yr = new Intent(SubjectPast.this, TestActivity.class);
                yr.putExtra("year", yearthree);
                yr.putExtra("subject",header);
                yr.putExtra("questions",getthree);
                yr.putExtra("topic",type);
                startActivity(yr);
                // close this activity
                finish();
            }
        });

        int[] colorfour = getResources().getIntArray(R.array.buttonfour);
        int randomcolorfour = colorfour[new Random().nextInt(colorfour.length)];

           // aAns = array.getJSONObject(3).getString("name");
        four = (Button) findViewById(R.id.btnyrfour);
        four.setText(yearfour);
        four.setBackgroundColor(randomcolorfour);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] colorfour = getResources().getIntArray(R.array.buttonfour);
                int randomcolorfour = colorfour[new Random().nextInt(colorfour.length)];

                if (setPressed)
                    four.setBackgroundColor(randomcolorfour);
                else
                    four.setBackgroundColor(randomcolorfour);
                setPressed = !setPressed;
                Intent yr = new Intent(SubjectPast.this, TestActivity.class);
                yr.putExtra("year", yearfour);
                yr.putExtra("subject",header);
                yr.putExtra("questions",getfour);
                yr.putExtra("topic",type);
                startActivity(yr);
                // close this activity
                finish();
            }
        });

        int[] colorfive = getResources().getIntArray(R.array.buttonfive);
        int randomcolorfive = colorfive[new Random().nextInt(colorfive.length)];


           // aAns = array.getJSONObject(4).getString("name");
        five = (Button) findViewById(R.id.btnyrfive);
        five.setText(yearfive);
        five.setBackgroundColor(randomcolorfive);
            //final String finalAAns = aAns;
            five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] colorfive = getResources().getIntArray(R.array.buttonfive);
                int randomcolorfive = colorfive[new Random().nextInt(colorfive.length)];

                if (setPressed)
                    five.setBackgroundColor(randomcolorfive);
                else
                    five.setBackgroundColor(randomcolorfive);
                setPressed = !setPressed;
                Intent yr = new Intent(SubjectPast.this, TestActivity.class);
                yr.putExtra("year", yearfive);
                yr.putExtra("subject",header);
                yr.putExtra("questions",getfive);
                yr.putExtra("topic",type);
                startActivity(yr);
                // close this activity
                finish();
            }
        });

        int[] colorsix = getResources().getIntArray(R.array.buttonsix);
        int randomcolorsix = colorsix[new Random().nextInt(colorsix.length)];

           //aAns = array.getJSONObject(5).getString("name");
        six = (Button) findViewById(R.id.btnyrsix);
        six.setText(yearsix);
        six.setBackgroundColor(randomcolorsix);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] colorsix = getResources().getIntArray(R.array.buttonsix);
                int randomcolorsix = colorsix[new Random().nextInt(colorsix.length)];
                if (setPressed)
                    six.setBackgroundColor(randomcolorsix);
                else
                    six.setBackgroundColor(randomcolorsix);
                setPressed = !setPressed;
                Intent yr = new Intent(SubjectPast.this, TestActivity.class);
                yr.putExtra("year", yearsix);
                yr.putExtra("subject",header);
                yr.putExtra("questions",getsix);
                yr.putExtra("topic",type);
                startActivity(yr);
                // close this activity
                finish();
            }
        });
        } catch (JSONException e) {
            e.printStackTrace();
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


}
