package ecoach.e_test_mobile_packages;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import ecoach.e_test_mobile_application.R;

/**
 * Created by banktech on 1/8/2015.
 */
public class QuestionsActivity extends Activity {

    // JSON node keys
    private static final String TAG_QUESTION = "text";
    private static final String TAG_ANSWEROPTIONONE = "text";
    private static final String TAG_ANSWEROPTIONTWO = "text";
    private static final String TAG_ANSWEROPTIONTHREE = "text";
    private static final String TAG_ANSWEROPTIONFOUR = "text";
    private static final String TAG_CONFIRM = "confirm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_page);
        // getting intent data
        Intent in = getIntent();

        // Get JSON values from previous intent
        String question = in.getStringExtra(TAG_QUESTION);
        String optionone = in.getStringExtra(TAG_ANSWEROPTIONONE);
        String optiontwo = in.getStringExtra(TAG_ANSWEROPTIONTWO);
        String optionthree = in.getStringExtra(TAG_ANSWEROPTIONTHREE);
        String optionfour = in.getStringExtra(TAG_ANSWEROPTIONFOUR);
        String confirm = in.getStringExtra(TAG_CONFIRM);


        // Displaying all values on the screen
        TextView questionView = (TextView) findViewById(R.id.tvqtn);
        Button one = (Button) findViewById(R.id.optionone);
        Button two = (Button) findViewById(R.id.optionone);
        Button three = (Button) findViewById(R.id.optionone);
        Button four = (Button) findViewById(R.id.optionone);

        questionView.setText(question);
        one.setText(optionone);
        two.setText(optiontwo);
        one.setText(optionthree);
        two.setText(optionfour);
    }
}
