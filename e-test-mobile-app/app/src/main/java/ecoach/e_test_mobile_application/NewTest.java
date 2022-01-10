package ecoach.e_test_mobile_application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Created by banktech on 10/13/2014.
 */
public class NewTest extends Activity {
    static JSONArray topics,topicbanks,quizzes, quesList,
            citizenQuesList,englishQuesList,
            ictQuesList,frenchQuesList,
            mathQuesList,scienceQuesList,rmeQuesList = null;

    static JSONObject package_primary_citizenshipObj,package_primary_englishObj,package_primary_frenchObj,
           package_primary_ictObj,package_primary_mathematicsObj,
           package_primary_rmeObj,package_primary_scienceObj;

    Intent menu = null;
    BufferedReader bReader = null;
    Button primary, jhs, shs, alevel, olevel, general;
    Random rnd = new Random();
    Boolean setPressed = true;
    ProgressBar _spinner = null;
    String ict,english,math,french,science,citizenship,rme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);
        init();

    }


    private void init(){
        _spinner = (ProgressBar) findViewById(R.id.loginProgressSpinner);

        int[] colorsix = getResources().getIntArray(R.array.buttonone);
        int randomcolorsix = colorsix[new Random().nextInt(colorsix.length)];
        primary = (Button) findViewById(R.id.primary);
        primary.setBackgroundColor(randomcolorsix);
        primary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] colorsix = getResources().getIntArray(R.array.buttonone);
                int randomcolorsix = colorsix[new Random().nextInt(colorsix.length)];

                if (setPressed)
                    primary.setBackgroundColor(randomcolorsix);
                else
                    primary.setBackgroundColor(randomcolorsix);
                setPressed = !setPressed;
                Intent select = new Intent(NewTest.this, SelectCourse.class);
                try {
                    getPrimaryPackages();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                startActivity(select);
                _spinner.setVisibility(View.VISIBLE);
                finish();
            }
        });

        int[] colorfive = getResources().getIntArray(R.array.buttontwo);
        int randomcolorfive = colorfive[new Random().nextInt(colorfive.length)];
        jhs = (Button) findViewById(R.id.jhs);
        jhs.setBackgroundColor(randomcolorfive);
        jhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] colorfive = getResources().getIntArray(R.array.buttontwo);
                int randomcolorfive = colorfive[new Random().nextInt(colorfive.length)];

                if (setPressed)
                    jhs.setBackgroundColor(randomcolorfive);
                else
                    jhs.setBackgroundColor(randomcolorfive);
                setPressed = !setPressed;
                Intent select = new Intent(NewTest.this, SelectCourse.class);
                try {
                    getJhsPackages();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                startActivity(select);
                _spinner.setVisibility(View.VISIBLE);
                // close this activity
                finish();
            }
        });

        int[] colorfour = getResources().getIntArray(R.array.buttonthree);
        int randomcolorfour = colorfour[new Random().nextInt(colorfour.length)];
        shs = (Button) findViewById(R.id.shs);
        shs.setBackgroundColor(randomcolorfour);
        shs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] colorfour = getResources().getIntArray(R.array.buttonthree);
                int randomcolorfour = colorfour[new Random().nextInt(colorfour.length)];

                if (setPressed)
                    shs.setBackgroundColor(randomcolorfour);
                else
                    shs.setBackgroundColor(randomcolorfour);
                setPressed = !setPressed;
                Intent select = new Intent(NewTest.this, SelectCourse.class);
                try {
                    getShsPackages();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                startActivity(select);
                _spinner.setVisibility(View.VISIBLE);
                // close this activity
                finish();
            }
        });

        int[] colorthree = getResources().getIntArray(R.array.buttonfour);
        int randomcolorthree = colorthree[new Random().nextInt(colorthree.length)];
        alevel = (Button) findViewById(R.id.alevel);
        alevel.setBackgroundColor(randomcolorthree);
        alevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] colorthree = getResources().getIntArray(R.array.buttonfour);
                int randomcolorthree = colorthree[new Random().nextInt(colorthree.length)];

                if (setPressed)
                    alevel.setBackgroundColor(randomcolorthree);
                else
                    alevel.setBackgroundColor(randomcolorthree);
                setPressed = !setPressed;
                //Intent select = new Intent(NewTest.this, SelectCourse.class);
                //startActivity(select);
                _spinner.setVisibility(View.VISIBLE);
                // close this activity
                finish();
            }
        });

        int[] colortwo = getResources().getIntArray(R.array.buttonfive);
        int randomcolortwo = colortwo[new Random().nextInt(colortwo.length)];
        olevel = (Button) findViewById(R.id.olevel);
        olevel.setBackgroundColor(randomcolortwo);
        olevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] colortwo = getResources().getIntArray(R.array.buttonfive);
                int randomcolortwo = colortwo[new Random().nextInt(colortwo.length)];

                if (setPressed)
                    olevel.setBackgroundColor(randomcolortwo);
                else
                    olevel.setBackgroundColor(randomcolortwo);
                setPressed = !setPressed;
                //Intent select = new Intent(NewTest.this, SelectCourse.class);
                //startActivity(select);
                _spinner.setVisibility(View.VISIBLE);
                // close this activity
                finish();
            }
        });

        int[] colorone = getResources().getIntArray(R.array.buttonsix);
        int randomcolorone = colorone[new Random().nextInt(colorone.length)];
        general = (Button) findViewById(R.id.general);
        general.setBackgroundColor(randomcolorone);
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] colorone = getResources().getIntArray(R.array.buttonsix);
                int randomcolorone = colorone[new Random().nextInt(colorone.length)];

                if (setPressed)
                    general.setBackgroundColor(randomcolorone);
                else
                    general.setBackgroundColor(randomcolorone);
                setPressed = !setPressed;
                //Intent select = new Intent(NewTest.this, SelectCourse.class);
                //startActivity(select);
                _spinner.setVisibility(View.VISIBLE);
                // close this activity
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.splash, menu);

        return super.onCreateOptionsMenu(menu);
    }


    private void getLevels(){
        
    }

    private void getPrimaryPackages() throws Exception {
        try {
            InputStream primary_citizenship = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_primary_citizenship_education_grades_4_6);
            bReader = new BufferedReader(new InputStreamReader(primary_citizenship,"UTF-8"), 8192);


            StringBuilder package_primary_citizenshipString = new StringBuilder();
            String package_primary_citizenshipJsonLine = null;
            while ((package_primary_citizenshipJsonLine = bReader.readLine()) != null) {
                package_primary_citizenshipString.append(package_primary_citizenshipJsonLine);
            }
            Log.d(this.getClass().toString(), package_primary_citizenshipString.toString());
            package_primary_citizenshipObj = new JSONObject(package_primary_citizenshipString.toString());
            citizenship = package_primary_citizenshipObj.getString("name");


            citizenQuesList = package_primary_citizenshipObj.getJSONArray("questions");
            topicbanks = package_primary_citizenshipObj.getJSONArray("topicbanks");
            Log.d(this.getClass().getName(),
                    "Num Citizenship Questions " + citizenQuesList.length());



            InputStream primary_english = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_primary_english_grades_4_6);
            bReader = new BufferedReader(new InputStreamReader(primary_english,"UTF-8"), 8192);


            StringBuilder package_primary_englishString = new StringBuilder();
            String package_primary_englishJsonLine = null;
            while ((package_primary_englishJsonLine = bReader.readLine()) != null) {
                package_primary_englishString.append(package_primary_englishJsonLine);
            }
            Log.d(this.getClass().toString(), package_primary_englishString.toString());
            package_primary_englishObj = new JSONObject(package_primary_englishString.toString());
            english = package_primary_englishObj.getString("name");


            englishQuesList = package_primary_englishObj.getJSONArray("questions");
            topicbanks = package_primary_englishObj.getJSONArray("topicbanks");
            Log.d(this.getClass().getName(),
                    "Num English Questions " + englishQuesList.length());



            InputStream primary_french = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_primary_french_4_6);
            bReader = new BufferedReader(new InputStreamReader(primary_french,"UTF-8"), 8192);


            StringBuilder package_primary_frenchString = new StringBuilder();
            String package_primary_frenchJsonLine = null;
            while ((package_primary_frenchJsonLine = bReader.readLine()) != null) {
                package_primary_frenchString.append(package_primary_frenchJsonLine);
            }
            Log.d(this.getClass().toString(), package_primary_frenchString.toString());
            package_primary_frenchObj = new JSONObject(package_primary_frenchString.toString());
            french = package_primary_frenchObj.getString("name");


            frenchQuesList = package_primary_frenchObj.getJSONArray("questions");
            topicbanks = package_primary_frenchObj.getJSONArray("topicbanks");
            Log.d(this.getClass().getName(),
                    "Num French Questions " + frenchQuesList.length());



            InputStream primary_ict = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_primary_ict_grades4_6);
            bReader = new BufferedReader(new InputStreamReader(primary_ict,"UTF-8"), 8192);


            StringBuilder package_primary_ictString = new StringBuilder();
            String package_primary_ictJsonLine = null;
            while ((package_primary_ictJsonLine = bReader.readLine()) != null) {
                package_primary_ictString.append(package_primary_ictJsonLine);
            }
            Log.d(this.getClass().toString(), package_primary_ictString.toString());
            package_primary_ictObj = new JSONObject(package_primary_ictString.toString());
            ict = package_primary_ictObj.getString("name");


            ictQuesList = package_primary_ictObj.getJSONArray("questions");
            topicbanks = package_primary_ictObj.getJSONArray("topicbanks");
            Log.d(this.getClass().getName(),
                    "Num ICT Questions " + ictQuesList.length());



            InputStream primary_mathematics = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_primary_mathematics_grades_4_6);
            bReader = new BufferedReader(new InputStreamReader(primary_mathematics,"UTF-8"), 8192);


            StringBuilder package_primary_mathematicsString = new StringBuilder();
            String package_primary_mathematicsJsonLine = null;
            while ((package_primary_mathematicsJsonLine = bReader.readLine()) != null) {
                package_primary_mathematicsString.append(package_primary_mathematicsJsonLine);
            }
            Log.d(this.getClass().toString(), package_primary_mathematicsString.toString());
            package_primary_mathematicsObj = new JSONObject(package_primary_mathematicsString.toString());
            math = package_primary_mathematicsObj.getString("name");


            mathQuesList = package_primary_mathematicsObj.getJSONArray("questions");
            topicbanks = package_primary_mathematicsObj.getJSONArray("topicbanks");
            Log.d(this.getClass().getName(),
                    "Num Math Questions " + mathQuesList.length());



            InputStream primary_rme = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_primary_rme_grades_4_6);
            bReader = new BufferedReader(new InputStreamReader(primary_rme,"UTF-8"), 8192);


            StringBuilder package_primary_rmeString = new StringBuilder();
            String package_primary_rmeJsonLine = null;
            while ((package_primary_rmeJsonLine = bReader.readLine()) != null) {
                package_primary_rmeString.append(package_primary_rmeJsonLine);
            }
            Log.d(this.getClass().toString(), package_primary_rmeString.toString());
            package_primary_rmeObj = new JSONObject(package_primary_rmeString.toString());
            rme = package_primary_rmeObj.getString("name");


            rmeQuesList = package_primary_rmeObj.getJSONArray("questions");
            topicbanks = package_primary_rmeObj.getJSONArray("topicbanks");
            Log.d(this.getClass().getName(),
                    "Num RME Questions " + rmeQuesList.length());




            InputStream primary_science = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_primary_science_grades_4_6);
            bReader = new BufferedReader(new InputStreamReader(primary_science,"UTF-8"), 8192);


            StringBuilder package_primary_science = new StringBuilder();
            String package_primary_scienceJsonLine = null;
            while ((package_primary_scienceJsonLine = bReader.readLine()) != null) {
                package_primary_science.append(package_primary_scienceJsonLine);
            }
            Log.d(this.getClass().toString(), package_primary_science.toString());
            package_primary_scienceObj = new JSONObject(package_primary_science.toString());
            science = package_primary_scienceObj.getString("name");


            scienceQuesList = package_primary_scienceObj.getJSONArray("questions");
            topicbanks = package_primary_scienceObj.getJSONArray("topicbanks");
            Log.d(this.getClass().getName(),
                    "Num science Questions " + scienceQuesList.length());

        } catch (Exception e){

        } finally {
            try {
                bReader.close();
            } catch (Exception e) {
                Log.e("", e.getMessage().toString(), e.getCause());
            }

        }
    }

    private void getJhsPackages() throws Exception {
        try {

            InputStream primary_citizenship = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_jhs_bdt);
            bReader = new BufferedReader(new InputStreamReader(primary_citizenship,"UTF-8"), 8192);


            StringBuilder package_primary_citizenshipString = new StringBuilder();
            String package_primary_citizenshipJsonLine = null;
            while ((package_primary_citizenshipJsonLine = bReader.readLine()) != null) {
                package_primary_citizenshipString.append(package_primary_citizenshipJsonLine);
            }
            Log.d(this.getClass().toString(), package_primary_citizenshipString.toString());
            package_primary_citizenshipObj = new JSONObject(package_primary_citizenshipString.toString());
            citizenship = package_primary_citizenshipObj.getString("name");


            citizenQuesList = package_primary_citizenshipObj.getJSONArray("questions");
            topicbanks = package_primary_citizenshipObj.getJSONArray("topicbanks");
            Log.d(this.getClass().getName(),
                    "Num Citizenship Questions " + citizenQuesList.length());



            InputStream primary_english = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_jhs_english_language);
            bReader = new BufferedReader(new InputStreamReader(primary_english,"UTF-8"), 8192);


            StringBuilder package_primary_englishString = new StringBuilder();
            String package_primary_englishJsonLine = null;
            while ((package_primary_englishJsonLine = bReader.readLine()) != null) {
                package_primary_englishString.append(package_primary_englishJsonLine);
            }
            Log.d(this.getClass().toString(), package_primary_englishString.toString());
            package_primary_englishObj = new JSONObject(package_primary_englishString.toString());
            english = package_primary_englishObj.getString("name");


            englishQuesList = package_primary_englishObj.getJSONArray("questions");
            topicbanks = package_primary_englishObj.getJSONArray("topicbanks");
            Log.d(this.getClass().getName(),
                    "Num English Questions " + englishQuesList.length());



            InputStream primary_french = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_jhs_french);
            bReader = new BufferedReader(new InputStreamReader(primary_french));


            StringBuilder package_primary_frenchString = new StringBuilder();
            String package_primary_frenchJsonLine = null;
            while ((package_primary_frenchJsonLine = bReader.readLine()) != null) {
                package_primary_frenchString.append(package_primary_frenchJsonLine);
            }
            Log.d(this.getClass().toString(), package_primary_frenchString.toString());
            package_primary_frenchObj = new JSONObject(package_primary_frenchString.toString());
            french = package_primary_frenchObj.getString("name");


            frenchQuesList = package_primary_frenchObj.getJSONArray("questions");
            topicbanks = package_primary_frenchObj.getJSONArray("topicbanks");
            Log.d(this.getClass().getName(),
                    "Num French Questions " + frenchQuesList.length());



            InputStream primary_ict = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_jhs_ga);
            bReader = new BufferedReader(new InputStreamReader(primary_ict,"UTF-8"), 8192);


            StringBuilder package_primary_ictString = new StringBuilder();
            String package_primary_ictJsonLine = null;
            while ((package_primary_ictJsonLine = bReader.readLine()) != null) {
                package_primary_ictString.append(package_primary_ictJsonLine);
            }
            Log.d(this.getClass().toString(), package_primary_ictString.toString());
            package_primary_ictObj = new JSONObject(package_primary_ictString.toString());
            ict = package_primary_ictObj.getString("name");


            ictQuesList = package_primary_ictObj.getJSONArray("questions");
            topicbanks = package_primary_ictObj.getJSONArray("topicbanks");
            Log.d(this.getClass().getName(),
                    "Num ICT Questions " + ictQuesList.length());



            InputStream primary_mathematics = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_primary_citizenship_education_grades_4_6);
            bReader = new BufferedReader(new InputStreamReader(primary_mathematics,"UTF-8"),8192);


            StringBuilder package_primary_mathematicsString = new StringBuilder();
            String package_primary_mathematicsJsonLine = null;
            while ((package_primary_mathematicsJsonLine = bReader.readLine()) != null) {
                package_primary_mathematicsString.append(package_primary_mathematicsJsonLine);
            }
            Log.d(this.getClass().toString(), package_primary_mathematicsString.toString());
            package_primary_mathematicsObj = new JSONObject(package_primary_mathematicsString.toString());
            math = package_primary_mathematicsObj.getString("name");


            mathQuesList = package_primary_mathematicsObj.getJSONArray("questions");
            topicbanks = package_primary_mathematicsObj.getJSONArray("topicbanks");
            Log.d(this.getClass().getName(),
                    "Num Math Questions " + mathQuesList.length());



            InputStream primary_rme = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_jhs_ict);
            bReader = new BufferedReader(new InputStreamReader(primary_rme));


            StringBuilder package_primary_rmeString = new StringBuilder();
            String package_primary_rmeJsonLine = null;
            while ((package_primary_rmeJsonLine = bReader.readLine()) != null) {
                package_primary_rmeString.append(package_primary_rmeJsonLine);
            }
            Log.d(this.getClass().toString(), package_primary_rmeString.toString());
            package_primary_rmeObj = new JSONObject(package_primary_rmeString.toString());
            rme = package_primary_rmeObj.getString("name");


            rmeQuesList = package_primary_rmeObj.getJSONArray("questions");
            topicbanks = package_primary_rmeObj.getJSONArray("topicbanks");
            Log.d(this.getClass().getName(),
                    "Num RME Questions " + rmeQuesList.length());




            InputStream primary_science = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_jhs_asante_twi);
            bReader = new BufferedReader(new InputStreamReader(primary_science));


            StringBuilder package_primary_science = new StringBuilder();
            String package_primary_scienceJsonLine = null;
            while ((package_primary_scienceJsonLine = bReader.readLine()) != null) {
                package_primary_science.append(package_primary_scienceJsonLine);
            }
            Log.d(this.getClass().toString(), package_primary_science.toString());
            package_primary_scienceObj = new JSONObject(package_primary_science.toString());
            science = package_primary_scienceObj.getString("name");


            scienceQuesList = package_primary_scienceObj.getJSONArray("questions");
            topicbanks = package_primary_scienceObj.getJSONArray("topicbanks");
            Log.d(this.getClass().getName(),
                    "Num science Questions " + scienceQuesList.length());


        } catch (Exception e){

        } finally {
            try {
                bReader.close();
            } catch (Exception e) {
                Log.e("", e.getMessage().toString(), e.getCause());
            }

        }
    }

    private void getShsPackages() throws Exception {
        try {

            InputStream primary_citizenship = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_shs_english_language);
            bReader = new BufferedReader(new InputStreamReader(primary_citizenship,"UTF-8"), 8192);


            StringBuilder package_primary_citizenshipString = new StringBuilder();
            String package_primary_citizenshipJsonLine = null;
            while ((package_primary_citizenshipJsonLine = bReader.readLine()) != null) {
                package_primary_citizenshipString.append(package_primary_citizenshipJsonLine);
            }
            Log.d(this.getClass().toString(), package_primary_citizenshipString.toString());
            package_primary_citizenshipObj = new JSONObject(package_primary_citizenshipString.toString());
            citizenship = package_primary_citizenshipObj.getString("name");


            citizenQuesList = package_primary_citizenshipObj.getJSONArray("questions");
            topicbanks = package_primary_citizenshipObj.getJSONArray("topicbanks");
            Log.d(this.getClass().getName(),
                    "Num Citizenship Questions " + citizenQuesList.length());



            InputStream primary_english = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_shs_graphics_designing);
            bReader = new BufferedReader(new InputStreamReader(primary_english,"UTF-8"), 8192);


            StringBuilder package_primary_englishString = new StringBuilder();
            String package_primary_englishJsonLine = null;
            while ((package_primary_englishJsonLine = bReader.readLine()) != null) {
                package_primary_englishString.append(package_primary_englishJsonLine);
            }
            Log.d(this.getClass().toString(), package_primary_englishString.toString());
            package_primary_englishObj = new JSONObject(package_primary_englishString.toString());
            english = package_primary_englishObj.getString("name");


            englishQuesList = package_primary_englishObj.getJSONArray("questions");
            topicbanks = package_primary_englishObj.getJSONArray("topicbanks");
            Log.d(this.getClass().getName(),
                    "Num English Questions " + englishQuesList.length());



            InputStream primary_french = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_shs_crs);
            bReader = new BufferedReader(new InputStreamReader(primary_french));


            StringBuilder package_primary_frenchString = new StringBuilder();
            String package_primary_frenchJsonLine = null;
            while ((package_primary_frenchJsonLine = bReader.readLine()) != null) {
                package_primary_frenchString.append(package_primary_frenchJsonLine);
            }
            Log.d(this.getClass().toString(), package_primary_frenchString.toString());
            package_primary_frenchObj = new JSONObject(package_primary_frenchString.toString());
            french = package_primary_frenchObj.getString("name");


            frenchQuesList = package_primary_frenchObj.getJSONArray("questions");
            topicbanks = package_primary_frenchObj.getJSONArray("topicbanks");
            Log.d(this.getClass().getName(),
                    "Num French Questions " + frenchQuesList.length());



            InputStream primary_ict = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_shs_social_studies);
            bReader = new BufferedReader(new InputStreamReader(primary_ict,"UTF-8"), 8192);


            StringBuilder package_primary_ictString = new StringBuilder();
            String package_primary_ictJsonLine = null;
            while ((package_primary_ictJsonLine = bReader.readLine()) != null) {
                package_primary_ictString.append(package_primary_ictJsonLine);
            }
            Log.d(this.getClass().toString(), package_primary_ictString.toString());
            package_primary_ictObj = new JSONObject(package_primary_ictString.toString());
            ict = package_primary_ictObj.getString("name");


            ictQuesList = package_primary_ictObj.getJSONArray("questions");
            topicbanks = package_primary_ictObj.getJSONArray("topicbanks");
            Log.d(this.getClass().getName(),
                    "Num ICT Questions " + ictQuesList.length());



            InputStream primary_mathematics = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_shs_economics);
            bReader = new BufferedReader(new InputStreamReader(primary_mathematics,"UTF-8"),8192);


            StringBuilder package_primary_mathematicsString = new StringBuilder();
            String package_primary_mathematicsJsonLine = null;
            while ((package_primary_mathematicsJsonLine = bReader.readLine()) != null) {
                package_primary_mathematicsString.append(package_primary_mathematicsJsonLine);
            }
            Log.d(this.getClass().toString(), package_primary_mathematicsString.toString());
            package_primary_mathematicsObj = new JSONObject(package_primary_mathematicsString.toString());
            math = package_primary_mathematicsObj.getString("name");


            mathQuesList = package_primary_mathematicsObj.getJSONArray("questions");
            topicbanks = package_primary_mathematicsObj.getJSONArray("topicbanks");
            Log.d(this.getClass().getName(),
                    "Num Math Questions " + mathQuesList.length());



            InputStream primary_rme = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_shs_economics);
            bReader = new BufferedReader(new InputStreamReader(primary_rme));


            StringBuilder package_primary_rmeString = new StringBuilder();
            String package_primary_rmeJsonLine = null;
            while ((package_primary_rmeJsonLine = bReader.readLine()) != null) {
                package_primary_rmeString.append(package_primary_rmeJsonLine);
            }
            Log.d(this.getClass().toString(), package_primary_rmeString.toString());
            package_primary_rmeObj = new JSONObject(package_primary_rmeString.toString());
            rme = package_primary_rmeObj.getString("name");


            rmeQuesList = package_primary_rmeObj.getJSONArray("questions");
            topicbanks = package_primary_rmeObj.getJSONArray("topicbanks");
            Log.d(this.getClass().getName(),
                    "Num RME Questions " + rmeQuesList.length());




            InputStream primary_science = this.getBaseContext().getResources()
                    .openRawResource(R.raw.package_shs_cost_accounting);
            bReader = new BufferedReader(new InputStreamReader(primary_science));


            StringBuilder package_primary_science = new StringBuilder();
            String package_primary_scienceJsonLine = null;
            while ((package_primary_scienceJsonLine = bReader.readLine()) != null) {
                package_primary_science.append(package_primary_scienceJsonLine);
            }
            Log.d(this.getClass().toString(), package_primary_science.toString());
            package_primary_scienceObj = new JSONObject(package_primary_science.toString());
            science = package_primary_scienceObj.getString("name");


            scienceQuesList = package_primary_scienceObj.getJSONArray("questions");
            topicbanks = package_primary_scienceObj.getJSONArray("topicbanks");
            Log.d(this.getClass().getName(),
                    "Num science Questions " + scienceQuesList.length());

        } catch (Exception e){

        } finally {
            try {
                bReader.close();
            } catch (Exception e) {
                Log.e("", e.getMessage().toString(), e.getCause());
            }

        }
    }

    public static JSONArray getCitizenQuesList() {
        return citizenQuesList;
    }

    public static JSONArray getEnglishQuesList() {
        return englishQuesList;
    }

    public static JSONArray getIctQuesList() {
        return ictQuesList;
    }

    public static JSONArray getFrenchQuesList() {
        return frenchQuesList;
    }

    public static JSONArray getMathQuesList() {
        return mathQuesList;
    }

    public static JSONArray getScienceQuesList() {
        return scienceQuesList;
    }

    public static JSONArray getRmeQuesList() {
        return rmeQuesList;
    }

    public static JSONObject getPackage_primary_citizenshipObj() {
        return package_primary_citizenshipObj;
    }

    public static JSONObject getPackage_primary_englishObj(){
        return package_primary_englishObj;
    }

    public static JSONObject getPackage_primary_frenchObj(){
        return package_primary_frenchObj;
    }

    public static JSONObject getPackage_primary_ictObj(){
        return package_primary_ictObj;
    }

    public static JSONObject getPackage_primary_mathematicsObj(){
        return package_primary_mathematicsObj;
    }

    public static JSONObject getPackage_primary_rmeObj(){
        return package_primary_rmeObj;
    }

    public static JSONObject getPackage_primary_scienceObj(){
        return package_primary_scienceObj;
    }
}


