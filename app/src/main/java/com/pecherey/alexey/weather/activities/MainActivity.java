package com.pecherey.alexey.weather.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.pecherey.alexey.forecast.R;
import com.pecherey.alexey.weather.logic.forecast.ForecastPresenter;


public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private ForecastPresenter mPresenter;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = ForecastPresenter.getInstance();
        mPresenter.setContext(this);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Log.i("tag", "what " + msg.what);
                method(mPresenter.getAdapter());
            }
        };
        //final ForecastPresenter presenter = ForecastPresenter.getInstance();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String testAddress = "/weather/11950/";
                mPresenter.update(testAddress, 0);
                mPresenter.update(testAddress, 1);
                mPresenter.update(testAddress, 2);
                mPresenter.update(testAddress, 3);
                mPresenter.update(testAddress, 4);
                mPresenter.update(testAddress, 5);
                mPresenter.update(testAddress, 6);
                mHandler.sendEmptyMessage(42);
            }
        }).start();

        mListView = (ListView)findViewById(R.id.listView2);
    }

    public void method(final BaseAdapter adapter) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mListView.setAdapter(adapter);
            }
        });
    }
}