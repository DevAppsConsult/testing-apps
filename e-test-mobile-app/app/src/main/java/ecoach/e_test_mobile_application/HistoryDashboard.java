package ecoach.e_test_mobile_application;

import android.app.ActionBar;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by banktech on 10/13/2014.
 */
public class HistoryDashboard extends TabActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        TabHost tabHost = getTabHost();

        // Tab for HistoryStats
        TabHost.TabSpec photospec = tabHost.newTabSpec("Statistics");
        photospec.setIndicator("Statistics", getResources().getDrawable(R.drawable.icon_photos_tab));
        Intent photosIntent = new Intent(this, HistoryStats.class);
        photospec.setContent(photosIntent);

        // Tab for HistoryMeanScore
        TabHost.TabSpec songspec = tabHost.newTabSpec("Mean score");
        // setting Title and Icon for the Tab
        songspec.setIndicator("Mean score", getResources().getDrawable(R.drawable.icon_songs_tab));
        Intent songsIntent = new Intent(this, HistoryMeanScore.class);
        songspec.setContent(songsIntent);

        // Tab for FullHistory
        TabHost.TabSpec videospec = tabHost.newTabSpec("Full History");
        videospec.setIndicator("Full History", getResources().getDrawable(R.drawable.icon_videos_tab));
        Intent videosIntent = new Intent(this, FullHistory.class);
        videospec.setContent(videosIntent);

        // Adding all TabSpec to TabHost
        tabHost.addTab(photospec); // Adding photos tab
        tabHost.addTab(songspec); // Adding songs tab
        tabHost.addTab(videospec); // Adding videos tab

        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#F7921E"));
        }

        tabHost.getTabWidget().setCurrentTab(0);
        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#00A89C"));


        tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#FFFFFF"));


        tabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#FFFFFF"));

        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#000000"));
        }

        TextView tv1 = (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        tv1.setTextColor(Color.parseColor("#FFFFFF"));

        TextView tv2 = (TextView) tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        tv2.setTextColor(Color.parseColor("#666666"));

        TextView tv3 = (TextView) tabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        tv3.setTextColor(Color.parseColor("#666666"));
    }

}
