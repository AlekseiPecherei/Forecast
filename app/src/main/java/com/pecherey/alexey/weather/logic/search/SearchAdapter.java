package com.pecherey.alexey.weather.logic.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alexey.network.search.SearchResult;
import com.pecherey.alexey.handler.R;

import java.util.List;

/**
 * Created by Алексей on 25.12.2015.
 */
public class SearchAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<SearchResult> mResults;

    public SearchAdapter(Context context, List<SearchResult> objects) {
        mResults = objects;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mResults.size();
    }

    @Override
    public Object getItem(int position) {
        return mResults.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.search_element, parent, false);
        }
        SearchResult result = mResults.get(position);

        ((TextView)view.findViewById(R.id.city)).setText(result.getName());
        ((TextView)view.findViewById(R.id.discription)).setText(result.getDiscription());
        return view;
    }
}
