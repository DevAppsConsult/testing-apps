package ecoach.e_test_mobile_application;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by banktech on 10/10/2014.
 */
public class Subjectyrs extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subjectyrs);

        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.profile:
                Intent profile = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(profile);
                return true;
            case R.id.about:
                Intent about = new Intent(getApplicationContext(), About.class);
                startActivity(about);
                return true;
            case R.id.help:
                Intent help = new Intent(getApplicationContext(), Help.class);
                startActivity(help);
                return true;
            case R.id.logout:
                Intent logout = new Intent(getApplicationContext(), Logout.class);
                startActivity(logout);
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

}
