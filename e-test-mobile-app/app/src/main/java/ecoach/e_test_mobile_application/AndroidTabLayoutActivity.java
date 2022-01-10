package ecoach.e_test_mobile_application;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class AndroidTabLayoutActivity extends TabActivity {
    /**
     * Called when the activity is first created.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        TabHost tabHost = getTabHost();

        // Tab for Profile
        TabSpec photospec = tabHost.newTabSpec("Profile");
        photospec.setIndicator("Profile", getResources().getDrawable(R.drawable.icon_photos_tab));
        Intent photosIntent = new Intent(this, ProfileActivity.class);
        photospec.setContent(photosIntent);

        // Tab for Account
        TabSpec songspec = tabHost.newTabSpec("Account");
        // setting Title and Icon for the Tab
        songspec.setIndicator("Account", getResources().getDrawable(R.drawable.icon_songs_tab));
        Intent songsIntent = new Intent(this, AccountActivity.class);
        songspec.setContent(songsIntent);

        // Tab for Statistics
        TabSpec videospec = tabHost.newTabSpec("Statistics");
        videospec.setIndicator("Statistics", getResources().getDrawable(R.drawable.icon_videos_tab));
        Intent videosIntent = new Intent(this, StatsActivity.class);
        videospec.setContent(videosIntent);

        // Adding all TabSpec to TabHost
        tabHost.addTab(photospec); // Adding photos tab
        tabHost.addTab(songspec); // Adding songs tab
        tabHost.addTab(videospec); // Adding videos tab

        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        tabHost.getTabWidget().setCurrentTab(0);
        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#F17D59"));


        tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#CBCBCB"));


        tabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#CBCBCB"));

        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#000000"));
        }

        TextView tv1 = (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        tv1.setTextColor(Color.parseColor("#666666"));

        TextView tv2 = (TextView) tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        tv2.setTextColor(Color.parseColor("#666666"));

        TextView tv3 = (TextView) tabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        tv3.setTextColor(Color.parseColor("#666666"));
    }

}