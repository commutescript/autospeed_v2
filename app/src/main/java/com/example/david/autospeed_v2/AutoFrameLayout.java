package com.example.david.autospeed_v2;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.xbunny.XBunny;

/**
 * @author David.Yi
 * @Describe
 * @create 2019/4/9
 */
public class AutoFrameLayout extends FrameLayout {

    private String TAG = "AutoFrameLayout";
    private String pageId;

    public AutoFrameLayout(Context context, String pageName) {
        super(context);
        pageId = pageName;
    }

    public static View wrap(View child, String pageName) {
        AutoFrameLayout vf = new AutoFrameLayout(child.getContext(), pageName);

        if (child.getLayoutParams() != null) {
            vf.setLayoutParams(child.getLayoutParams());
        }
        vf.addView(child, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return vf;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        // TODO 需要判断二次渲染的结束时机，这里手动设置api加载完了
        XBunny.getInstance().onApiLoadEnd(pageId);
        XBunny.getInstance().onPageDrawEnd(pageId);

        Log.d(TAG, XBunny.getInstance().getPageRenderTime(pageId) + " cold:" +XBunny.getInstance().getColdStartTime());


    }
}
