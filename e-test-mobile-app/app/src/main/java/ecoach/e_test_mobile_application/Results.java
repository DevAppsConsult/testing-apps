package ecoach.e_test_mobile_application;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ecoach.e_test_mobile_packages.QuestionPackage;

/**
 * Created by banktech on 10/10/2014.
 */
public class Results extends Activity {

    List<PieDetailsItem> piedata = new ArrayList<PieDetailsItem>(0);

    TextView tvScored, tvTotal;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        Intent mIntent = getIntent();
        int score = mIntent.getIntExtra("intVariableName", 0);
        tvScored = (TextView) findViewById(R.id.tvscored);
        String number = String.valueOf(score);
        tvScored.setText(number);

        tvTotal = (TextView) findViewById(R.id.tvoverall);

        int total = QuestionPackage.getQuesList().length();
        String overall = String.valueOf(total);
        tvTotal.setText(overall);

        int wrong = total - score;


        PieDetailsItem item;
        int maxCount = 0;
        int itemCount = 0;
        int items[] = {score, 15, wrong};
        int colors[] = {-129129129, -86116451, -102204451};
        String[] itemslabel = {"Correct 100", "Unattempted 200", "Mistakes 300"};
        for (int i = 0; i < items.length; i++) {
            itemCount = items[i];
            item = new PieDetailsItem();
            item.count = itemCount;
            item.label = itemslabel[i];
            item.color = colors[i];
            piedata.add(item);
            maxCount = maxCount + itemCount;
        }
        int size = 360;
        int BgColor = 0xfffffff;
        Bitmap mBaggroundImage = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        ViewPieChart piechart = new ViewPieChart(this);
        piechart.setLayoutParams(new RelativeLayout.LayoutParams(size, size));
        piechart.setGeometry(size, size, 2, 2, 2, 2, 2130837504);
        piechart.setSkinparams(BgColor);
        piechart.setData(piedata, maxCount);
        piechart.invalidate();
        piechart.draw(new Canvas(mBaggroundImage));
        piechart = null;
        ImageView mImageView = new ImageView(this);
        mImageView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        mImageView.setBackgroundColor(BgColor);
        mImageView.setImageBitmap(mBaggroundImage);
        LinearLayout finalLayout = (LinearLayout) findViewById(R.id.pie_container);
        finalLayout.addView(mImageView);


    }
}
