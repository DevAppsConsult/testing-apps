package ecoach.e_test_mobile_application;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

/**
 * Created by banktech on 10/13/2014.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class HistoryActivity extends FragmentActivity {
    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.history);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("Statistics").setIndicator("Statistics", null),
                HistoryStatistics.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("Mean score").setIndicator("Mean score", null),
                HistoryMeanScore.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("Full History").setIndicator("Full History", null),
                HistoryFull.class, null);
    }

}

