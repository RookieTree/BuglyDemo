package com.tree.showanimationdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.meituan.android.walle.WalleChannelReader;

public class MainActivity
        extends AppCompatActivity
{

    private FrameLayout  mSurface_container;
    private TextView     mTvTitle;
    private TextView     mTvBottom;
    private TextView     mTvBottom2;
    private LinearLayout ll;


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                removeMessages(100);
                removeCallbacks(mRunnable);
                postDelayed(mRunnable, 3000);

            }

        }
    };

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mTvTitle.setTag(1);
            //            mTvTitle.setVisibility(View.GONE);
            mTvTitle.animate()
                    .translationY(-mDis);
            Log.e("animte", "hide mTvTitle.getHeight(): " + -mDis);
        }
    };

    //触摸显示消失定时任务

    private int    mDis;
    private Button mBtn;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSurface_container = findViewById(R.id.surface_container);
        mTvTitle = findViewById(R.id.tv_title);
        mTvBottom = findViewById(R.id.tv_bottom);
        mTvBottom2 = findViewById(R.id.tv_bottom2);
        mBtn = findViewById(R.id.btn);
        ll = findViewById(R.id.ll);

        String channel = WalleChannelReader.getChannel(this);
        mTvTitle.setText(channel + "  标题  " + BuildConfig.VERSION_NAME);
        //        mTvTitle.setText(channel + "  标题 " + BuildConfig.VERSION_NAME + "fixed");


        mSurface_container.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                //                mDis = px2dip(MainActivity.this, mTvTitle.getHeight());
                mDis = mTvTitle.getHeight();

                switch (motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mTvTitle.getTag() == null || (int) mTvTitle.getTag() == 0) {
                            //隐藏
                            mTvTitle.setTag(1);
                            Log.e("animte", "hide mTvTitle.getHeight(): " + -mDis);
                            mTvTitle.animate()
                                    .translationY(-mDis);

                        } else {
                            //显示
                            mTvTitle.setTag(0);
                            Log.e("animte", "show mTvTitle.getHeight(): " + mDis);
                            mTvTitle.animate()
                                    .translationY(0);
                            mHandler.sendEmptyMessage(100);
                        }
                        break;

                    default:
                        break;
                }
                return true;
            }
        });

        mTvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "title", Toast.LENGTH_SHORT)
                     .show();
            }
        });

        mTvBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT)
                     .show();
            }
        });


        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String url = null;
                String url = "12121212";
                Toast.makeText(MainActivity.this, "url :" + url.length(), Toast.LENGTH_SHORT)
                     .show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

}

