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
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

/**
 * Created by banktech on 10/9/2014.
 */
public class Mocktest extends ActionBarActivity {

    Button one, two, three, four, five, six;
    Random rnd = new Random();
    Boolean setPressed = true;
    String getone,gettwo,getthree,getfour,getfive,getsix,display,displaytwo,displaythree,displayfour,displayfive,displaysix;
    String  mock,mocktwo,mockthree,mockfour,mockfive,mocksix,header;

    TextView head;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mocktest);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent mIntent = getIntent();
        mock = mIntent.getStringExtra("mockQuestions");
        mocktwo = mIntent.getStringExtra("mockQuestiontwo");
        mockthree = mIntent.getStringExtra("mockQuestionthree");
        mockfour = mIntent.getStringExtra("mockQuestionfour");
        mockfive = mIntent.getStringExtra("mockQuestionfive");
       // mocksix = mIntent.getStringExtra("mockQuestionsix");
        header = mIntent.getStringExtra("header");


        head = (TextView)findViewById(R.id.tvhelp);
        head.setText(header);
        String topicsArray = mIntent.getStringExtra("topics");
        final String topicstest = mIntent.getStringExtra("questions");
        try {
            JSONArray arrayoftopic = new JSONArray(topicstest);
            for(int i=0; i<arrayoftopic.length();i++){
                JSONObject object = arrayoftopic.getJSONObject(0);
                getone = object.getString("questions");

                object = arrayoftopic.getJSONObject(1);
                gettwo = object.getString("questions");

                object = arrayoftopic.getJSONObject(2);
                getthree = object.getString("questions");

                object = arrayoftopic.getJSONObject(3);
                getfour = object.getString("questions");

                object = arrayoftopic.getJSONObject(4);
                getfive = object.getString("questions");

                object = arrayoftopic.getJSONObject(5);
                getsix = object.getString("questions");


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        int[] colorone = getResources().getIntArray(R.array.buttonone);
        int randomcolorone = colorone[new Random().nextInt(colorone.length)];

        one = (Button) findViewById(R.id.btnyrone);
        one.setText(mock);
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
                Intent yr = new Intent(Mocktest.this, TestActivity.class);
                yr.putExtra("subject",header);
                yr.putExtra("topic",mock);
                yr.putExtra("questions",getone);
                startActivity(yr);
                // close this activity
                finish();
            }
        });

        int[] colortwo = getResources().getIntArray(R.array.buttontwo);
        int randomcolortwo = colortwo[new Random().nextInt(colortwo.length)];

        two = (Button) findViewById(R.id.btnyrtwo);
        two.setText(mocktwo);
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
                Intent yr = new Intent(Mocktest.this, TestActivity.class);
                yr.putExtra("subject",header);
                yr.putExtra("topic",mocktwo);
                yr.putExtra("questions",gettwo);
                startActivity(yr);
                // close this activity
                finish();
            }
        });

        int[] colorthree = getResources().getIntArray(R.array.buttonthree);
        int randomcolorthree = colorthree[new Random().nextInt(colorthree.length)];

        three = (Button) findViewById(R.id.btnyrthree);
        three.setText(mockthree);
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
                Intent yr = new Intent(Mocktest.this, TestActivity.class);
                yr.putExtra("subject",header);
                yr.putExtra("topic",mockthree);
                yr.putExtra("questions",getthree);
                startActivity(yr);
                // close this activity
                finish();
            }
        });

        int[] colorfour = getResources().getIntArray(R.array.buttonfour);
        int randomcolorfour = colorfour[new Random().nextInt(colorfour.length)];

        four = (Button) findViewById(R.id.btnyrfour);
        four.setText(mockfour);
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
                Intent yr = new Intent(Mocktest.this, TestActivity.class);
                yr.putExtra("subject",header);
                yr.putExtra("topic",mockfour);
                yr.putExtra("questions",getfour);
                startActivity(yr);
                // close this activity
                finish();
            }
        });

        int[] colorfive = getResources().getIntArray(R.array.buttonfive);
        int randomcolorfive = colorfive[new Random().nextInt(colorfive.length)];

        five = (Button) findViewById(R.id.btnyrfive);
        five.setText(mockfive);
        five.setBackgroundColor(randomcolorfive);
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
                Intent yr = new Intent(Mocktest.this, TestActivity.class);
                yr.putExtra("subject",header);
                yr.putExtra("topic",mockfive);
                yr.putExtra("questions",getfive);
                startActivity(yr);
                // close this activity
                finish();
            }
        });

        int[] colorsix = getResources().getIntArray(R.array.buttonsix);
        int randomcolorsix = colorsix[new Random().nextInt(colorsix.length)];
        six = (Button) findViewById(R.id.btnyrsix);
        //six.setText(mocksix);
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
                Intent yr = new Intent(Mocktest.this, TestActivity.class);
                yr.putExtra("questions",getsix);
                startActivity(yr);
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

}
