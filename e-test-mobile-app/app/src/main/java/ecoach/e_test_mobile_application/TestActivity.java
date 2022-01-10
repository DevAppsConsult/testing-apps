package ecoach.e_test_mobile_application;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ecoach.e_test_mobile_packages.QuestionPackage;

/**
 * Created by banktech on 10/9/2014.
 */
public class TestActivity extends ActionBarActivity {

    private ProgressDialog pDialog;
    static JSONArray topics,topicbanks,quizzes,jsArray, quesList = null;
    Intent menu = null;
    BufferedReader bReader = null;
    QuestionPackage json = new QuestionPackage();



    // contacts JSONArray
    JSONObject allquestions = null;

    // Hashmap for question
    ArrayList<HashMap<String, String>> questionslist;

    public List<String> results; // distractors plus real answer
    //public String[] answers;
    public String[] questions;

    boolean timerProcessing = false;
    int i;
    Button optionone, optiontwo, optionthree, optionfour, play, stop;
    TextView tvtime, questionView, answerView, mTextField, intruction, subject, year,instructcontent, topic;
    RadioGroup answers = null;
    JSONObject questionMark, currentDynamicValue;

    JSONObject obj;
    Boolean[] testmix = {true, false};
    boolean review = false;
    String name;
    JSONArray vname = null;
    //Boolean setPressed = false;
    //private int EnabledButton;
    Button back, next = null;
    String subjectText,currentDynamicKey,pastyear,me,answer,qtn;
    //int selectedAnswer = -1;
    int quesIndex = 0;

    int selected[] = null;
    int correctAns[] = null;
    int score = 0;
    int increase = 0;
    private ProgressBar progress = null;
    private int progressBarStatus = 0;
    private Boolean anyselected;
    private int currentQuestion;
    ArrayList<String> arr = new ArrayList<String>();
    ArrayList<String> qtns = new ArrayList<String>();
    ArrayList<String> ans = new ArrayList<String>();
    String[] keyArr;
    Iterator<String> see;
    public static JSONArray getQuesList() {

        return quesList;
    }

    public static JSONArray getTopicList() {

        return topicbanks;
    }

    public static JSONArray getJsArray() {

        return jsArray;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_page);

        //forceShowActionBarOverflowMenu();

        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        ScrollView quizLayout = (ScrollView) findViewById(R.id.quizLayout);
        quizLayout.setVisibility(View.INVISIBLE);

        Intent mIntent = getIntent();

        me = mIntent.getStringExtra("topic");
        qtn = mIntent.getStringExtra("questions");
        System.out.println("this is topic tests "+qtn);
        subjectText = mIntent.getStringExtra("subject");
        pastyear = mIntent.getStringExtra("year");
        questionslist = new ArrayList<HashMap<String, String>>();
        //client = new DefaultHttpClient();
        //new Read().execute("text");

        init();

