package ecoach.e_test_mobile_application;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by banktech on 11/5/2014.
 */
public class ActionItem {
    private Drawable icon;
    private Bitmap thumb;
    private String title;
    private boolean selected;

    public ActionItem() {
    }

    public ActionItem(Drawable icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Bitmap getThumb() {
        return this.thumb;
    }

    public void setThumb(Bitmap thumb) {
        this.thumb = thumb;
    }
}
