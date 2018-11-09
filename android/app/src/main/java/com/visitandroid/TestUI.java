package com.visitandroid;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.visitandroid.widgets.TimerView;

/**
 * Created by Danfeng on 2018/11/8.
 */

public class TestUI extends SimpleViewManager<TimerView> {
    @Override
    public String getName() {
        return "TimerView";
    }

    @Override
    protected TimerView createViewInstance(ThemedReactContext reactContext) {

        return new TimerView(reactContext);
    }

    @ReactProp(name = "start")
    public void startTimer(TimerView timerView, Integer totalCount) {
          timerView.startTimer(totalCount);
    }
}
