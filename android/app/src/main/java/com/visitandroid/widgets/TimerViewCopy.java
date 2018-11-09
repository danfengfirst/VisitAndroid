package com.visitandroid.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.visitandroid.R;

import java.text.MessageFormat;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Danfeng on 2018/11/8.
 */

public class TimerViewCopy extends View {
    private Timer mTimer;
    private TimerTask mTimerTask;
    private TextView mTimerTv;
    private Context mContext;
    private int mCount;
    private Paint mTvPaint;
    private Canvas mCanvas;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            measure(
                    MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.EXACTLY));
            layout(getLeft(), getTop(), getRight(), getBottom());
            postInvalidate();
         //   mTimerTv.setText(MessageFormat.format("{0}s", mCount));
        }
    };

    public TimerViewCopy(Context context) {
        this(context, null);
    }

    public TimerViewCopy(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mTvPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mTvPaint.setColor(getResources().getColor(R.color.catalyst_redbox_background));
        mTvPaint.setTextSize(50);
        //  init(context);
    }

    private void init(Context context) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.timer_view, null,false);
        mTimerTv = view.findViewById(R.id.timer_tv);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        String text=mCount+"";
        Rect rect = new Rect();
        mTvPaint.getTextBounds(text, 0, text.length(), rect);
        canvas.drawText(text,0,rect.width(),mTvPaint);
        super.onDraw(canvas);
    }

    public void startTimer(int totalcount) {
        mCount = totalcount;
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (mCount >= 0) {
                    --mCount;
                 //   postInvalidate();

                    mHandler.sendEmptyMessage(0);

                    Log.e("开始变化",""+mCount);
                } else {
                    if (mTimer != null) {
                        mTimer.cancel();
                        mTimer = null;
                    }
                }
            }
        };
        mTimer.schedule(mTimerTask, 0,1000);
    }
    
}
