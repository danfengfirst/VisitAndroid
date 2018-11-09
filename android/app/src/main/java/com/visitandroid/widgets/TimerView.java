package com.visitandroid.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.react.views.view.ReactViewGroup;
import com.visitandroid.R;

import java.text.MessageFormat;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Danfeng on 2018/11/8.
 */

public class TimerView extends LinearLayout {
    private Timer mTimer;
    private TimerTask mTimerTask;
    private TextView mTimerTv;
    private Context mContext;
    private int mCount;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTimerTv.setText(mCount + "");
            int w = MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY);
            int h = MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.EXACTLY);
            measure(
                    w,
                    h);
            layout(getLeft(), getTop(), getRight(), getBottom());
        }
    };

    public TimerView(Context context) {
        this(context,null);
    }

    public TimerView(Context context, @Nullable AttributeSet attrs) {
        super(context);
        init(context);
    }


    private void init(Context context) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.timer_view, this);
        view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        mTimerTv = view.findViewById(R.id.timer_tv);
        mTimerTv.setText(10 + "");
    }


    public void startTimer(int totalcount) {
        mCount = totalcount;
        mTimerTv.setText(totalcount + "");
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (mCount >= 0) {
                    --mCount;
                    mHandler.sendEmptyMessage(0);
                } else {
                    if (mTimer != null) {
                        mTimer.cancel();
                        mTimer = null;
                    }
                }
            }
        };
        mTimer.schedule(mTimerTask, 0, 1000);
    }
}
