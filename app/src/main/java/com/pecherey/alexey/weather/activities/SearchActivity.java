package com.pecherey.alexey.weather.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.pecherey.alexey.forecast.R;
import com.pecherey.alexey.weather.logic.search.SearchPresenter;

public class SearchActivity extends AppCompatActivity {
    private static final String PROGRESS_BAR_STATUS = "PROGRESS_BAR_STATUS";
    private static final String SEARCH_BUTTON_STATUS = "SEARCH_BUTTON_STATUS";

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    private Button mSearch;
    private EditText mEditText;
    private ListView mResultsList;
    private ProgressBar mProgressBar;

    private SearchPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);


        mPresenter = SearchPresenter.getInstance();
        mPresenter.setContext(this);

        mSearch = (Button) findViewById(R.id.button);
        mEditText = (EditText) findViewById(R.id.editText);
        mResultsList = (ListView) findViewById(R.id.listView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String text = mEditText.getText().toString();
                        mPresenter.search(text);
                    }
                }).start();
            }
        });

        if (mPresenter.getAdapter() != null) {
            mResultsList.setAdapter(mPresenter.getAdapter());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PROGRESS_BAR_STATUS, mProgressBar.getVisibility());
        outState.putBoolean(SEARCH_BUTTON_STATUS, mSearch.isEnabled());
    }

    @SuppressWarnings("ResourceType")
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mProgressBar.setVisibility(savedInstanceState.getInt(PROGRESS_BAR_STATUS, View.GONE));
        mSearch.setEnabled(savedInstanceState.getBoolean(SEARCH_BUTTON_STATUS, true));
    }

    public void startSearch() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.VISIBLE);
                mSearch.setEnabled(false);
            }
        });
    }

    public void stopSearch() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.GONE);
                mSearch.setEnabled(true);
                mResultsList.setAdapter(mPresenter.getAdapter());
            }
        });
    }
}
