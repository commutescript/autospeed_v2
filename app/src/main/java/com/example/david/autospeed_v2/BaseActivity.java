package com.example.david.autospeed_v2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xbunny.XBunny;

/**
 * @author David.Yi
 * @Describe
 * @create 2019/4/9
 */
public abstract class BaseActivity extends Activity {

    private final String TAG = this.getClass().getSimpleName() + this.hashCode();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        XBunny.getInstance().onPageDrawBegin(TAG);
        super.onCreate(savedInstanceState);
        Log.d(TAG, "开始时间" + System.nanoTime() / 1000000);
    }


    @Override
    public void setContentView(View view) {
        super.setContentView(AutoFrameLayout.wrap(view, TAG));
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(AutoFrameLayout.wrap(LayoutInflater.from(this).inflate(layoutResID, null), TAG));
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(AutoFrameLayout.wrap(view, TAG), params);
    }
}
