package ecoach.e_test_mobile_application;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by banktech on 11/5/2014.
 */
public class PopUpWindows {

    protected Context mContext;
    protected PopupWindow mWindow;
    protected View mRootView;
    protected Drawable mBackground = null;
    protected WindowManager mWindowManager;

    public PopUpWindows(Context context) {
        mContext = context;
        mWindow = new PopupWindow(context);

        mWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    mWindow.dismiss();

                    return true;
                }

                return false;
            }
        });

        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    protected void onDismiss() {
    }

    protected void onShow() {
    }

    protected void preShow() {
        if (mRootView == null)
            throw new IllegalStateException("setContentView was not called with a view to display.");

        onShow();

        if (mBackground == null)
            mWindow.setBackgroundDrawable(new BitmapDrawable());
        else
            mWindow.setBackgroundDrawable(mBackground);

        mWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        mWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        mWindow.setTouchable(true);
        mWindow.setFocusable(true);
        mWindow.setOutsideTouchable(true);

        mWindow.setContentView(mRootView);
    }

    public void setBackgroundDrawable(Drawable background) {
        mBackground = background;
    }

    public void setContentView(View root) {
        mRootView = root;

        mWindow.setContentView(root);
    }

    public void setContentView(int layoutResID) {
        LayoutInflater inflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        setContentView(inflator.inflate(layoutResID, null));
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener listener) {
        mWindow.setOnDismissListener(listener);
    }

    public void dismiss() {
        mWindow.dismiss();
    }

}
