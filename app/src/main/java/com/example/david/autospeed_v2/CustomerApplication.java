package com.example.david.autospeed_v2;

import android.app.Application;

import com.example.xbunny.XBunny;

/**
 * @author David.Yi
 * @Describe
 * @create 2019/5/9
 */
public class CustomerApplication extends Application {

    public CustomerApplication() {
        XBunny.getInstance().onColdStartBegin();
    }
}
