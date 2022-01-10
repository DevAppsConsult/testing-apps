package ecoach.e_test_mobile_packages;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import ecoach.e_test_mobile_application.R;

public class QuestionPackage extends Activity {
    static JSONArray topics,topicbanks,quizzes, quesList = null;
    Intent menu = null;
    BufferedReader bReader = null;

    public static JSONArray getQuesList() {

        return quesList;
    }

    public static JSONArray getTopics() {

        return topics;
    }

    public static JSONArray getTopicbanks() {

        return topicbanks;
    }

    public static JSONArray getQuizzes() {

        return quizzes;
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);

        Thread thread = new Thread() {
            public void run() {
                try {
                    //Thread.sleep(1000);
                    finish();
                    loadQuestions();
                    //loadJSONFromAsset();
                    //Intent intent = new Intent(QuestionPackage.this, TestActivity.class);
                    //QuestionPackage.this.startActivity(intent);
                } catch (Exception e) {
                }
            }
        };
        thread.start();

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
            quesList = quesObj.getJSONArray("questions");
            topicbanks =quesObj.getJSONArray("topicbanks");
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

    private void loadPrimaryPackage() throws Exception {
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
            quesList = quesObj.getJSONArray("questions");
            Log.d(this.getClass().getName(),
                    "Num Questions " + quesList.length());
        } catch (Exception e) {

        } finally {
            try {
                bReader.close();
            } catch (Exception e) {
                Log.e("", e.getMessage().toString(), e.getCause());
            }

        }
    }

}