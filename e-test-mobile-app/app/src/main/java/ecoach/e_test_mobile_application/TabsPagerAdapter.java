package ecoach.e_test_mobile_application;

/**
 * Created by banktech on 11/4/2014.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new HistoryFull();
            case 1:
                // Games fragment activity
                return new HistoryStatistics();
            case 2:
                // Movies fragment activity
                return new HistoryMeanScore();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
}
