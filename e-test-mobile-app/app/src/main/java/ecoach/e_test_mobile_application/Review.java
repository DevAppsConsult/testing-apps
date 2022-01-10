package ecoach.e_test_mobile_application;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import ecoach.e_test_mobile_packages.QuestionPackage;

/**
 * Created by banktech on 10/13/2014.
 */
public class Review extends Activity {

    Button optionone, optiontwo, optionthree, optionfour;
    int qIndex;
    boolean review;
    int quesIndex = 0;
    //int numEvents = 0;
    int selected[] = null;
    int correctAns[] = null;
    int score = 0;
    TextView questionView;
    RadioGroup answers = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);


        final QuickAction mQuickAction = new QuickAction(this);


        optionone = (Button) findViewById(R.id.optionone);

        optionthree = (Button) findViewById(R.id.optionthree);

        optionfour = (Button) findViewById(R.id.optionfour);

        optiontwo = (Button) findViewById(R.id.optiontwo);
        optiontwo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mQuickAction.show(v);
                mQuickAction.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
            }
        });
        showQuestion();
        debug();
    }

    private void debug() {
        optionone.setBackgroundColor(Color.parseColor("#F1F1F1"));
    }

    private void showQuestion() {
        try {
            JSONObject aQues = QuestionPackage.getQuesList().getJSONObject(qIndex);
            String topichead = aQues.getString("");
            String quesValue = aQues.getString("Question");
            if (correctAns[qIndex] == -1) {
                String correctAnsStr = aQues.getString("CorrectAnswer");
                correctAns[qIndex] = Integer.parseInt(correctAnsStr);
            }

            questionView.setText(quesValue.toCharArray(), 0, quesValue.length());
            answers.check(-1);
            optionone.setTextColor(Color.BLACK);
            optionone.setBackgroundColor(Color.parseColor("#F1F1F1"));
            optiontwo.setTextColor(Color.BLACK);
            optionthree.setTextColor(Color.BLACK);
            optionfour.setTextColor(Color.BLACK);
            JSONArray ansList = aQues.getJSONArray("Answers");
            String aAns = ansList.getJSONObject(0).getString("Answer");
            optionone.setText(aAns.toCharArray(), 0, aAns.length());
            aAns = ansList.getJSONObject(1).getString("Answer");
            optiontwo.setText(aAns.toCharArray(), 0, aAns.length());
            aAns = ansList.getJSONObject(2).getString("Answer");
            optionthree.setText(aAns.toCharArray(), 0, aAns.length());
            aAns = ansList.getJSONObject(3).getString("Answer");
            optionfour.setText(aAns.toCharArray(), 0, aAns.length());
            Log.d("", selected[qIndex] + "");
            if (selected[qIndex] == 0)
                answers.check(R.id.optionone);
            if (selected[qIndex] == 1)
                answers.check(R.id.optiontwo);
            if (selected[qIndex] == 2)
                answers.check(R.id.optionthree);
            if (selected[qIndex] == 3)
                answers.check(R.id.optionfour);


            if (review) {
                Log.d("review", selected[qIndex] + "" + correctAns[qIndex]);
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
}
