package ecoach.e_test_mobile_application;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalActivity;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

/**
 * Created by banktech on 11/7/2014.
 */
public class PieGraph extends GraphicalActivity {


    public Intent getIntent(Context context) {

        int[] values = {1, 2, 3};
        String[] labels = {"10 unattempeted", "45 correct", "25 incorrect"};
        CategorySeries series = new CategorySeries("Pie Graph");

        int k = 0;


        for (int value : values) {
            series.add("" + k, value);
            //series.remove(value);
        }

        int[] colors = new int[]{Color.parseColor("#B2B2B2"), Color.parseColor("#A6C075"), Color.parseColor("#EE8D99")};

        DefaultRenderer renderer = new DefaultRenderer();
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            r.setShowLegendItem(false);
            renderer.addSeriesRenderer(r);
        }

        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        // renderer.setChartTitle("Pie Chart Demo");
        // renderer.setChartTitleTextSize(7);
        // renderer.setZoomButtonsVisible(true);

        Intent intent = ChartFactory.getPieChartIntent(context, series, renderer, null);
        return intent;
    }


}

