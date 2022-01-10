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
public class Dashboard extends TabActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        TabHost tabHost = getTabHost();

        // Tab for New Test
        TabHost.TabSpec photospec = tabHost.newTabSpec("New Test");
        photospec.setIndicator("New Test", getResources().getDrawable(R.drawable.icon_photos_tab));
        Intent photosIntent = new Intent(this, NewTest.class);
        photospec.setContent(photosIntent);

        // Tab for History
        TabHost.TabSpec songspec = tabHost.newTabSpec("History");
        // setting Title and Icon for the Tab
        songspec.setIndicator("History", getResources().getDrawable(R.drawable.icon_songs_tab));
        Intent songsIntent = new Intent(this, HistoryActivity.class);
        songspec.setContent(songsIntent);

        // Tab for Analytics
        TabHost.TabSpec videospec = tabHost.newTabSpec("Analytics");
        videospec.setIndicator("Analytics", getResources().getDrawable(R.drawable.icon_videos_tab));
        Intent videosIntent = new Intent(this, AnalyticsActivity.class);
        videospec.setContent(videosIntent);

        // Adding all TabSpec to TabHost
        tabHost.addTab(photospec); // Adding photos tab
        tabHost.addTab(songspec); // Adding songs tab
        tabHost.addTab(videospec); // Adding videos tab

        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#F7921E"));
        }

        tabHost.getTabWidget().setCurrentTab(0);
        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#ffffff"));


        tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#E5E5E5"));


        tabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#CBCBCB"));

        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#000000"));
        }

        TextView tv1 = (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        tv1.setTextColor(Color.parseColor("#F7921E"));

        TextView tv2 = (TextView) tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        tv2.setTextColor(Color.parseColor("#666666"));

        TextView tv3 = (TextView) tabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        tv3.setTextColor(Color.parseColor("#666666"));
    }
}