        try {
            topicbanks = new JSONArray(qtn);
            System.out.println("this is qtn "+topicbanks);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            loadQuestions();
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            topic = (TextView) findViewById(R.id.tvtopicname);
            year = (TextView) findViewById(R.id.tvYr);
            year.setText(pastyear);
            questionView = (TextView) findViewById(R.id.tvqtn);
            intruction = (TextView) findViewById(R.id.tvinstructcontent);
            optionone = (Button) findViewById(R.id.optionone);
            optiontwo = (Button) findViewById(R.id.optiontwo);
            optionthree = (Button) findViewById(R.id.optionthree);
            optionfour = (Button) findViewById(R.id.optionfour);
            subject = (TextView) findViewById(R.id.tvTopic);
            subject.setText(subjectText);
            answers = (RadioGroup) findViewById(R.id.answers);
            RadioGroup questionLayout = (RadioGroup) findViewById(R.id.answers);
            Button Review = (Button) findViewById(R.id.review);
            Review.setOnClickListener(finishListener);

            back = (Button) findViewById(R.id.back);
            //back.setVisibility(View.GONE);
            back.setOnClickListener(prevListener);
            next = (Button) findViewById(R.id.next);
            //next.setVisibility(View.GONE);
            next.setOnClickListener(nextListener);


            selected = new int[getQuesList().length()];
            Arrays.fill(selected, -1);
            correctAns = new int[getQuesList().length()];
            Arrays.fill(correctAns, -1);


            this.showQuestion(0, review);

            quizLayout.setVisibility(View.VISIBLE);

        } catch (Exception e) {
            Log.e("", e.getMessage().toString(), e.getCause());
        }


    }

    private void showQuestion(int qIndex,boolean review) {
        try {

            //JSONObject tQues = getTopicList().getJSONObject(qIndex);
            //System.out.println("this a question for abrabo "+tQues);
            //Iterator<?> keys = tQues.keys();

           /* while( keys.hasNext() ){
                String key = (String)keys.next();
                //System.out.println("this is key "+key);
                JSONArray tList = tQues.getJSONArray("questions");
                // get the value of the dynamic key
                //JSONObject currentDynamicValue = tQues.getJSONObject(key);
                //System.out.println("this is currentDynamicValue "+currentDynamicValue);
                for (int i = 0; i < tList.length(); i++) {
                    String c = tList.getString(i);

                    System.out.println("this is c "+c);
                }


            }
            */

            JSONObject aQues = getTopicList().getJSONObject(qIndex);
            //jsArray = aQues.getJSONArray("questions");
            //System.out.println("this is jsArray "+jsArray);
           // JSONObject tQues = getJsArray().getJSONObject(qIndex);



            String quesValue = aQues.getString("text");
            System.out.println("this is tQues "+aQues);
            String instructions = aQues.getString("instructions");
            //String topics = aQues.getString("name");
            topic.setText(me);
            intruction.setText(instructions.toCharArray(), 0, instructions.length());
            if (correctAns[qIndex] == -1) {
                String correctAnsStr = aQues.getString("confirmed");
                correctAns[qIndex] = Integer.parseInt(correctAnsStr);
            }

            questionView.setText(quesValue.toCharArray(), 0, quesValue.length());
            answers.check(-1);
            optionone.setTextColor(Color.BLACK);
            optiontwo.setTextColor(Color.BLACK);
            optionthree.setTextColor(Color.BLACK);
            optionfour.setTextColor(Color.BLACK);
            JSONArray ansList = aQues.getJSONArray("answers");
            String aAns = ansList.getJSONObject(0).getString("text");
            optionone.setText(aAns.toCharArray(), 0, aAns.length());
            aAns = ansList.getJSONObject(1).getString("text");
            optiontwo.setText(aAns.toCharArray(), 0, aAns.length());
            aAns = ansList.getJSONObject(2).getString("text");
            optionthree.setText(aAns.toCharArray(), 0, aAns.length());
            aAns = ansList.getJSONObject(3).getString("text");
            optionfour.setText(aAns.toCharArray(), 0, aAns.length());
            Log.d("",selected[qIndex]+"");
            if (selected[qIndex] == 0)
                answers.check(R.id.optionone);
            if (selected[qIndex] == 1)
                answers.check(R.id.optiontwo);
            if (selected[qIndex] == 2)
                answers.check(R.id.optionthree);
            if (selected[qIndex] == 3)
                answers.check(R.id.optionfour);

            setScoreTitle();
            if (quesIndex == (getTopicList().length()-1))
                next.setEnabled(false);

            if (quesIndex == 0)
                back.setEnabled(false);

            if (quesIndex > 0)
                back.setEnabled(true);

            if (quesIndex < (getTopicList().length()-1))
                next.setEnabled(true);


            if (review) {
                Log.d("review",selected[qIndex]+""+correctAns[qIndex]);;
                if (selected[qIndex] != correctAns[qIndex]) {
                    if (selected[qIndex] == 0)
                        optionone.setTextColor(Color.RED);
                    if (selected[qIndex] == 1)
                        optiontwo.setTextColor(Color.RED);
                    if (selected[qIndex] == 2)
                        optionthree.setTextColor(Color.RED);
                    if (selected[qIndex] == 3)
                        optionfour.setTextColor(Color.RED);
                }
                if (correctAns[qIndex] == 0)
                    optionone.setTextColor(Color.GREEN);
                if (correctAns[qIndex] == 1)
                    optiontwo.setTextColor(Color.GREEN);
                if (correctAns[qIndex] == 2)
                    optionthree.setTextColor(Color.GREEN);
                if (correctAns[qIndex] == 3)
                    optionfour.setTextColor(Color.GREEN);
            }
        } catch (Exception e) {
            Log.e(this.getClass().toString(), e.getMessage(), e.getCause());
        }
    }

    private View.OnClickListener finishListener = new View.OnClickListener() {
        public void onClick(View v) {
            setAnswer();
            //Calculate Score

            for (int i = 0; i < correctAns.length; i++) {
                if ((correctAns[i] != -1) && (correctAns[i] == selected[i]))
                    score++;
            }
            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(TestActivity.this).create();
            alertDialog.setTitle("Score");
            alertDialog.setMessage((score) + " out of " + (getTopicList().length()));

            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Retake", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    review = false;
                    quesIndex = 0;
                    TestActivity.this.showQuestion(0, review);
                }
            });

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Review", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    review = true;
                    quesIndex = 0;
                    TestActivity.this.showQuestion(0, review);
                }
            });

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Quit", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    review = false;
                    finish();
                }
            });

            alertDialog.show();

        }
    };


    /*private View.OnClickListener nextListener = new View.OnClickListener() {
        public void onClick(View v) {
            setAnswer();


            quesIndex++;
            if (quesIndex >= getJsArray().length())
                quesIndex = getJsArray().length() - 1;
            showQuestion(quesIndex,review);
        }
    };*/



    private View.OnClickListener nextListener = new View.OnClickListener() {
        public void onClick(View v) {
            setAnswer();
            if (quesIndex != getTopicList().length() - 1) {
                quesIndex++;
                progress.setProgress(0);
                progress.setMax(100);
            } else {
                Intent myIntent = new Intent(TestActivity.this, Assesment.class);
                myIntent.putExtra("intVariableName", score);
                startActivity(myIntent);
            }
            if (quesIndex == getTopicList().length() - 1) {
                next.setText("Finish");

                Intent myIntent = new Intent(TestActivity.this, Assesment.class);
                myIntent.putExtra("intVariableName", score);
                startActivity(myIntent);
                // close this activity
                finish();
            }
            showQuestion(quesIndex, review);
        }
    };


    private View.OnClickListener prevListener = new View.OnClickListener() {
        public void onClick(View v) {
            setAnswer();
            quesIndex--;
            if (quesIndex < 0)
                quesIndex = 0;

            next.setText("Next");
            showQuestion(quesIndex, review);
        }
    };


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    private void setAnswer() {
        if (optionone.isPressed()) {
            selected[quesIndex] = 0;
        } else if (optiontwo.isPressed()) {
            selected[quesIndex] = 1;
        } else if (optionthree.isPressed()) {
            selected[quesIndex] = 2;
        } else if (optionfour.isPressed()) {
            selected[quesIndex] = 3;
        }

        Log.d("", Arrays.toString(selected));
        Log.d("", Arrays.toString(correctAns));

    }

    private void setScoreTitle() throws JSONException {
        this.setTitle("Question " + (quesIndex + 1) + "/" + getTopicList().length());
    }

    public void init() {

        mTextField = (TextView) findViewById(R.id.tvcontrols);
        progress = (ProgressBar) findViewById(R.id.progress);
        progress.setProgress(0);

        optionone = (Button) findViewById(R.id.optionone);
        optionone.setTag(1);
        //optionone.setId(i);

        optionone.setBackgroundDrawable(getResources().getDrawable(R.drawable.testselect));
        optionone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int change = (Integer) v.getTag();
                setAnswer();
                /*if (change == 1){
                    optionone.setBackgroundColor(Color.parseColor("#B2B2B2"));
                    back.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                    optiontwo.setEnabled(false);
                    optionthree.setEnabled(false);
                    optionfour.setEnabled(false);

                    v.setTag(0);
                }else{
                    optionone.setBackgroundColor(Color.parseColor("#F1F1F1"));
                    back.setVisibility(View.GONE);
                    next.setVisibility(View.GONE);
                    optiontwo.setEnabled(true);
                    optionthree.setEnabled(true);
                    optionfour.setEnabled(true);
                    v.setTag(1);
                }*/
            }
        });

        optiontwo = (Button) findViewById(R.id.optiontwo);
        optiontwo.setTag(1);
        optiontwo.setBackgroundDrawable(getResources().getDrawable(R.drawable.testselect));
        optiontwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int change = (Integer) v.getTag();
                setAnswer();
                /*if (change == 1){
                    optiontwo.setBackgroundColor(Color.parseColor("#B2B2B2"));
                    back.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                    optionone.setEnabled(false);
                    optionthree.setEnabled(false);
                    optionfour.setEnabled(false);
                    setAnswer();
                    v.setTag(0);
                }else{
                    optiontwo.setBackgroundColor(Color.parseColor("#F1F1F1"));
                    back.setVisibility(View.GONE);
                    next.setVisibility(View.GONE);
                    optionone.setEnabled(true);
                    optionthree.setEnabled(true);
                    optionfour.setEnabled(true);
                    v.setTag(1);
                } */
            }
        });

        optionthree = (Button) findViewById(R.id.optionthree);
        optionthree.setTag(1);
        optionthree.setBackgroundDrawable(getResources().getDrawable(R.drawable.testselect));
        optionthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnswer();
                final int change = (Integer) v.getTag();
                /*if (change == 1){
                    optionthree.setBackgroundColor(Color.parseColor("#B2B2B2"));
                    back.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                    optionone.setEnabled(false);
                    optiontwo.setEnabled(false);
                    optionfour.setEnabled(false);
                    setAnswer();
                    v.setTag(0);
                }else{
                    optionthree.setBackgroundColor(Color.parseColor("#F1F1F1"));
                    back.setVisibility(View.GONE);
                    next.setVisibility(View.GONE);
                    optionone.setEnabled(true);
                    optiontwo.setEnabled(true);
                    optionfour.setEnabled(true);
                    v.setTag(1);
                }*/


            }
        });

        optionfour = (Button) findViewById(R.id.optionfour);
        optionfour.setTag(1);
        optionfour.setBackgroundDrawable(getResources().getDrawable(R.drawable.testselect));

        optionfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnswer();
                final int change = (Integer) v.getTag();
               /* if (change == 1){
                    optionfour.setBackgroundColor(Color.parseColor("#B2B2B2"));
                    back.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                    optionone.setEnabled(false);
                    optiontwo.setEnabled(false);
                    optionthree.setEnabled(false);
                    setAnswer();
                    v.setTag(0);
                }else{
                    optionfour.setBackgroundColor(Color.parseColor("#F1F1F1"));
                    back.setVisibility(View.GONE);
                    next.setVisibility(View.GONE);
                    optionone.setEnabled(true);
                    optiontwo.setEnabled(true);
                    optionthree.setEnabled(true);
                    v.setTag(1);
                } */
            }
        });


        tvtime = (TextView) findViewById(R.id.tvtime);
        //mTextField.setText("Time Spent "+"00 min : 00 sec");
        final CountDownTimer timer = new CountDownTimer(180000, 1000) {
            @TargetApi(Build.VERSION_CODES.GINGERBREAD)
            public void onTick(long millisUntilFinished) {
                mTextField.setText("Time Spent: " + String.format("%d min : %d sec",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                mTextField.setText("00 : 00");
                timerProcessing = false;
                setAnswer();
                //Calculate Score

                for (int i = 0; i < correctAns.length; i++) {
                    if ((correctAns[i] != -1) && (correctAns[i] == selected[i]))
                        score++;
                }
                AlertDialog alertDialog;
                alertDialog = new AlertDialog.Builder(TestActivity.this).create();
                alertDialog.setTitle("       Your Time is Up ! ");
                //alertDialog.setMessage((score) +" out of " + (QuizFunActivity.getQuesList().length()));


                // TestActivity.this.showQuestion(0, review);
                Intent myIntent = new Intent(TestActivity.this, Assesment.class);
                myIntent.putExtra("intVariableName", score);
                startActivity(myIntent);
                // close this activity
                finish();


//                alertDialog.show();
            }
        }.start();


        play = (Button) findViewById(R.id.pause);
        play.setTag(1);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int status = (Integer) v.getTag();
                if (status == 1) {
                    //play.setText("START TEST");
                    play.setBackgroundResource(R.drawable.pause);
                    timer.start();
                    v.setTag(0); //pause
                } else {
                    // play.setText("TEST MIX");
                    play.setBackgroundResource(R.drawable.play);
                    try {
                        timer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    v.setTag(1); //pause
                }
            }
        });

        stop = (Button) findViewById(R.id.stop);
        stop.setTag(1);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int status = (Integer) v.getTag();
                if (status == 1) {
                    // play.setText("START TEST");
                    play.setBackgroundResource(R.drawable.play);

                    v.setTag(0); //pause
                } else {
                    // play.setText("TEST MIX");
                    stop.setBackgroundResource(R.drawable.stop);
                    timer.cancel();
                    v.setTag(1); //pause
                }
            }
        });

     /*  back = (Button) findViewById(R.id.back);
        back.setVisibility(View.GONE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPreviousQuestion();
            }
        });

        next = (Button) findViewById(R.id.next);
        next.setVisibility(View.GONE);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showQuestion();
                Intent intent = new Intent(TestActivity.this, Assesment.class);
                startActivity(intent);
            }
        });*/

    }

    /*public void DeselectButtons() {
        int NumberofButtons = 4;
        for (int i = 0; i < NumberofButtons; i++) {
            if (EnabledButton != i)
                this.findViewById(i).setSelected(false);
        }

    }
*/
    public void showQuestion() {
        currentQuestion++;
        if (currentQuestion == questions.length) {
            currentQuestion = 0;
            currentQuestion++;
            questionView.setText(questions[currentQuestion]);
            answerView.setText("");
        }
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
            //topicbanks = quesObj.getJSONArray("Topic Tests");
            //System.out.println("this is topicbanks "+quesObj);
            quesList = quesObj.getJSONArray("questions");

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

    //Method that will parse the JSON file and will return a JSONObject
    /*public String loadJSONFromAsset() {
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

    /*
        public JSONObject questions (String questions)throws ClientProtocolException, IOException, JSONException{
            StringBuilder url = new StringBuilder(URL);
            url.append(questions);

            HttpGet get = new HttpGet(url.toString());
            HttpResponse r = client.execute(get);
            int status = r.getStatusLine().getStatusCode();
            if(status == 200){
                HttpEntity e = r.getEntity();
                String data = EntityUtils.toString(e);
                JSONArray timeline = new JSONArray(data);
                JSONObject qtns = timeline.getJSONObject(0);

                return qtns;
            }else {
                Toast.makeText(TestActivity.this, "error", Toast.LENGTH_SHORT);
                return null;
            }

        }


        public class Read extends AsyncTask<String, Integer, String>{

            @Override
            protected String doInBackground(String... params) {
                try {
                    json = questions("TOPIC");
                    return json.getString(params[0]);
                }catch (ClientProtocolException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                questionView.setText(result);
            }
        }
    */
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
