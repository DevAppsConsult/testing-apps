package ecoach.e_test_mobile_application;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

/**
 * Created by banktech on 10/9/2014.
 */
public class Subjectinfo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subjectinfo);

        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

    }
}
