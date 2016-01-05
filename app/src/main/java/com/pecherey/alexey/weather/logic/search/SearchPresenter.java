package com.pecherey.alexey.weather.logic.search;

import android.content.Context;

import com.alexey.network.interfaces.ISearch;
import com.alexey.network.search.SearchManager;
import com.alexey.network.search.SearchResult;
import com.pecherey.alexey.weather.activities.SearchActivity;

import java.util.List;

/**
 * Created by Алексей on 25.12.2015.
 */
public class SearchPresenter implements ISearch {
    private static SearchPresenter ourInstance = new SearchPresenter();

    public static SearchPresenter getInstance() {
        return ourInstance;
    }

    private SearchPresenter() {
        mManager = new SearchManager(this);
    }

    private Context mContext;
    private SearchManager mManager;
    private SearchAdapter mAdapter;

    public void setContext(Context context) {
        mContext = context;
    }

    public void search(String what) {
        mManager.search(what);
    }

    public SearchAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onSearchStart() {
        ((SearchActivity)mContext).startSearch();
    }

    @Override
    public void onSearchFinish(List<SearchResult> result) {
        ((SearchActivity)mContext).stopSearch();
        mAdapter = new SearchAdapter(mContext, result);
    }
}
